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

import com.helger.httpclient.HttpClientSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.ICommonsMap;

/**
 * Global DE4A Kafka settings.
 *
 * @author Philip Helger
 */
public final class DE4AKafkaSettings
{
  public static final String DEFAULT_KAFKA_TOPIC = "de4a";

  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AKafkaSettings.class);
  private static final AtomicBoolean s_aLoggingEnabled = new AtomicBoolean (true);
  private static final AtomicBoolean s_aKafkaEnabled = new AtomicBoolean (false);
  private static final AtomicReference <String> s_aKafkaTopic = new AtomicReference <> (DEFAULT_KAFKA_TOPIC);
  private static final AtomicBoolean s_aHttp = new AtomicBoolean(false);
  private static final AtomicReference<HttpClientSettings> s_oSettings = new AtomicReference<>(new HttpClientSettings());

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
   * Enable or disable logging globally.
   *
   * @param bLoggingEnabled
   *        <code>true</code> to enable, <code>false</code> to disable.
   */
  public static void setLoggingEnabled (final boolean bLoggingEnabled)
  {
    s_aLoggingEnabled.set (bLoggingEnabled);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("DE4A Logging is now " + (bLoggingEnabled ? "enabled" : "disabled"));
  }

  /**
   * @return <code>true</code> if Logging is enabled, <code>false</code> if not.
   *         By default is is enabled.
   */
  public static boolean isLoggingEnabled ()
  {
    return s_aLoggingEnabled.get ();
  }

  /**
   * Enable or disable globally. Call this only globally on startup.
   *
   * @param bEnabled
   *        <code>true</code> to enable, <code>false</code> to disable.
   */
  public static void setKafkaEnabled (final boolean bEnabled)
  {
    s_aKafkaEnabled.set (bEnabled);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("DE4A Kafka Client is now " + (bEnabled ? "enabled" : "disabled"));
  }

  /**
   * @return <code>true</code> if client is enabled, <code>false</code> if not.
   *         By default is is disabled.
   */
  public static boolean isKafkaEnabled ()
  {
    return s_aKafkaEnabled.get ();
  }

  public static void setKafkaTopic (@Nonnull final String sTopic)
  {
    ValueEnforcer.notNull (sTopic, "Topic");
    s_aKafkaTopic.set (sTopic);
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("DE4A Kafka Client is now set to topic: " + s_aKafkaTopic);
  }

  public static void setKafkaHttp(@Nonnull final boolean bHttp){
    s_aHttp.set(bHttp);
    if(LOGGER.isInfoEnabled())
      LOGGER.info("HTTP mode for Kafka is now " + (bHttp ? "enabled" : "disabled"));
  }

  /**
   * @return <code>true</code> if HTTP mode is enable, <code>false</code> if not.
   *         Disabled by default.
   */

  public static boolean isHttpEnabled(){
    return s_aHttp.get();
  }

  @Nonnull
  public static String getKafkaTopic ()
  {
    return s_aKafkaTopic.get ();
  }

  public static void setHttpClientSetting(@Nonnull final HttpClientSettings settings){
      s_oSettings.set(settings);
      if(LOGGER.isInfoEnabled())
          LOGGER.info("HttpClientSettings are now set");
  }

  public static HttpClientSettings getHttpClientSettings(){
      return s_oSettings.get();
  }
}
