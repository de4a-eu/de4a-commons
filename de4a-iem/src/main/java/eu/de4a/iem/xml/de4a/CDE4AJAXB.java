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
package eu.de4a.iem.xml.de4a;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.ubl20.CUBL20;
import com.helger.ubl23.CUBL23;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for handling DE4A stuff
 *
 * @author Philip Helger
 */
public final class CDE4AJAXB
{
  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CDE4AJAXB.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS;
  static
  {
    final ICommonsList <ClassPathResource> a = new CommonsArrayList <> ();
    a.add (new ClassPathResource ("schemas/core/external/eidas-LP.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/core/external/eidas-NP.xsd", _getCL ()));
    a.add (CXML_XSD.getXSDResource ());
    a.add (new ClassPathResource ("schemas/core/de4a/common-identity-types.xsd", _getCL ()));
    a.add (CUBL20.XSD_CODELIST_MIME_MEDIA_TYPE_CODE);
    a.add (CUBL23.XSD_UNQUALIFIED_DATA_TYPES);
    a.add (new ClassPathResource ("schemas/core/de4a/common-types.xsd", _getCL ()));
    XSDS = a.getAsUnmodifiable ();
  }

  public static final ClassPathResource XSD_DE_USI = new ClassPathResource ("schemas/core/DE1-USI.xsd", _getCL ());
  public static final ClassPathResource XSD_DR_USI = new ClassPathResource ("schemas/core/DR1-USI.xsd", _getCL ());
  public static final ClassPathResource XSD_DT_USI = new ClassPathResource ("schemas/core/DT1-USI.xsd", _getCL ());
  public static final ClassPathResource XSD_DO_USI = new ClassPathResource ("schemas/core/DO1-USI.xsd", _getCL ());

  public static final ClassPathResource XSD_DR_DE_IM = new ClassPathResource ("schemas/core/DR-DE1-IM.xsd", _getCL ());
  public static final ClassPathResource XSD_DT_DO_IM = new ClassPathResource ("schemas/core/DT-DO1-IM.xsd", _getCL ());

  public static final ClassPathResource XSD_DR_DT_IDK = new ClassPathResource ("schemas/core/DR-DT1-IDK.xsd", _getCL ());

  private CDE4AJAXB ()
  {}
}
