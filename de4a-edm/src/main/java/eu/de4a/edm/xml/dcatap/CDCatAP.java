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
package eu.de4a.edm.xml.dcatap;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xlink.CXLink;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for handling DCatAP
 *
 * @author Philip Helger
 */
public final class CDCatAP
{
  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CDCatAP.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS = new CommonsArrayList <> (CXML_XSD.getXSDResource (),
                                                                               CCCTS.getXSDResource (),
                                                                               CXLink.getXSDResource (),
                                                                               new ClassPathResource ("schemas/provided/foaf.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/locn.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/skos.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/org.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/rdf.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/prov.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/dcterms.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/CV-DataTypes.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/CV-CommonBasicComponents.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/adms.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/odrl.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/spdx.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/vcard.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/dcat-ap.xsd",
                                                                                                      _getCL ())).getAsUnmodifiable ();

  private CDCatAP ()
  {}
}
