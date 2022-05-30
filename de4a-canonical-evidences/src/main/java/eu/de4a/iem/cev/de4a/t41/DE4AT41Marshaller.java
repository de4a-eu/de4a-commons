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
package eu.de4a.iem.cev.de4a.t41;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.NamespaceContext;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.jaxb.t41.uc1.hed.v2021_04_13.HigherEducationDiplomaType;
import eu.de4a.iem.jaxb.t41.uc1.sed.v2022_05_12.SecondaryEducationDiplomaType;
import eu.de4a.iem.jaxb.t41.uc2.de.v2022_05_12.DisabilityEvidenceType;
import eu.de4a.iem.jaxb.t41.uc2.lf.v2022_05_12.LargeFamilyEvidenceType;

/**
 * Special marshaller for canonical evidences of the DE4A T4.1 v2021-04-13
 * pilot. This class can ONLY ready T4.1 stuff without the surrounding core
 * document.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        JAXB type to be read
 */
public class DE4AT41Marshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  protected DE4AT41Marshaller (@Nonnull final Class <JAXBTYPE> aType,
                               @Nonnull final List <? extends ClassPathResource> aXSDs,
                               @Nonnull final Function <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper,
                               @Nullable final NamespaceContext aNSContext)
  {
    super (aType, aXSDs, aJAXBElementWrapper);
    setNamespaceContext (aNSContext);
  }

  @Nonnull
  public static DE4AT41Marshaller <HigherEducationDiplomaType> higherEducationDiploma ()
  {
    return new DE4AT41Marshaller <> (HigherEducationDiplomaType.class,
                                     CT41.getAllXSDsHigherEducationDiploma (),
                                     new eu.de4a.iem.jaxb.t41.uc1.hed.v2021_04_13.ObjectFactory ()::createHigherEducationDiploma,
                                     DE4AT41NamespaceContext.getHigherEducationDiplomaInstance ());
  }

  @Nonnull
  public static DE4AT41Marshaller <SecondaryEducationDiplomaType> secondaryEducationDiploma ()
  {
    return new DE4AT41Marshaller <> (SecondaryEducationDiplomaType.class,
                                     CT41.getAllXSDsSecondaryEducationDiploma (),
                                     new eu.de4a.iem.jaxb.t41.uc1.sed.v2022_05_12.ObjectFactory ()::createSecondaryEducationDiploma,
                                     DE4AT41NamespaceContext.getSecondaryEducationDiplomaInstance ());
  }

  @Nonnull
  public static DE4AT41Marshaller <DisabilityEvidenceType> disability ()
  {
    return new DE4AT41Marshaller <> (DisabilityEvidenceType.class,
                                     CT41.getAllXSDsDisability (),
                                     new eu.de4a.iem.jaxb.t41.uc2.de.v2022_05_12.ObjectFactory ()::createDisability,
                                     DE4AT41NamespaceContext.getDisabilityInstance ());
  }

  @Nonnull
  public static DE4AT41Marshaller <LargeFamilyEvidenceType> largeFamily ()
  {
    return new DE4AT41Marshaller <> (LargeFamilyEvidenceType.class,
                                     CT41.getAllXSDsLargeFamily (),
                                     new eu.de4a.iem.jaxb.t41.uc2.lf.v2022_05_12.ObjectFactory ()::createLargeFamily,
                                     DE4AT41NamespaceContext.getLargeFamilyInstance ());
  }
}
