/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.de4a.kafkaclient;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;

import com.helger.httpclient.HttpClientManager;
import com.helger.httpclient.HttpClientSettings;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.StringSerializer;


/**
 * Global Kafka resource manager. Call shutdown() upon end of application.
 *
 * @author Philip Helger
 */
final class DE4AKafkaManager
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AKafkaManager.class);
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("s_aRWLock")
  private static KafkaProducer <String, String> s_aProducer;

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
  public static KafkaProducer <String, String> getOrCreateProducer ()
  {
    // Read-lock first
    KafkaProducer <String, String> ret = s_aRWLock.readLockedGet ( () -> s_aProducer);
    if (ret == null)
    {
      s_aRWLock.writeLock ().lock ();
      try
      {
        // Try again in write lock
        ret = s_aProducer;
        if (ret == null)
        {
          // Create new one
          s_aProducer = ret = new KafkaProducer <> (_getCreationProperties (), new StringSerializer (), new StringSerializer ());
          if (LOGGER.isDebugEnabled ())
            LOGGER.debug ("Successfully created new KafkaProducer");
        }
      }
      finally
      {
        s_aRWLock.writeLock ().unlock ();
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
    s_aRWLock.writeLocked ( () -> {
      if (s_aProducer != null)
      {
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

    final ProducerRecord <String, String> aMessage = new ProducerRecord <> (DE4AKafkaSettings.getKafkaTopic (), sKey, sValue);
    return getOrCreateProducer ().send (aMessage, aKafkaCallback);
  }

  @Nonnull
  public static void sendHTTP(@Nullable final String sKey, @Nonnull final String sValue) {

      ValueEnforcer.notNull(sValue, "Value");

      HttpClientSettings settings = DE4AKafkaSettings.getHttpClientSettings();

      try(HttpClientManager mgr = HttpClientManager.create(settings)) {

          BasicHttpEntity entity = new BasicHttpEntity();
          entity.setContent(new ByteArrayInputStream(getJsonAsBytes(sKey, sValue)));
          entity.setContentEncoding("utf-8");
          HttpUriRequest req = RequestBuilder.post()
              .setUri((String) _getCreationProperties().get("bootstrap.servers") + "/topics/" + DE4AKafkaSettings.getKafkaTopic())
              .setHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.kafka.json.v2+json; charset=utf-8")
              .setEntity(entity)
              .build();

          try(CloseableHttpResponse res = mgr.execute(req)){
              if(LOGGER.isInfoEnabled())
                  LOGGER.info("Kafka REST responsecode: " + res.getStatusLine().getStatusCode());
          }

      } catch (IOException ex) {
          LOGGER.debug("IOException: " + ex.getMessage());
      }
  }

    private static byte[] getJsonAsBytes(String key, String value){
        return new JsonObject()
            .add("records", new JsonArray()
                    .add(new JsonObject()
                        .add("key", key)
                        .add("value", value)))
            .getAsJsonString().getBytes(StandardCharsets.UTF_8);
    }

}
