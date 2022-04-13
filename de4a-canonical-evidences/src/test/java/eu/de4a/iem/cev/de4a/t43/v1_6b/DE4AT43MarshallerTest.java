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
package eu.de4a.iem.cev.de4a.t43.v1_6b;

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

    if (false)
    {
      aMarshaller.setFormattedOutput (true);
      LOGGER.info (aMarshaller.getAsString (aRead));
    }
  }

  @Test
  public void testBirthEvidence ()
  {
    for (final String s : new String [] { "MA-example-Birth-ES.xml",
                                          "MA-example-Birth-ES-A.xml",
                                          "MA-example-Birth-ES-B.xml",
                                          "MA-example-Birth-ES-C.xml",
                                          "MA-example-Birth-ES-D.xml",
                                          "MA-example-Birth-PT.xml",
                                          "MA-example-Birth-PT-A.xml",
                                          "MA-example-Birth-PT-B.xml",
                                          "MA-example-Birth-PT-C.xml",
                                          "MA-example-Birth-PT-D.xml",
                                          "Sample Birth Evidence ES.xml" })
      _testReadWrite (DE4AT43Marshaller.birthEvidence (), new File (BASE_PATH + s));
  }

  @Test
  public void testDomicileRegistrationEvidence ()
  {
    for (final String s : new String [] { "MA-example-DomicileRegistration-ES.xml",
                                          "MA-example-DomicileRegistration-ES-A.xml",
                                          "MA-example-DomicileRegistration-ES-B.xml",
                                          "MA-example-DomicileRegistration-ES-C.xml",
                                          "MA-example-DomicileRegistration-ES-D.xml",
                                          "MA-example-DomicileRegistration-PT.xml",
                                          "MA-example-DomicileRegistration-PT-A.xml",
                                          "MA-example-DomicileRegistration-PT-B.xml",
                                          "MA-example-DomicileRegistration-PT-C.xml",
                                          "MA-example-DomicileRegistration-PT-D.xml",
                                          "Sample Domicile Registration Evidence ES.xml" })
      _testReadWrite (DE4AT43Marshaller.domicileRegistrationEvidence (), new File (BASE_PATH + s));
  }

  @Test
  public void testMarriageEvidence ()
  {
    for (final String s : new String [] { "MA-example-Marriage-ES.xml",
                                          "MA-example-Marriage-ES-A.xml",
                                          "MA-example-Marriage-ES-B.xml",
                                          "MA-example-Marriage-ES-C.xml",
                                          "MA-example-Marriage-ES-D.xml",
                                          "MA-example-Marriage-PT.xml",
                                          "MA-example-Marriage-PT-A.xml",
                                          "MA-example-Marriage-PT-B.xml",
                                          "MA-example-Marriage-PT-C.xml",
                                          "MA-example-Marriage-PT-D.xml",
                                          "Sample Marriage Evidence ES.xml" })
      _testReadWrite (DE4AT43Marshaller.marriageEvidence (), new File (BASE_PATH + s));
  }
}
