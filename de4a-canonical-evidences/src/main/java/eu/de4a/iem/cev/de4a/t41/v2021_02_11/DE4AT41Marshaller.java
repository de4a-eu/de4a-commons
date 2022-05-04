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

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;

import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.jaxb.t41.uc1.v2021_02_11.HigherEducationEvidenceType;

/**
 * Special marshaller for canonical evidences of the DE4A T4.1 v2021-02-11
 * pilot. This class can ONLY ready T4.1 stuff without the surrounding core
 * document.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        JAXB type to be read
 */
public class DE4AT41Marshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  public DE4AT41Marshaller (@Nonnull final Class <JAXBTYPE> aType,
                            @Nonnull final Function <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper)
  {
    super (aType, CT41.getAllXSDs (), aJAXBElementWrapper);
    setNamespaceContext (DE4AT41NamespaceContext.getInstance ());
  }

  @Nonnull
  public static DE4AT41Marshaller <HigherEducationEvidenceType> higherEducationEvidence ()
  {
    return new DE4AT41Marshaller <> (HigherEducationEvidenceType.class,
                                     new eu.de4a.iem.jaxb.t41.uc1.v2021_02_11.ObjectFactory ()::createHigherEducationEvidence);
  }
}
