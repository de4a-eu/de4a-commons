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
package eu.de4a.iem.xml.de4a.t41.v2021_04_13;

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
 * Constants for handling DE4A T4.1 v2021-04-13 pilot stuff
 *
 * @author Philip Helger
 */
public final class CT41
{
  public static final String NAMESPACE_URI = "urn:eu-de4a:xsd:CanonicalEvidenceType::HigherEducationEvidence:v1.0";

  private CT41 ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CT41.class.getClassLoader ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDs ()
  {
    final ICommonsList <ClassPathResource> a = new CommonsArrayList <> ();
    a.add (CXML_XSD.getXSDResource ());
    // XMLDsig + Xades
    a.addAll (CXAdES132.getAllXSDResources ());
    a.add (CCCTS.getXSDResource ());
    a.add (CUBL23.XSD_UNQUALIFIED_DATA_TYPES);
    a.add (CUBL23.XSD_QUALIFIED_DATA_TYPES);
    a.add (CUBL23.XSD_COMMON_BASIC_COMPONENTS);
    a.add (new ClassPathResource ("schemas/it1/pilot-shared/CoreVocabularies-BasicComponents-1.1.xsd", _getCL ()));
    final String sT41 = "schemas/it1/t4.1/uc1/";
    if (false)
    {
      // No includes - just imports
      a.add (new ClassPathResource (sT41 + "w3c_verifiableCredentials.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "imported/edci_esco_individuals.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "imported/edci_esco_eqf.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "imported/edci_esco_iscedf2013.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "imported/edci_mdr_humansex.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "imported/edci_mdr_currency.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "imported/edci_esco_nuts2016.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "imported/edci_mdr_languages.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "imported/edci_mdr_countries.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "edci_individuals.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "edci_simpleContentTypes.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "edci_complexContentTypes.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "edci_associationobject.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "edci_contact.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "edci_learningOpportunity.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "edci_accreditation.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "edci_agent.xsd", _getCL ()));
      a.add (new ClassPathResource (sT41 + "edci_commonTypes.xsd", _getCL ()));
    }
    a.add (new ClassPathResource (sT41 + "edci_credentialTypes.xsd", _getCL ()));
    a.add (new ClassPathResource (sT41 + "SA-UC1-13-04-2021.xsd", _getCL ()));
    return a;
  }
}
