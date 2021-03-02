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
package eu.de4a.iem.model;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.mock.CommonsTestHelper;

import eu.de4a.iem.jaxb.w3.cv.ac.CoreBusinessType;
import eu.de4a.iem.xml.cv.BusinessMarshaller;

/**
 * Test class for class {@link BusinessPojo}.
 *
 * @author Philip Helger
 */
public final class BusinessPojoTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (BusinessPojoTest.class);

  private static void _testWriteAndRead (@Nonnull final BusinessPojo x)
  {
    assertNotNull (x);

    final CoreBusinessType aObj = x.getAsCoreBusiness ();
    assertNotNull (aObj);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (aObj, aObj.clone ());

    // Write
    final BusinessMarshaller m = new BusinessMarshaller ();
    m.setFormattedOutput (true);
    assertNotNull (m.getAsDocument (aObj));
    if (false)
      LOGGER.info (m.getAsString (aObj));

    // Re-read
    final BusinessPojo y = BusinessPojo.builder (aObj).build ();
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (x, y);
  }

  @Test
  public void testBasic ()
  {
    final BusinessPojo x = BusinessPojo.builder ()
                                       .legalID ("LegalID")
                                       .legalIDSchemeID ("LegalIDType")
                                       .id ("ID")
                                       .idSchemeID ("IDType")
                                       .legalName ("LegalName")
                                       .address (y -> y.fullAddress ("FullAddress")
                                                       .streetName ("StreetName")
                                                       .buildingNumber ("BuildingNumber")
                                                       .town ("Town")
                                                       .postalCode ("PostalCode")
                                                       .countryCode ("CountryCode"))
                                       .build ();
    _testWriteAndRead (x);
  }

  @Test
  public void testMinimum ()
  {
    final BusinessPojo x = BusinessPojo.builder ().build ();
    _testWriteAndRead (x);
  }
}
