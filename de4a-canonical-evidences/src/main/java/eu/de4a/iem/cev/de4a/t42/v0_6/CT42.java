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
package eu.de4a.iem.cev.de4a.t42.v0_6;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.ubl23.CUBL23;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for handling DE4A T4.2 v0.6 pilot stuff
 *
 * @author Philip Helger
 */
public final class CT42
{
  public static final String NAMESPACE_URI = "urn:eu-de4a:xsd:CanonicalEvidenceType::CompanyRegistration:v0.6";

  private CT42 ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CT42.class.getClassLoader ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDs ()
  {
    final ICommonsList <ClassPathResource> a = new CommonsArrayList <> ();
    a.add (CXML_XSD.getXSDResource ());
    a.add (CCCTS.getXSDResource ());
    a.add (CUBL23.XSD_UNQUALIFIED_DATA_TYPES);
    a.add (new ClassPathResource ("schemas/pilot-shared/CoreVocabularies-BasicComponents-1.1.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/t4.2/doing_Business_abroad_XSD_v0.6 draft.xsd", _getCL ()));
    return a;
  }
}
