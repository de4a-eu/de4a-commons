/*
 * Copyright (C) 2023, Partners of the EU funded DE4A project consortium
 *   (https://www.de4a.eu/consortium), under Grant Agreement No.870635
 * Author: Austrian Federal Computing Center (BRZ)
 *
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
package eu.de4a.iem.cev.de4a.t42;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jaxb.GenericJAXBMarshaller;

/**
 * Test class for class {@link DE4AT42Marshaller}.
 *
 * @author Philip Helger
 */
public final class DE4AT42MarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AT42MarshallerTest.class);

  private static <T> void _testReadWrite (@Nonnull final GenericJAXBMarshaller <T> aMarshaller,
                                          @Nonnull final File aFile)
  {
    assertTrue ("Test file does not exists " + aFile.getAbsolutePath (), aFile.exists ());

    final T aRead = aMarshaller.read (aFile);
    assertNotNull ("Failed to read " + aFile.getAbsolutePath (), aRead);

    final byte [] aBytes = aMarshaller.getAsBytes (aRead);
    assertNotNull ("Failed to re-write " + aFile.getAbsolutePath (), aBytes);

    if (false)
    {
      aMarshaller.setFormattedOutput (true);
      if (LOGGER.isInfoEnabled ())
        LOGGER.info ("Created version of '" + aFile.getName () + "'\n" + aMarshaller.getAsString (aRead));
    }
  }

  @Test
  public void testLegalEntity ()
  {
    final String sBasePath = "src/test/resources/de4a/t4.2/v0.6/";
    _testReadWrite (DE4AT42Marshaller.legalEntity (), new File (sBasePath + "Sample Company Registration AT.xml"));
    _testReadWrite (DE4AT42Marshaller.legalEntity (), new File (sBasePath + "Sample Company Registration NL KVK.xml"));
    _testReadWrite (DE4AT42Marshaller.legalEntity (), new File (sBasePath + "Sample Company Registration RO ONRC.xml"));
    _testReadWrite (DE4AT42Marshaller.legalEntity (), new File (sBasePath + "Sample Company Registration SE.xml"));
  }
}
