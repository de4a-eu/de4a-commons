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

import java.util.UUID;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.w3c.dom.Document;

import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.mock.CommonsTestHelper;
import com.helger.regrep.ERegRepResponseStatus;
import com.helger.schematron.svrl.AbstractSVRLMessage;

import eu.de4a.iem.CDE4AEDM;
import eu.de4a.iem.EDMResponse;
import eu.de4a.iem.model.EDE4AIdentifierType;
import eu.de4a.iem.schematron.SchematronBusinessRules2Validator;
import eu.de4a.iem.schematron.SchematronEDM2Validator;

/**
 * Test class for class {@link EDMResponse}.
 *
 * @author Philip Helger
 */
public final class EDMResponseTest
{
  private static void _testWriteAndRead (@Nonnull final EDMResponse aResp)
  {
    assertNotNull (aResp);

    // Write
    final byte [] aBytes = aResp.getWriter ().getAsBytes ();
    assertNotNull (aBytes);

    // Re-read
    final EDMResponse aResp2 = EDMResponse.reader ().read (aBytes);
    // Compare with original
    assertEquals (aResp, aResp2);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aResp, aResp2);

    {
      // Schematron validation
      final Document aDoc = aResp.getWriter ().getAsDocument ();
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
  private static <T extends EDMResponse.AbstractBuilder <T>> T _resp (@Nonnull final T aBuilder)
  {
    return aBuilder.requestID (UUID.randomUUID ())
                   .issueDateTimeNow ()
                   .specificationIdentifier (CDE4AEDM.SPECIFICATION_IDENTIFIER_DE4A_EDM_V01)
                   .dataProvider (x -> x.address (y -> y.town ("MyTown")
                                                        .streetName ("MyStreet")
                                                        .buildingNumber ("22")
                                                        .countryCode ("GR")
                                                        .fullAddress ("MyStreet 22, 11134, MyTown, GR")
                                                        .postalCode ("11134"))
                                        .name ("DP NAME")
                                        .id ("1234")
                                        .idSchemeID (EDE4AIdentifierType.EIDAS))
                   .responseStatus (ERegRepResponseStatus.SUCCESS);
  }

  @Nonnull
  private static EDMResponse.BuilderDocument _respDocument ()
  {
    return _resp (EDMResponse.builderDocument ()).addResponseObject (x -> x.repositoryItemRef (y -> y.title ("Evidence.pdf")
                                                                                                     .link ("https://www.example.com/evidence.pdf")));
  }

  @Nonnull
  private static EDMResponse.BuilderDocumentReference _respDocumentRef ()
  {
    return _resp (EDMResponse.builderDocumentReference ()).addResponseObject (x -> x.randomRegistryObjectID ())
                                                          .addResponseObject (x -> x.randomRegistryObjectID ());
  }

  @Test
  public void createDocumentResponse ()
  {
    final EDMResponse aResp = _respDocument ().build ();
    _testWriteAndRead (aResp);
  }

  @Test
  public void createDocumentRefResponse ()
  {
    final EDMResponse aResp = _respDocumentRef ().build ();
    _testWriteAndRead (aResp);
  }

  @Test
  public void testReadAndWriteExampleFiles ()
  {
    EDMResponse aResponse;

    aResponse = EDMResponse.reader ().read (new ClassPathResource ("toop/Document Response.xml"));
    _testWriteAndRead (aResponse);
  }

  @Test
  public void testBadCases ()
  {
    EDMResponse aResponse = EDMResponse.reader ().read (new ClassPathResource ("toop/Bogus.xml"));
    assertNull (aResponse);

    aResponse = EDMResponse.reader ().read (new ClassPathResource ("toop/Document Request_LP.xml"));
    assertNull (aResponse);

    aResponse = EDMResponse.reader ().read (new ClassPathResource ("toop/Error Response 1.xml"));
    assertNull (aResponse);
  }
}
