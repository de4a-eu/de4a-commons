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

/**
 * Test class for class {@link BusinessPojo}.
 *
 * @author Philip Helger
 */
public final class BusinessPojoTest
{
  private static void _testWriteAndRead (@Nonnull final BusinessPojo x)
  {
    assertNotNull (x);
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
