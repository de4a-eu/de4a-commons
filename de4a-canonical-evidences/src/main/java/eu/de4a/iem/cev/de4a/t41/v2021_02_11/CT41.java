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
package eu.de4a.iem.cev.de4a.t41.v2021_02_11;

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
 * Constants for handling DE4A T4.1 v2021-02-11 pilot stuff
 *
 * @author Philip Helger
 */
public final class CT41
{
  public static final String NAMESPACE_URI = "https://data.europe.eu/de4a/model/studying-abroad/";

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
    if (false)
    {
      // No includes - just imports
      a.add (new ClassPathResource ("schemas/t4.1/uc1/w3c_verifiableCredentials.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/imported/edci_esco_individuals.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/imported/edci_esco_eqf.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/imported/edci_esco_iscedf2013.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/imported/edci_mdr_humansex.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/imported/edci_mdr_currency.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/imported/edci_esco_nuts2016.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/imported/edci_mdr_languages.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/imported/edci_mdr_countries.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_individuals.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_simpleContentTypes.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_complexContentTypes.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_associationobject.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_contact.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_learningOpportunity.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_accreditation.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_agent.xsd", _getCL ()));
      a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_commonTypes.xsd", _getCL ()));
    }
    a.add (new ClassPathResource ("schemas/t4.1/uc1/edci_credentialTypes.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/t4.1/uc1/SA-UC1-11-02-2021.xsd", _getCL ()));
    return a;
  }
}
