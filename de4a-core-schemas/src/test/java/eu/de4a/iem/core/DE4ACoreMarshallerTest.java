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
package eu.de4a.iem.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private static <T> void _testReadWrite (@Nonnull final GenericJAXBMarshaller <T> aMarshaller,
                                          @Nonnull final File aFile)
  {
    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("Reading Core file " + aFile.getName ());

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
  public void testDE ()
  {
    _testReadWrite (DE4ACoreMarshaller.deResponseTransferEvidenceMarshaller (IDE4ACanonicalEvidenceType.NONE),
                    new File (BASE_PATH + "core/DE-response-transfer-evidence.xml"));
    _testReadWrite (DE4ACoreMarshaller.deUSIRedirectUserMarshaller (),
                    new File (BASE_PATH + "core/DE-usi-redirect-user.xml"));
    _testReadWrite (DE4ACoreMarshaller.deEventNotificationMarshaller (),
                    new File (BASE_PATH + "core/DE-event-notification.xml"));
  }

  @Test
  public void testDO ()
  {
    _testReadWrite (DE4ACoreMarshaller.doRequestExtractMultiEvidenceIMMarshaller (),
                    new File (BASE_PATH + "core/DO-request-extract-multi-evidence-im.xml"));
    _testReadWrite (DE4ACoreMarshaller.doRequestExtractMultiEvidenceLUMarshaller (),
                    new File (BASE_PATH + "core/DO-request-extract-multi-evidence-lu.xml"));
    _testReadWrite (DE4ACoreMarshaller.doRequestExtractMultiEvidenceUSIMarshaller (),
                    new File (BASE_PATH + "core/DO-request-extract-multi-evidence-usi.xml"));
    _testReadWrite (DE4ACoreMarshaller.doRequestEventSubscriptionMarshaller (),
                    new File (BASE_PATH + "core/DO-request-event-subscription.xml"));
  }

  @Test
  public void testDR ()
  {
    _testReadWrite (DE4ACoreMarshaller.drRequestTransferEvidenceIMMarshaller (),
                    new File (BASE_PATH + "core/DR-request-transfer-evidence-im.xml"));
    _testReadWrite (DE4ACoreMarshaller.drRequestTransferEvidenceLUMarshaller (),
                    new File (BASE_PATH + "core/DR-request-transfer-evidence-lu.xml"));
    _testReadWrite (DE4ACoreMarshaller.drRequestTransferEvidenceUSIMarshaller (),
                    new File (BASE_PATH + "core/DR-request-transfer-evidence-usi.xml"));
    _testReadWrite (DE4ACoreMarshaller.drRequestEventSubscriptionMarshaller (),
                    new File (BASE_PATH + "core/DR-request-event-subscription.xml"));
  }

  @Test
  public void testDT ()
  {
    _testReadWrite (DE4ACoreMarshaller.dtUSIRedirectUserMarshaller (),
                    new File (BASE_PATH + "core/DT-usi-redirect-user.xml"));
    _testReadWrite (DE4ACoreMarshaller.dtResponseTransferEvidenceMarshaller (IDE4ACanonicalEvidenceType.NONE),
                    new File (BASE_PATH + "core/DT-response-transfer-evidence.xml"));
    _testReadWrite (DE4ACoreMarshaller.dtResponseEventSubscriptionMarshaller (),
                    new File (BASE_PATH + "core/DT-response-event-subscription.xml"));
    _testReadWrite (DE4ACoreMarshaller.dtEventNotificationMarshaller (),
                    new File (BASE_PATH + "core/DT-event-notification.xml"));
  }
}
