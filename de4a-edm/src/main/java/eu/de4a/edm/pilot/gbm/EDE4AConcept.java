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
package eu.de4a.edm.pilot.gbm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.lang.EnumHelper;

import eu.de4a.edm.model.IConceptName;

/**
 * Predefined DE4A concepts for "registered organization".
 *
 * @author Philip Helger
 */
public enum EDE4AConcept implements IConceptName
{
  ACTIVITY_DESCRIPTION ("ActivityDescription"),
  BIRTH_DATE ("LegalRepresentativeBirthDate"),
  CAPTIAL_TYPE ("CapitalType"),
  COMPANY_CODE ("CompanyCode"),
  COMPANY_NAME ("CompanyName"),
  COMPANY_TYPE ("CompanyType"),
  COUNTRY_NAME ("CountryName"),
  EMAIL_ADDRESS ("EmailAddress"),
  FAMILY_NAME ("LegalRepresentativeFamilyName"),
  FAX_NUMBER ("FaxNumber"),
  FOUNDATION_DATE ("FoundationDate"),
  GIVEN_NAME ("LegalRepresentativeGivenName"),
  HAS_LEGAL_REPRESENTATIVE ("HasLegalRepresentative"),
  LEGAL_STATUS ("LegalStatus"),
  LEGAL_STATUS_EFFECTIVE_DATE ("LegalStatusEffectiveDate"),
  LOCALITY ("Locality"),
  NACE_CODE ("NaceCode"),
  PERSON ("Person"),
  POSTAL_CODE ("PostalCode"),
  REGION ("Region"),
  REGISTERED_ORGANIZATION ("RegisteredOrganization"),
  REGISTRATION_AUTH ("RegistrationAuthority"),
  REGISTRATION_DATE ("RegistrationDate"),
  REGISTRATION_NUMBER ("RegistrationNumber"),
  SOCIAL_SEC_NUMBER ("SSNumber"),
  STREET_ADDRESS ("StreetAddress"),
  TELEPHONE_NUMBER ("TelephoneNumber"),
  VAT_NUMBER ("VATNumber");

  /**
   * The namespace URI to be used for these concepts
   */
  public static final String NAMESPACE_URI = "http://toop.eu/registered-organization";

  private final String m_sID;

  EDE4AConcept (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getConceptNamespaceURI ()
  {
    return NAMESPACE_URI;
  }

  @Nullable
  public static EDE4AConcept getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EDE4AConcept.class, sID);
  }
}
