/*
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
package eu.de4a.iem.cev;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.core.DE4ACoreMarshaller;

/**
 * Test class for class {@link DE4AMarshaller}.
 *
 * @author Philip Helger
 */
public final class DE4ACoreMarshallerFuncTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4ACoreMarshallerFuncTest.class);

  private static <T> void _testReadWrite (@Nonnull final GenericJAXBMarshaller <T> aMarshaller,
                                          @Nonnull final File aFile)
  {
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("Reading Core+Canonical file " + aFile.getName ());

    assertTrue ("Test file does not exists " + aFile.getAbsolutePath (), aFile.exists ());

    if (false)
    {
      aMarshaller.readExceptionCallbacks ().set (ex -> LOGGER.error ("Read error", ex));
      aMarshaller.writeExceptionCallbacks ().set (ex -> LOGGER.error ("Write error", ex));
    }

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
  public void testDBA ()
  {
    final String sBasePath = "src/test/resources/de4a/";
    _testReadWrite (DE4ACoreMarshaller.deResponseTransferEvidenceMarshaller (EDE4ACanonicalEvidenceType.T42_LEGAL_ENTITY_V06),
                    new File (sBasePath + "core/t4.2/0.6/DE-response-transfer-evidence-DBA.xml"));
    _testReadWrite (DE4ACoreMarshaller.dtResponseTransferEvidenceMarshaller (EDE4ACanonicalEvidenceType.T42_LEGAL_ENTITY_V06),
                    new File (sBasePath + "core/t4.2/0.6/DT-response-transfer-evidence-DBA.xml"));
  }
}
