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
package eu.de4a.iem.xml.de4a.t42;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;

import com.helger.commons.functional.IFunction;
import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.jaxb.t42.ContactPointType;
import eu.de4a.iem.jaxb.t42.LegalEntityType;

/**
 * Special marshaller for DE4A T4.2 pilot. This class can ONLY ready T4.2 stuff
 * without the surrounding core document.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        JAXB type to be read
 */
public class DE4AT42Marshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  public DE4AT42Marshaller (@Nonnull final Class <JAXBTYPE> aType,
                            @Nonnull final IFunction <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper)
  {
    super (aType, CT42.getAllXSDs (), aJAXBElementWrapper);
    setNamespaceContext (DE4AT42NamespaceContext.getInstance ());
  }

  @Nonnull
  public static DE4AT42Marshaller <LegalEntityType> legalEntity ()
  {
    return new DE4AT42Marshaller <> (LegalEntityType.class, new eu.de4a.iem.jaxb.t42.ObjectFactory ()::createLegalEntity);
  }

  @Nonnull
  public static DE4AT42Marshaller <ContactPointType> contactPoint ()
  {
    return new DE4AT42Marshaller <> (ContactPointType.class, new eu.de4a.iem.jaxb.t42.ObjectFactory ()::createContactPoint);
  }
}
