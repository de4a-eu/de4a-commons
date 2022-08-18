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
package eu.de4a.iem.xml.de4a.t43.v1_6b;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.ubl23.CUBL23;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xades141.CXAdES141;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for handling DE4A T4.3 v1.6 pilot stuff
 *
 * @author Philip Helger
 */
public final class CT43
{
  public static final String NS_URI_BIRTH_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::BirthEvidence:v1.6";
  public static final String NS_URI_DOMICILE_REGISTRATION_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::DomicileRegistrationEvidence:v1.6";
  public static final String NS_URI_MARRIAGE_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::MarriageEvidence:v1.6";

  private CT43 ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CT43.class.getClassLoader ();
  }

  @Nonnull
  @ReturnsMutableCopy
  private static ICommonsList <ClassPathResource> _getBaseXSDs ()
  {
    final ICommonsList <ClassPathResource> a = new CommonsArrayList <> ();
    a.add (CXML_XSD.getXSDResource ());
    // XMLDsig + Xades
    a.addAll (CXAdES141.getAllXSDResources ());
    a.add (CCCTS.getXSDResource ());
    a.add (CUBL23.XSD_UNQUALIFIED_DATA_TYPES);
    a.add (CUBL23.XSD_QUALIFIED_DATA_TYPES);
    a.add (CUBL23.XSD_COMMON_BASIC_COMPONENTS);
    if (false)
      a.add (CUBL23.XSD_EXTENSION_CONTENT_DATA_TYPE);
    a.add (CUBL23.XSD_COMMON_EXTENSION_COMPONENTS);
    a.add (new ClassPathResource ("schemas/it1/t4.3/CoreVocabularyBasicComponents-v1.00.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/it1/t4.3/CoreBusiness-v1.00.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/it1/t4.3/CoreLocation-v1.00.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/it1/t4.3/CorePerson-v1.00.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/it1/t4.3/CoreVocabularyAggregateComponents-v1.00.xsd", _getCL ()));
    return a;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllBirthEvidenceXSDs ()
  {
    final ICommonsList <ClassPathResource> a = _getBaseXSDs ();
    a.add (new ClassPathResource ("schemas/it1/t4.3/v1.6b/birthEvidence-1.6.xsd", _getCL ()));
    return a;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllDomicileRegistrationEvidenceXSDs ()
  {
    final ICommonsList <ClassPathResource> a = _getBaseXSDs ();
    a.add (new ClassPathResource ("schemas/it1/t4.3/v1.6b/domicileRegistrationEvidence-1.6.xsd", _getCL ()));
    return a;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllMarriageEvidenceXSDs ()
  {
    final ICommonsList <ClassPathResource> a = _getBaseXSDs ();
    a.add (new ClassPathResource ("schemas/it1/t4.3/v1.6b/marriageEvidence-1.6.xsd", _getCL ()));
    return a;
  }
}
