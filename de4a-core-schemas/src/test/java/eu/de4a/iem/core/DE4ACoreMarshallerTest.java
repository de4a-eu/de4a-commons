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
package eu.de4a.iem.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.wrapper.Wrapper;
import com.helger.jaxb.GenericJAXBMarshaller;

/**
 * Test class for class {@link DE4ACoreMarshaller}.
 *
 * @author Philip Helger
 */
public final class DE4ACoreMarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4ACoreMarshallerTest.class);
  private static final String BASE_PATH = "src/test/resources/de4a/";

  @SuppressWarnings ("unused")
  private static <T> void _receiveViaHttp (@Nonnull final GenericJAXBMarshaller <T> aMarshaller,
                                           @Nonnull final File aFile) throws Exception
  {
    final Wrapper <Exception> aExWrapper = new Wrapper <> ();
    aMarshaller.readExceptionCallbacks ().removeAll ();
    aMarshaller.readExceptionCallbacks ().add (ex -> aExWrapper.set (ex));
    aMarshaller.readExceptionCallbacks ().add (ex -> LOGGER.error ("Failed to parse XML", ex));

    final T aRead = aMarshaller.read (aFile);
    if (aRead == null)
    {
      if (aExWrapper.isSet ())
        throw aExWrapper.get ();
      throw new Exception ("HTTP 400");
    }
  }

  private static <T> void _testReadWrite (@Nonnull final GenericJAXBMarshaller <T> aMarshaller,
                                          @Nonnull final File aFile)
  {
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
  public void testDE ()
  {
    _testReadWrite (DE4ACoreMarshaller.deResponseTransferEvidenceMarshaller (IDE4ACanonicalEvidenceType.NONE),
                    new File (BASE_PATH + "core/DE-response-transfer-evidence.xml"));
    _testReadWrite (DE4ACoreMarshaller.deUSIRedirectUserMarshaller (),
                    new File (BASE_PATH + "core/DE-usi-redirect.xml"));
    _testReadWrite (DE4ACoreMarshaller.deEventNotificationMarshaller (),
                    new File (BASE_PATH + "core/DE-event-notification.xml"));
  }
}
