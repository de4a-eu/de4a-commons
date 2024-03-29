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
package eu.de4a.iem.cev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.cev.de4a.t41.DE4AT41Marshaller;
import eu.de4a.iem.cev.de4a.t42.DE4AT42Marshaller;
import eu.de4a.iem.cev.de4a.t43.DE4AT43Marshaller;
import eu.de4a.iem.core.DE4ACoreMarshaller;
import eu.de4a.iem.core.IDE4ACanonicalEvidenceType;
import eu.de4a.iem.core.jaxb.common.ResponseExtractMultiEvidenceType;
import eu.de4a.iem.jaxb.t41.higheredu.v2022_06_23.HigherEducationDiplomaType;
import eu.de4a.iem.jaxb.t42.v0_6.LegalEntityType;
import eu.de4a.iem.jaxb.t43.birth.v1_7.BirthEvidenceType;

/**
 * Test class for class {@link DE4ACoreMarshaller}.
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

  @Test
  public void testExtractCanonicalEvidenceT41 ()
  {
    final String sBasePath = "src/test/resources/de4a/";
    final DE4ACoreMarshaller <ResponseExtractMultiEvidenceType> m = DE4ACoreMarshaller.dtResponseTransferEvidenceMarshaller (IDE4ACanonicalEvidenceType.NONE);
    final ResponseExtractMultiEvidenceType aDoc = m.read (new File (sBasePath + "core/t4.1/hed/maria1.xml"));
    assertNotNull (aDoc);
    assertEquals (1, aDoc.getResponseExtractEvidenceItemCount ());
    final Object aCanonicalEvidence = aDoc.getResponseExtractEvidenceItemAtIndex (0).getCanonicalEvidence ().getAny ();
    assertNotNull (aCanonicalEvidence);
    assertTrue (aCanonicalEvidence instanceof Element);

    final HigherEducationDiplomaType aHED = DE4AT41Marshaller.higherEducationDiploma ()
                                                             .read ((Element) aCanonicalEvidence);
    assertNotNull (aHED);
    assertEquals (1, aHED.getIdentifierCount ());
    assertEquals ("0124738434", aHED.getIdentifierAtIndex (0).getValue ());

    assertNotNull (m.getAsBytes (aDoc));
  }

  @Test
  public void testExtractCanonicalEvidenceT42 ()
  {
    final String sBasePath = "src/test/resources/de4a/";
    final DE4ACoreMarshaller <ResponseExtractMultiEvidenceType> m = DE4ACoreMarshaller.deResponseTransferEvidenceMarshaller (EDE4ACanonicalEvidenceType.T42_LEGAL_ENTITY_V06);
    final ResponseExtractMultiEvidenceType aDoc = m.read (new File (sBasePath +
                                                                    "core/t4.2/0.6/DE-response-transfer-evidence-DBA.xml"));
    assertNotNull (aDoc);
    assertEquals (1, aDoc.getResponseExtractEvidenceItemCount ());
    final Object aCanonicalEvidence = aDoc.getResponseExtractEvidenceItemAtIndex (0).getCanonicalEvidence ().getAny ();
    assertNotNull (aCanonicalEvidence);
    assertTrue (aCanonicalEvidence instanceof Element);

    final LegalEntityType aLegalEntity = DE4AT42Marshaller.legalEntity ().read ((Element) aCanonicalEvidence);
    assertNotNull (aLegalEntity);
    assertEquals ("Einzelunternehmen", aLegalEntity.getCompanyType ());

    assertNotNull (m.getAsBytes (aDoc));
  }

  @Test
  public void testExtractCanonicalEvidenceT43 ()
  {
    final String sBasePath = "src/test/resources/de4a/";
    final DE4ACoreMarshaller <ResponseExtractMultiEvidenceType> m = DE4ACoreMarshaller.dtResponseTransferEvidenceMarshaller (EDE4ACanonicalEvidenceType.T43_BIRTH_EVIDENCE_V17);
    final ResponseExtractMultiEvidenceType aDoc = m.read (new File (sBasePath + "core/t4.3/birth/birth1.xml"));
    assertNotNull (aDoc);
    assertEquals (1, aDoc.getResponseExtractEvidenceItemCount ());
    final Object aCanonicalEvidence = aDoc.getResponseExtractEvidenceItemAtIndex (0).getCanonicalEvidence ().getAny ();
    assertNotNull (aCanonicalEvidence);
    assertTrue (aCanonicalEvidence instanceof Element);

    final BirthEvidenceType aCE = DE4AT43Marshaller.birthEvidence ().read ((Element) aCanonicalEvidence);
    assertNotNull (aCE);
    assertEquals ("1234", aCE.getIdentifier ().getIdentifier ().getValue ());

    assertNotNull (m.getAsBytes (aDoc));
  }
}
