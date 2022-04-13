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

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.httpclient.HttpClientSettings;

/**
 * Global DE4A Kafka settings.
 *
 * @author Philip Helger
 */
public final class DE4AKafkaSettings
{
  public static final String DEFAULT_KAFKA_TOPIC = "de4a";
  public static final boolean DEFAULT_USE_HTTP = false;

  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AKafkaSettings.class);
  private static final AtomicBoolean LOGGING_ENABLED = new AtomicBoolean (true);
  private static final AtomicBoolean KAFKA_ENABLED = new AtomicBoolean (false);
  private static final AtomicReference <String> KAFKA_TOPIC = new AtomicReference <> (DEFAULT_KAFKA_TOPIC);
  private static final AtomicBoolean USE_HTTP = new AtomicBoolean (DEFAULT_USE_HTTP);
  private static final AtomicReference <HttpClientSettings> HTTP_CLIENT_SETTINGS = new AtomicReference <> (new HttpClientSettings ());

  private DE4AKafkaSettings ()
  {}

  /**
   * @return The default properties for customization. Changes to this map only
   *         effect new connections! Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  public static ICommonsMap <String, String> defaultProperties ()
  {
    return DE4AKafkaManager.defaultProperties ();
  }

  /**
   * @return <code>true</code> if Logging is enabled, <code>false</code> if not.
   *         By default is is enabled.
   */
  public static boolean isLoggingEnabled ()
  {
    return LOGGING_ENABLED.get ();
  }

  /**
   * Enable or disable logging globally.
   *
   * @param bLoggingEnabled
   *        <code>true</code> to enable, <code>false</code> to disable.
   */
  public static void setLoggingEnabled (final boolean bLoggingEnabled)
  {
    LOGGING_ENABLED.set (bLoggingEnabled);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("DE4A Logging is now " + (bLoggingEnabled ? "enabled" : "disabled"));
  }

  /**
   * @return <code>true</code> if client is enabled, <code>false</code> if not.
   *         By default is is disabled.
   */
  public static boolean isKafkaEnabled ()
  {
    return KAFKA_ENABLED.get ();
  }

  /**
   * Enable or disable globally. Call this only globally on startup.
   *
   * @param bEnabled
   *        <code>true</code> to enable, <code>false</code> to disable.
   */
  public static void setKafkaEnabled (final boolean bEnabled)
  {
    KAFKA_ENABLED.set (bEnabled);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("DE4A Kafka Client is now " + (bEnabled ? "enabled" : "disabled"));
  }

  @Nonnull
  public static String getKafkaTopic ()
  {
    return KAFKA_TOPIC.get ();
  }

  public static void setKafkaTopic (@Nonnull final String sTopic)
  {
    ValueEnforcer.notNull (sTopic, "Topic");
    KAFKA_TOPIC.set (sTopic);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("DE4A Kafka Client is now set to topic: " + KAFKA_TOPIC);
  }

  /**
   * @return <code>true</code> if HTTP mode is enable, <code>false</code> if
   *         not. Disabled by default.
   * @deprecated Use {@link #isKafkaHttpEnabled()} instead
   */
  @Deprecated
  public static boolean isHttpEnabled ()
  {
    return isKafkaHttpEnabled ();
  }

  /**
   * @return <code>true</code> if HTTP mode is enable, <code>false</code> if
   *         not. Disabled by default.
   */
  public static boolean isKafkaHttpEnabled ()
  {
    return USE_HTTP.get ();
  }

  public static void setKafkaHttp (final boolean bHttp)
  {
    USE_HTTP.set (bHttp);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("HTTP mode for Kafka is now " + (bHttp ? "enabled" : "disabled"));
  }

  @Nonnull
  public static HttpClientSettings getHttpClientSettings ()
  {
    return HTTP_CLIENT_SETTINGS.get ();
  }

  /**
   * @param settings
   *        The new HTTP client settings to be used. May not be
   *        <code>null</code>.
   * @deprecated Use {@link #setHttpClientSettings(HttpClientSettings)} instead
   */
  @Deprecated
  public static void setHttpClientSetting (@Nonnull final HttpClientSettings settings)
  {
    setHttpClientSettings (settings);
  }

  /**
   * @param settings
   *        The new HTTP client settings to be used. May not be
   *        <code>null</code>.
   */
  public static void setHttpClientSettings (@Nonnull final HttpClientSettings settings)
  {
    ValueEnforcer.notNull (settings, "HttpClientSettings");
    HTTP_CLIENT_SETTINGS.set (settings);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("HttpClientSettings are now set");
  }
}
