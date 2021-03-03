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
package eu.de4a.iem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.Month;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.mock.CommonsTestHelper;
import com.helger.schematron.svrl.AbstractSVRLMessage;

import eu.de4a.iem.model.BusinessPojo;
import eu.de4a.iem.model.EDE4AGenderCode;
import eu.de4a.iem.model.EDE4AIdentifierType;
import eu.de4a.iem.model.EDE4AResponseOptionType;
import eu.de4a.iem.model.PersonPojo;
import eu.de4a.iem.schematron.SchematronBusinessRules2Validator;
import eu.de4a.iem.schematron.SchematronEDM2Validator;

/**
 * Test class for class {@link EDMRequest}.
 *
 * @author Philip Helger
 */
public final class EDMRequestTest
{
  private static void _testWriteAndRead (@Nonnull final EDMRequest aReq)
  {
    assertNotNull (aReq);

    // Write
    final byte [] aBytes = aReq.getWriter ().getAsBytes ();
    assertNotNull (aBytes);

    // Re-read
    final EDMRequest aReq2 = EDMRequest.reader ().read (aBytes);

    // Compare with original
    assertEquals (aReq, aReq2);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aReq, aReq2);

    {
      // Schematron validation
      final Document aDoc = aReq.getWriter ().getAsDocument ();
      assertNotNull (aDoc);
      // Schematron 1
      ICommonsList <AbstractSVRLMessage> aMsgs = new SchematronEDM2Validator ().validateDocument (aDoc);
      assertTrue (aMsgs.toString (), aMsgs.isEmpty ());

      // Schematron 2
      aMsgs = new SchematronBusinessRules2Validator ().validateDocument (aDoc);
      assertTrue (aMsgs.toString (), aMsgs.isEmpty ());
    }
  }

  @Nonnull
  private static <T extends EDMRequest.AbstractBuilder <T>> T _req (@Nonnull final T aBuilder)
  {
    return aBuilder.specificationIdentifier (CDE4AEDM.SPECIFICATION_IDENTIFIER_DE4A_EDM_V01)
                   .responseOption (EDE4AResponseOptionType.INLINE)
                   .randomID ()
                   .issueDateTimeNow ()
                   .procedure (Locale.US, "GBM Procedure")
                   .dataConsumer (x -> x.address (y -> y.town ("MyTown")
                                                        .streetName ("MyStreet")
                                                        .buildingNumber ("22")
                                                        .countryCode ("GR")
                                                        .fullAddress ("MyStreet 22, 11134, MyTown, GR")
                                                        .postalCode ("11134"))
                                        .name ("DC NAME")
                                        .id ("1234")
                                        .idSchemeID (EDE4AIdentifierType.VATREGISTRATION))
                   .authorizedRepresentative (x -> x.address (y -> y.town ("MyTown")
                                                                    .streetName ("MyStreet")
                                                                    .buildingNumber ("22")
                                                                    .countryCode ("GR")
                                                                    .fullAddress ("MyStreet 22, 11134, MyTown, GR")
                                                                    .postalCode ("11134"))
                                                    .birthDate (PDTFactory.createLocalDate (1994, Month.FEBRUARY, 1))
                                                    .birthTown ("ATown")
                                                    .birthName ("John Doe")
                                                    .familyName ("Doe")
                                                    .genderCode (EDE4AGenderCode.M)
                                                    .givenName ("John")
                                                    .id ("LALALA")
                                                    .idSchemeID (EDE4AIdentifierType.EIDAS))
                   .datasetIdentifier ("IdentifierForDatasets")
                   .consentToken ("AAABBB");
  }

  @Nonnull
  private static EDMRequest.BuilderDocumentByID _reqDocumentByID ()
  {
    return _req (EDMRequest.builderDocumentByID ()).documentID (UUID.randomUUID ().toString ());
  }

  @Nonnull
  private static PersonPojo.Builder _np ()
  {
    return PersonPojo.builder ()
                     .address (x -> x.town ("MyTown")
                                     .streetName ("MyStreet")
                                     .buildingNumber ("22")
                                     .countryCode ("GR")
                                     .fullAddress ("MyStreet 22, 11134, MyTown, GR")
                                     .postalCode ("11134"))
                     .birthDate (PDTFactory.createLocalDate (1994, Month.FEBRUARY, 1))
                     .birthTown ("ATown")
                     .birthName ("John Doe")
                     .familyName ("Doe")
                     .genderCode (EDE4AGenderCode.M)
                     .givenName ("John")
                     .id ("LALALA")
                     .idSchemeID (EDE4AIdentifierType.EIDAS);
  }

  @Nonnull
  private static BusinessPojo.Builder _lp ()
  {
    return BusinessPojo.builder ()
                       .address (x -> x.town ("MyTown")
                                       .streetName ("MyStreet")
                                       .buildingNumber ("22")
                                       .countryCode ("GR")
                                       .fullAddress ("MyStreet 22, 11134, MyTown, GR")
                                       .postalCode ("11134"))
                       .legalID ("DE/AT/12345")
                       .legalIDSchemeID (EDE4AIdentifierType.EIDAS)
                       .legalName ("NiarTsiou")
                       .id ("anID")
                       .idSchemeID (EDE4AIdentifierType.VATREGISTRATION);
  }

  @Test
  public void createEDMDocumentGetByIDRequestLP ()
  {
    final EDMRequest aRequest = _reqDocumentByID ().dataSubject (_lp ()).build ();
    _testWriteAndRead (aRequest);
  }

  @Test
  public void createEDMDocumentGetByIDRequestNP ()
  {
    final EDMRequest aRequest = _reqDocumentByID ().dataSubject (_np ()).build ();
    _testWriteAndRead (aRequest);
  }

  @Test
  public void createEDMDocumentGetByIDRequestNoDS ()
  {
    // No DataSubject is okay when querying by ID
    final EDMRequest aRequest = _reqDocumentByID ().build ();
    _testWriteAndRead (aRequest);
  }

  @Test
  public void testReadAndWriteExampleFiles ()
  {
    EDMRequest aRequest;

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("toop/Document Request_LP.xml"));
    _testWriteAndRead (aRequest);

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("toop/Document Request_NP.xml"));
    _testWriteAndRead (aRequest);

    if (false)
    {
      aRequest = EDMRequest.reader ().read (new ClassPathResource ("toop/request/request1.xml"));
      _testWriteAndRead (aRequest);
    }
  }

  @Test
  public void testBadCases ()
  {
    EDMRequest aRequest = EDMRequest.reader ().read (new ClassPathResource ("toop/Bogus.xml"));
    assertNull (aRequest);

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("toop/Document Response.xml"));
    assertNull (aRequest);

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("toop/Error Response 1.xml"));
    assertNull (aRequest);
  }
}