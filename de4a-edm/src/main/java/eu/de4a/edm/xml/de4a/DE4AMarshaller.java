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
package eu.de4a.edm.xml.de4a;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.edm.jaxb.de_usi.RequestForwardEvidenceType;
import eu.de4a.edm.jaxb.de_usi.ResponseForwardEvidenceType;

/**
 * DE4A Marshaller factory
 *
 * @author Philip Helger
 */
@Immutable
public class DE4AMarshaller
{
  private DE4AMarshaller ()
  {}

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DE_USI ()
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJaxb.XSDS);
    ret.add (CDE4AJaxb.XSD_DE_USI);
    return ret;
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.de_usi.RequestForwardEvidenceType> deUsiRequestMarshaller ()
  {
    return new GenericJAXBMarshaller <> (RequestForwardEvidenceType.class,
                                         _getXSDs_DE_USI (),
                                         new eu.de4a.edm.jaxb.de_usi.ObjectFactory ()::createRequestForwardEvidence);
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.de_usi.ResponseForwardEvidenceType> deUsiResponseMarshaller ()
  {
    return new GenericJAXBMarshaller <> (ResponseForwardEvidenceType.class,
                                         _getXSDs_DE_USI (),
                                         new eu.de4a.edm.jaxb.de_usi.ObjectFactory ()::createResponseForwardEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DO_IM ()
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJaxb.XSDS);
    ret.add (CDE4AJaxb.XSD_DO_IM);
    return ret;
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.do_im.RequestExtractEvidenceType> doImRequestMarshaller ()
  {
    return new GenericJAXBMarshaller <> (eu.de4a.edm.jaxb.do_im.RequestExtractEvidenceType.class,
                                         _getXSDs_DO_IM (),
                                         new eu.de4a.edm.jaxb.do_im.ObjectFactory ()::createRequestExtractEvidence);
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.do_im.ResponseExtractEvidenceType> doImResponseMarshaller ()
  {
    return new GenericJAXBMarshaller <> (eu.de4a.edm.jaxb.do_im.ResponseExtractEvidenceType.class,
                                         _getXSDs_DO_IM (),
                                         new eu.de4a.edm.jaxb.do_im.ObjectFactory ()::createResponseExtractEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DO_USI ()
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJaxb.XSDS);
    ret.add (CDE4AJaxb.XSD_DO_USI);
    return ret;
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.do_usi.RequestExtractEvidenceType> doUsiRequestMarshaller ()
  {
    return new GenericJAXBMarshaller <> (eu.de4a.edm.jaxb.do_usi.RequestExtractEvidenceType.class,
                                         _getXSDs_DO_USI (),
                                         new eu.de4a.edm.jaxb.do_usi.ObjectFactory ()::createRequestExtractEvidence);
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.do_usi.ResponseExtractEvidenceType> doUsiResponseMarshaller ()
  {
    return new GenericJAXBMarshaller <> (eu.de4a.edm.jaxb.do_usi.ResponseExtractEvidenceType.class,
                                         _getXSDs_DO_USI (),
                                         new eu.de4a.edm.jaxb.do_usi.ObjectFactory ()::createResponseExtractEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DR_IM ()
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJaxb.XSDS);
    ret.add (CDE4AJaxb.XSD_DR_IM);
    return ret;
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.dr_im.RequestTransferEvidenceType> drImRequestMarshaller ()
  {
    return new GenericJAXBMarshaller <> (eu.de4a.edm.jaxb.dr_im.RequestTransferEvidenceType.class,
                                         _getXSDs_DR_IM (),
                                         new eu.de4a.edm.jaxb.dr_im.ObjectFactory ()::createRequestTransferEvidence);
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.dr_im.ResponseTransferEvidenceType> drImResponseMarshaller ()
  {
    return new GenericJAXBMarshaller <> (eu.de4a.edm.jaxb.dr_im.ResponseTransferEvidenceType.class,
                                         _getXSDs_DR_IM (),
                                         new eu.de4a.edm.jaxb.dr_im.ObjectFactory ()::createResponseTransferEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DR_USI ()
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJaxb.XSDS);
    ret.add (CDE4AJaxb.XSD_DR_USI);
    return ret;
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.dr_usi.RequestTransferEvidenceType> drUsiRequestMarshaller ()
  {
    return new GenericJAXBMarshaller <> (eu.de4a.edm.jaxb.dr_usi.RequestTransferEvidenceType.class,
                                         _getXSDs_DR_USI (),
                                         new eu.de4a.edm.jaxb.dr_usi.ObjectFactory ()::createRequestTransferEvidence);
  }

  @Nonnull
  public static GenericJAXBMarshaller <eu.de4a.edm.jaxb.dr_usi.ResponseTransferEvidenceType> drUsiResponseMarshaller ()
  {
    return new GenericJAXBMarshaller <> (eu.de4a.edm.jaxb.dr_usi.ResponseTransferEvidenceType.class,
                                         _getXSDs_DR_USI (),
                                         new eu.de4a.edm.jaxb.dr_usi.ObjectFactory ()::createResponseTransferEvidence);
  }
}
