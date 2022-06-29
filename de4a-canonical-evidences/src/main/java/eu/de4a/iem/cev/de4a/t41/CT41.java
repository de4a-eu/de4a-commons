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
package eu.de4a.iem.cev.de4a.t41;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.ubl23.CUBL23;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xades132.CXAdES132;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for handling DE4A T4.1 pilot stuff
 *
 * @author Philip Helger
 */
public final class CT41
{
  public static final String NS_URI_HIGHER_EDUCATION_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::HigherEducationEvidence:v1.0";
  public static final String NS_URI_SECONDARY_EDUCATION_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::SecondaryEducationEvidence:v1.0";
  public static final String NS_URI_DISABILITY_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::DisabilityEvidence:v1.0";
  public static final String NS_URI_LARGE_FAMILY_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::LargeFamilyEvidence:v1.0";

  private CT41 ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CT41.class.getClassLoader ();
  }

  private static void _addEDCI (@Nonnull final ICommonsList <ClassPathResource> aList)
  {
    aList.add (CXML_XSD.getXSDResource ());
    // XMLDsig + Xades
    aList.addAll (CXAdES132.getAllXSDResources ());
    aList.add (CCCTS.getXSDResource ());
    aList.add (CUBL23.XSD_UNQUALIFIED_DATA_TYPES);
    aList.add (CUBL23.XSD_QUALIFIED_DATA_TYPES);
    aList.add (CUBL23.XSD_COMMON_BASIC_COMPONENTS);
    if (false)
    {
      // No includes - just imports
      aList.add (new ClassPathResource ("schemas/t4.1/w3c_verifiableCredentials.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/imported/edci_esco_individuals.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/imported/edci_esco_eqf.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/imported/edci_esco_iscedf2013.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/imported/edci_mdr_humansex.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/imported/edci_mdr_currency.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/imported/edci_esco_nuts2016.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/imported/edci_mdr_languages.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/imported/edci_mdr_countries.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/edci_individuals.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/edci_simpleContentTypes.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/edci_complexContentTypes.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/edci_associationobject.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/edci_contact.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/edci_learningOpportunity.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/edci_accreditation.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/edci_agent.xsd", _getCL ()));
      aList.add (new ClassPathResource ("schemas/t4.1/edci_commonTypes.xsd", _getCL ()));
    }
    aList.add (new ClassPathResource ("schemas/t4.1/edci_credentialTypes.xsd", _getCL ()));
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDsHigherEducationDiploma ()
  {
    final ICommonsList <ClassPathResource> aList = new CommonsArrayList <> ();
    _addEDCI (aList);
    aList.add (new ClassPathResource ("schemas/t4.1/SA-UC1-HigherEducationDiplomaType-23-06-2022.xsd", _getCL ()));
    return aList;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDsSecondaryEducationDiploma ()
  {
    final ICommonsList <ClassPathResource> aList = new CommonsArrayList <> ();
    _addEDCI (aList);
    aList.add (new ClassPathResource ("schemas/t4.1/SA-UC1-SecondaryEducationEvidenceType-12-05-2022.xsd", _getCL ()));
    return aList;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDsDisability ()
  {
    final ICommonsList <ClassPathResource> aList = new CommonsArrayList <> ();
    _addEDCI (aList);
    aList.add (new ClassPathResource ("schemas/t4.1/SA-UC2-DisabilityEvidenceType-12-05-2022.xsd", _getCL ()));
    return aList;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDsLargeFamily ()
  {
    final ICommonsList <ClassPathResource> aList = new CommonsArrayList <> ();
    _addEDCI (aList);
    aList.add (new ClassPathResource ("schemas/t4.1/SA-UC2-LargeFamilyEvidenceType-12-05-2022.xsd", _getCL ()));
    return aList;
  }
}
