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
package eu.de4a.iem.xml.de4a.t43.v1_6b;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jaxb.GenericJAXBMarshaller;

/**
 * Test class for class {@link DE4AT43Marshaller}.
 *
 * @author Philip Helger
 */
public final class DE4AT43MarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AT43MarshallerTest.class);
  private static final String BASE_PATH = "src/test/resources/de4a/t4.3/v1.6b/";

  private static <T> void _testReadWrite (@Nonnull final GenericJAXBMarshaller <T> aMarshaller, @Nonnull final File aFile)
  {
    assertTrue ("Test file does not exists " + aFile.getAbsolutePath (), aFile.exists ());

    final T aRead = aMarshaller.read (aFile);
    assertNotNull ("Failed to read " + aFile.getAbsolutePath (), aRead);

    final byte [] aBytes = aMarshaller.getAsBytes (aRead);
    assertNotNull ("Failed to re-write " + aFile.getAbsolutePath (), aBytes);

    if (true)
    {
      aMarshaller.setFormattedOutput (true);
      LOGGER.info (aMarshaller.getAsString (aRead));
    }
  }

  @Test
  public void testBirthEvidence ()
  {
    _testReadWrite (DE4AT43Marshaller.birthEvidence (), new File (BASE_PATH + "Sample Birth Evidence ES.xml"));
  }

  @Test
  public void testDomicileRegistrationEvidence ()
  {
    _testReadWrite (DE4AT43Marshaller.domicileRegistrationEvidence (),
                    new File (BASE_PATH + "Sample Domicile Registration Evidence ES.xml"));
  }

  @Test
  public void testMarriageEvidence ()
  {
    _testReadWrite (DE4AT43Marshaller.marriageEvidence (), new File (BASE_PATH + "Sample Marriage Evidence ES.xml"));
  }
}
