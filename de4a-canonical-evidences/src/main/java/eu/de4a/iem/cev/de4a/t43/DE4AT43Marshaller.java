/*
 * Copyright (C) 2023, Partners of the EU funded DE4A project consortium
 *   (https://www.de4a.eu/consortium), under Grant Agreement No.870635
 * Author: Austrian Federal Computing Center (BRZ)
 *
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
package eu.de4a.iem.cev.de4a.t43;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.NamespaceContext;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.jaxb.t43.birth.v1_7.BirthEvidenceType;
import eu.de4a.iem.jaxb.t43.domdereg.v1_0.DomicileDeregistrationEvidenceType;
import eu.de4a.iem.jaxb.t43.domreg.v1_7.DomicileRegistrationEvidenceType;
import eu.de4a.iem.jaxb.t43.marriage.v1_7.MarriageEvidenceType;

/**
 * Special marshaller for canonical evidences of the DE4A T4.3 v1.6a pilot. This
 * class can ONLY reads T4.3 stuff without the surrounding core document.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        JAXB type to be read
 */
public class DE4AT43Marshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  protected DE4AT43Marshaller (@Nonnull final Class <JAXBTYPE> aType,
                               @Nullable final List <? extends ClassPathResource> aXSDs,
                               @Nonnull final Function <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper,
                               @Nullable final NamespaceContext aNSContext)
  {
    super (aType, aXSDs, aJAXBElementWrapper);
    setNamespaceContext (aNSContext);
  }

  @Nonnull
  public static DE4AT43Marshaller <BirthEvidenceType> birthEvidence ()
  {
    return new DE4AT43Marshaller <> (BirthEvidenceType.class,
                                     CT43.getAllBirthEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.birth.v1_7.ObjectFactory ()::createBirthEvidence,
                                     DE4AT43NamespaceContext.getBirthEvidenceInstance ());
  }

  @Nonnull
  public static DE4AT43Marshaller <DomicileDeregistrationEvidenceType> domicileDeregistrationEvidence ()
  {
    return new DE4AT43Marshaller <> (DomicileDeregistrationEvidenceType.class,
                                     CT43.getAllDomicileDeregistrationEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.domdereg.v1_0.ObjectFactory ()::createDomicileDeregistrationEvidence,
                                     DE4AT43NamespaceContext.getDomicileDeregistrationEvidenceInstance ());
  }

  @Nonnull
  public static DE4AT43Marshaller <DomicileRegistrationEvidenceType> domicileRegistrationEvidence ()
  {
    return new DE4AT43Marshaller <> (DomicileRegistrationEvidenceType.class,
                                     CT43.getAllDomicileRegistrationEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.domreg.v1_7.ObjectFactory ()::createDomicileRegistrationEvidence,
                                     DE4AT43NamespaceContext.getDomicileRegistrationEvidenceInstance ());
  }

  @Nonnull
  public static DE4AT43Marshaller <MarriageEvidenceType> marriageEvidence ()
  {
    return new DE4AT43Marshaller <> (MarriageEvidenceType.class,
                                     CT43.getAllMarriageEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.marriage.v1_7.ObjectFactory ()::createMarriageEvidence,
                                     DE4AT43NamespaceContext.getMarriageEvidenceInstance ());
  }
}
