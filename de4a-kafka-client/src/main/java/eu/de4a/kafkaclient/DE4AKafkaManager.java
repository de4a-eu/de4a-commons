package eu.de4a.kafkaclient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.httpclient.HttpClientManager;
import com.helger.httpclient.HttpClientSettings;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;
import com.helger.json.serialize.JsonWriter;

/**
 * Global Kafka resource manager. Call shutdown() upon end of application.
 *
 * @author Philip Helger
 */
final class DE4AKafkaManager
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AKafkaManager.class);
  private static final SimpleReadWriteLock RW_LOCK = new SimpleReadWriteLock ();
  @GuardedBy ("RW_LOCK")
  private static Producer <String, String> s_aProducer;

  private static final ICommonsMap <String, String> DEFAULT_PROPS = new CommonsHashMap <> ();

  static
  {
    // Instead of 16K
    // s_aProps.put ("batch.size", "1");

    // Server URL - MUST be configured
    // s_aProps.put ("bootstrap.servers", "193.10.8.211:7073");

    // Default: 5secs
    DEFAULT_PROPS.put (ProducerConfig.MAX_BLOCK_MS_CONFIG, "5000");
  }

  /**
   * @return The default properties for customization. Changes to this map only
   *         effect new connections! Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  public static ICommonsMap <String, String> defaultProperties ()
  {
    return DEFAULT_PROPS;
  }

  private DE4AKafkaManager ()
  {}

  @Nonnull
  @ReturnsMutableObject
  private static ICommonsMap <String, Object> _getCreationProperties ()
  {
    final ICommonsMap <String, Object> aProps = new CommonsHashMap <> ();
    // Use all default props
    aProps.putAll (DEFAULT_PROPS);
    return aProps;
  }

  /**
   * Init the global {@link KafkaProducer} - must be called once before the
   * first message is logged. This is only invoked internally.
   *
   * @return The non-<code>null</code> producer to be used.
   * @throws KafkaException
   *         in case of invalid properties (like non-existing server domain)
   */
  @Nonnull
  public static Producer <String, String> getOrCreateProducer ()
  {
    // Read-lock first
    Producer <String, String> ret = RW_LOCK.readLockedGet ( () -> s_aProducer);
    if (ret == null)
    {
      RW_LOCK.writeLock ().lock ();
      try
      {
        // Try again in write lock
        ret = s_aProducer;
        if (ret == null)
        {
          // Create new one
          s_aProducer = ret = new KafkaProducer <> (_getCreationProperties (),
                                                    new StringSerializer (),
                                                    new StringSerializer ());
          if (LOGGER.isDebugEnabled ())
            LOGGER.debug ("Successfully created new KafkaProducer");
        }
      }
      finally
      {
        RW_LOCK.writeLock ().unlock ();
      }
    }
    return ret;
  }

  /**
   * Shutdown the global {@link KafkaProducer}. This method can be called
   * independent of the initialization state.
   */
  public static void shutdown ()
  {
    RW_LOCK.writeLocked ( () -> {
      if (s_aProducer != null)
      {
        if (LOGGER.isDebugEnabled ())
          LOGGER.debug ("Trying to close KafkaProducer");
        s_aProducer.close ();
        s_aProducer = null;
        if (LOGGER.isDebugEnabled ())
          LOGGER.debug ("Successfully closed KafkaProducer");
      }
    });
  }

  /**
   * Main sending of a message. Since the send call is asynchronous it returns a
   * Future for the RecordMetadata that will be assigned to this record.
   * Invoking get() on this future will block until the associated request
   * completes and then return the metadata for the record or throw any
   * exception that occurred while sending the record.
   *
   * @param sKey
   *        Key to be send. May be <code>null</code>.
   * @param sValue
   *        Value to be send. May not be <code>null</code>.
   * @param aKafkaCallback
   *        Optional Kafka callback
   * @return The {@link Future} with the details on message receipt
   */
  @Nonnull
  public static Future <RecordMetadata> sendTCP (@Nullable final String sKey,
                                                 @Nonnull final String sValue,
                                                 @Nullable final Callback aKafkaCallback)
  {
    ValueEnforcer.notNull (sValue, "Value");

    final ProducerRecord <String, String> aMessage = new ProducerRecord <> (DE4AKafkaSettings.getKafkaTopic (),
                                                                            sKey,
                                                                            sValue);
    return getOrCreateProducer ().send (aMessage, aKafkaCallback);
  }

  @Nonnull
  private static byte [] _getJsonAsBytes (@Nullable final String key, @Nonnull final String value)
  {
    return new JsonWriter ().writeAsByteArray (new JsonObject ().add ("records",
                                                                      new JsonArray ().add (new JsonObject ().add ("key",
                                                                                                                   key)
                                                                                                             .add ("value",
                                                                                                                   value))),
                                               StandardCharsets.UTF_8);
  }

  @Nonnull
  public static void sendHTTP (@Nullable final String sKey, @Nonnull final String sValue)
  {
    ValueEnforcer.notNull (sValue, "Value");

    final HttpClientSettings aHttpClientSettings = DE4AKafkaSettings.getHttpClientSettings ();

    try (final HttpClientManager aHCMgr = HttpClientManager.create (aHttpClientSettings))
    {
      final ByteArrayEntity entity = new ByteArrayEntity (_getJsonAsBytes (sKey, sValue));
      entity.setContentEncoding (StandardCharsets.UTF_8.name ());

      final String sURI = (String) _getCreationProperties ().get ("bootstrap.servers") +
                          "/topics/" +
                          DE4AKafkaSettings.getKafkaTopic ();
      if (LOGGER.isDebugEnabled ())
        LOGGER.debug ("Posting to Kafka server " + sURI);

      final HttpUriRequest req = RequestBuilder.post ()
                                               .setUri (sURI)
                                               .setHeader (HttpHeaders.CONTENT_TYPE,
                                                           "application/vnd.kafka.json.v2+json; charset=utf-8")
                                               .setEntity (entity)
                                               .build ();

      try (final CloseableHttpResponse res = aHCMgr.execute (req))
      {
        if (LOGGER.isInfoEnabled ())
          LOGGER.info ("Kafka REST responsecode: " + res.getStatusLine ().getStatusCode ());
      }
    }
    catch (final IOException ex)
    {
      LOGGER.debug ("IOException: " + ex.getMessage ());
    }
  }
}
