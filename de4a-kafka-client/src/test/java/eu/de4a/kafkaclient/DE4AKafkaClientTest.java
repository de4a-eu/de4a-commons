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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.kafka.common.KafkaException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.error.level.EErrorLevel;
import com.helger.httpclient.HttpClientSettings;

/**
 * Test class for class {@link DE4AKafkaClient}.
 *
 * @author Philip Helger
 */
public final class DE4AKafkaClientTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AKafkaClientTest.class);

  @BeforeClass
  public static void beforeAll ()
  {
    DE4AKafkaSettings.setKafkaEnabled (true);
  }

  @AfterClass
  public static void afterAll ()
  {
    // Disable again for other tests
    DE4AKafkaSettings.setKafkaEnabled (false);
  }

  @Test
  public void testBasic ()
  {
    if (true)
    {
      // Set the correct server to see real messages
      DE4AKafkaSettings.defaultProperties ().put ("bootstrap.servers", "de4a.simplegob.com:9092");
    }
    try
    {
      // Don't send too many - will take forever if no Kafka server is up and
      // running!
      for (int i = 0; i < 5; ++i)
        DE4AKafkaClient.send (EErrorLevel.INFO, "Value" + i);
    }
    catch (final KafkaException ex)
    {
      // lets act as if we are not surprised...
      LOGGER.error ("Something's wrong with the world today....", ex);
    }
    finally
    {
      DE4AKafkaClient.close ();
    }
  }

  @Test
  public void testDefaultProperties ()
  {
    final ICommonsMap <String, String> aProps = DE4AKafkaSettings.defaultProperties ();
    assertNotNull (aProps);
    // Ensure mutable map
    aProps.put ("foo", "bar");
    assertEquals ("bar", DE4AKafkaSettings.defaultProperties ().get ("foo"));
  }

  @Test
  @Ignore
  public void testHttpMode ()
  {
    DE4AKafkaSettings.setKafkaHttp (true);
    DE4AKafkaSettings.defaultProperties ().put ("bootstrap.servers", "https://de4a-dev-kafka.egovlab.eu");
    DE4AKafkaSettings.setHttpClientSetting (new HttpClientSettings ());

    try
    {
      for (int i = 0; i < 5; i++)
      {
        DE4AKafkaClient.send (EErrorLevel.INFO, "Test-" + i);
      }
    }
    catch (final KafkaException ex)
    {
      System.out.println ("Oupsie: " + ex.getMessage ());
    }
    finally
    {
      DE4AKafkaClient.close ();
    }
  }
}
