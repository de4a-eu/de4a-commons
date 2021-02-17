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
package eu.de4a.edm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Month;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mock.CommonsTestHelper;
import com.helger.schematron.svrl.AbstractSVRLMessage;

import eu.de4a.edm.jaxb.cccev.CCCEVRequirementType;
import eu.de4a.edm.model.BusinessPojo;
import eu.de4a.edm.model.EDE4ADistributionFormat;
import eu.de4a.edm.model.EDE4AGenderCode;
import eu.de4a.edm.model.EDE4AIdentifierType;
import eu.de4a.edm.model.EDE4AResponseOptionType;
import eu.de4a.edm.model.PersonPojo;
import eu.de4a.edm.pilot.gbm.EDE4AConcept;
import eu.de4a.edm.request.EDMRequestPayloadConcepts;
import eu.de4a.edm.schematron.SchematronBusinessRules2Validator;
import eu.de4a.edm.schematron.SchematronEDM2Validator;

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
                   .addFullfillingRequirement (new CCCEVRequirementType ())
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
  private static EDMRequest.BuilderConcept _reqConcept ()
  {
    return _req (EDMRequest.builderConcept ()).concept (x -> x.randomID ()
                                                              .name (EDE4AConcept.COMPANY_TYPE)
                                                              .addChild (y -> y.randomID ().name (EDE4AConcept.COMPANY_NAME))
                                                              .addChild (y -> y.randomID ().name (EDE4AConcept.COMPANY_CODE))
                                                              .addChild (y -> y.randomID ().name (EDE4AConcept.COMPANY_TYPE)));
  }

  @Nonnull
  private static EDMRequest.BuilderDocumentsByDistribution _reqDocument ()
  {
    return _req (EDMRequest.builderDocumentsByDistribution ()).distribution (x -> x.format (EDE4ADistributionFormat.STRUCTURED)
                                                                                   .mediaType (CMimeType.APPLICATION_PDF));
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
  public void createEDMConceptRequestLP ()
  {
    final EDMRequest aRequest = _reqConcept ().dataSubject (_lp ()).build ();
    _testWriteAndRead (aRequest);
  }

  @Test
  public void createEDMConceptRequestNP ()
  {
    final EDMRequest aRequest = _reqConcept ().dataSubject (_np ()).build ();
    _testWriteAndRead (aRequest);
  }

  @Test
  public void createEDMConceptRequestNoDS ()
  {
    // DataSubject is required
    try
    {
      _reqConcept ().build ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {
      // expected
    }
  }

  @Test
  public void createEDMDocumentRequestLP ()
  {
    final EDMRequest aRequest = _reqDocument ().dataSubject (_lp ()).build ();
    _testWriteAndRead (aRequest);
  }

  @Test
  public void createEDMDocumentRequestNP ()
  {
    final EDMRequest aRequest = _reqDocument ().dataSubject (_np ()).build ();
    _testWriteAndRead (aRequest);
  }

  @Test
  public void createEDMDocumentRequestNoDS ()
  {
    // DataSubject is required
    try
    {
      _reqDocument ().build ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {
      // expected
    }
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
  public void createEDMDocumentRefRequestNP ()
  {
    final EDMRequest aRequest = _reqDocument ().dataSubject (_np ()).responseOption (EDE4AResponseOptionType.REFERENCE).build ();
    _testWriteAndRead (aRequest);
  }

  @Test
  public void testReadAndWriteExampleFiles ()
  {
    EDMRequest aRequest = EDMRequest.reader ().read (new ClassPathResource ("Concept Request_LP.xml"));
    _testWriteAndRead (aRequest);

    assertTrue (aRequest.getPayloadProvider () instanceof EDMRequestPayloadConcepts);
    final EDMRequestPayloadConcepts aRPC = (EDMRequestPayloadConcepts) aRequest.getPayloadProvider ();
    assertTrue (aRPC.concepts ()
                    .containsAny (x -> x.getAllChildren ()
                                        .containsAny (y -> y.getID ().equals ("ConceptID-2") &&
                                                           y.getName ().getNamespaceURI ().equals (EDE4AConcept.NAMESPACE_URI) &&
                                                           y.getName ().getLocalPart ().equals ("Concept-Name-2"))));

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("Concept Request_NP.xml"));
    _testWriteAndRead (aRequest);

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("Document Request_LP.xml"));
    _testWriteAndRead (aRequest);

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("Document Request_NP.xml"));
    _testWriteAndRead (aRequest);

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("request/edm-jonas1.xml"));
    _testWriteAndRead (aRequest);

    if (false)
    {
      aRequest = EDMRequest.reader ().read (new ClassPathResource ("request/request1.xml"));
      _testWriteAndRead (aRequest);
    }
  }

  @Test
  public void testBadCases ()
  {
    EDMRequest aRequest = EDMRequest.reader ().read (new ClassPathResource ("Bogus.xml"));
    assertNull (aRequest);

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("Concept Response.xml"));
    assertNull (aRequest);

    aRequest = EDMRequest.reader ().read (new ClassPathResource ("Error Response 1.xml"));
    assertNull (aRequest);
  }
}
