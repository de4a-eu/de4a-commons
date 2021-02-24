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

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.xml.bind.JAXBElement;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.functional.IFunction;
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
public class DE4AMarshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  public DE4AMarshaller (@Nonnull final Class <JAXBTYPE> aType,
                         @Nullable final List <? extends ClassPathResource> aXSDs,
                         @Nonnull final IFunction <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper)
  {
    super (aType, aXSDs, aJAXBElementWrapper);
    setNamespaceContext (DE4ANamespaceContext.getInstance ());
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DE_USI (@Nullable final ICommonsList <ClassPathResource> aCanonicalEvidenceXSDs)
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJAXB.XSDS);
    ret.add (CDE4AJAXB.XSD_DE_USI);
    if (aCanonicalEvidenceXSDs != null)
      ret.addAll (aCanonicalEvidenceXSDs);
    return ret;
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.de_usi.RequestForwardEvidenceType> deUsiRequestMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (RequestForwardEvidenceType.class,
                                  _getXSDs_DE_USI (aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.edm.jaxb.de_usi.ObjectFactory ()::createRequestForwardEvidence);
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.de_usi.ResponseForwardEvidenceType> deUsiResponseMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (ResponseForwardEvidenceType.class,
                                  _getXSDs_DE_USI (aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.edm.jaxb.de_usi.ObjectFactory ()::createResponseForwardEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DO_IM (@Nullable final ICommonsList <ClassPathResource> aCanonicalEvidenceXSDs)
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJAXB.XSDS);
    ret.add (CDE4AJAXB.XSD_DO_IM);
    if (aCanonicalEvidenceXSDs != null)
      ret.addAll (aCanonicalEvidenceXSDs);
    return ret;
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.do_im.RequestExtractEvidenceType> doImRequestMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.do_im.RequestExtractEvidenceType.class,
                                  _getXSDs_DO_IM (aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.edm.jaxb.do_im.ObjectFactory ()::createRequestExtractEvidence);
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.do_im.ResponseExtractEvidenceType> doImResponseMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.do_im.ResponseExtractEvidenceType.class,
                                  _getXSDs_DO_IM (aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.edm.jaxb.do_im.ObjectFactory ()::createResponseExtractEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DO_USI (@Nullable final ICommonsList <ClassPathResource> aCanonicalEvidenceXSDs)
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJAXB.XSDS);
    ret.add (CDE4AJAXB.XSD_DO_USI);
    if (aCanonicalEvidenceXSDs != null)
      ret.addAll (aCanonicalEvidenceXSDs);
    return ret;
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.do_usi.RequestExtractEvidenceType> doUsiRequestMarshaller ()
  {
    // Only CanonicalEvidenceId
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.do_usi.RequestExtractEvidenceType.class,
                                  _getXSDs_DO_USI (null),
                                  new eu.de4a.edm.jaxb.do_usi.ObjectFactory ()::createRequestExtractEvidence);
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.do_usi.ResponseExtractEvidenceType> doUsiResponseMarshaller ()
  {
    // Only CanonicalEvidenceId
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.do_usi.ResponseExtractEvidenceType.class,
                                  _getXSDs_DO_USI (null),
                                  new eu.de4a.edm.jaxb.do_usi.ObjectFactory ()::createResponseExtractEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DR_IM (@Nullable final ICommonsList <ClassPathResource> aCanonicalEvidenceXSDs)
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJAXB.XSDS);
    ret.add (CDE4AJAXB.XSD_DR_IM);
    if (aCanonicalEvidenceXSDs != null)
      ret.addAll (aCanonicalEvidenceXSDs);
    return ret;
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.dr_im.RequestTransferEvidenceType> drImRequestMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.dr_im.RequestTransferEvidenceType.class,
                                  _getXSDs_DR_IM (aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.edm.jaxb.dr_im.ObjectFactory ()::createRequestTransferEvidence);
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.dr_im.ResponseTransferEvidenceType> drImResponseMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.dr_im.ResponseTransferEvidenceType.class,
                                  _getXSDs_DR_IM (aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.edm.jaxb.dr_im.ObjectFactory ()::createResponseTransferEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DR_USI (@Nullable final ICommonsList <ClassPathResource> aCanonicalEvidenceXSDs)
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJAXB.XSDS);
    ret.add (CDE4AJAXB.XSD_DR_USI);
    if (aCanonicalEvidenceXSDs != null)
      ret.addAll (aCanonicalEvidenceXSDs);
    return ret;
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.dr_usi.RequestTransferEvidenceType> drUsiRequestMarshaller ()
  {
    // Only CanonicalEvidenceId
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.dr_usi.RequestTransferEvidenceType.class,
                                  _getXSDs_DR_USI (null),
                                  new eu.de4a.edm.jaxb.dr_usi.ObjectFactory ()::createRequestTransferEvidence);
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.dr_usi.ResponseTransferEvidenceType> drUsiResponseMarshaller ()
  {
    // Only CanonicalEvidenceId
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.dr_usi.ResponseTransferEvidenceType.class,
                                  _getXSDs_DR_USI (null),
                                  new eu.de4a.edm.jaxb.dr_usi.ObjectFactory ()::createResponseTransferEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_DT_USI (@Nullable final ICommonsList <ClassPathResource> aCanonicalEvidenceXSDs)
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJAXB.XSDS);
    ret.add (CDE4AJAXB.XSD_DT_USI);
    if (aCanonicalEvidenceXSDs != null)
      ret.addAll (aCanonicalEvidenceXSDs);
    return ret;
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.dt_usi.RequestTransferEvidenceType> dtUsiRequestMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.dt_usi.RequestTransferEvidenceType.class,
                                  _getXSDs_DT_USI (aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.edm.jaxb.dt_usi.ObjectFactory ()::createRequestTransferEvidence);
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.dt_usi.ResponseTransferEvidenceType> dtUsiResponseMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.dt_usi.ResponseTransferEvidenceType.class,
                                  _getXSDs_DT_USI (aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.edm.jaxb.dt_usi.ObjectFactory ()::createResponseTransferEvidence);
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs_IDK (@Nullable final ICommonsList <ClassPathResource> aCanonicalEvidenceXSDs)
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJAXB.XSDS);
    ret.add (CDE4AJAXB.XSD_DR_DT_IDK);
    if (aCanonicalEvidenceXSDs != null)
      ret.addAll (aCanonicalEvidenceXSDs);
    return ret;
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.idk.RequestLookupEvidenceServiceDataType> idkRequestLookupEvidenceServiceDataMarshaller ()
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.idk.RequestLookupEvidenceServiceDataType.class,
                                  _getXSDs_IDK (null),
                                  new eu.de4a.edm.jaxb.idk.ObjectFactory ()::createRequestLookupEvidenceServiceData);
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.idk.ResponseLookupEvidenceServiceDataType> idkResponseLookupEvidenceServiceDataMarshaller ()
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.idk.ResponseLookupEvidenceServiceDataType.class,
                                  _getXSDs_IDK (null),
                                  new eu.de4a.edm.jaxb.idk.ObjectFactory ()::createResponseLookupEvidenceServiceData);
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.idk.RequestLookupRoutingInformationType> idkRequestLookupRoutingInformationMarshaller ()
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.idk.RequestLookupRoutingInformationType.class,
                                  _getXSDs_IDK (null),
                                  new eu.de4a.edm.jaxb.idk.ObjectFactory ()::createRequestLookupRoutingInformation);
  }

  @Nonnull
  public static DE4AMarshaller <eu.de4a.edm.jaxb.idk.ResponseLookupRoutingInformationType> idkResponseLookupRoutingInformationMarshaller ()
  {
    return new DE4AMarshaller <> (eu.de4a.edm.jaxb.idk.ResponseLookupRoutingInformationType.class,
                                  _getXSDs_IDK (null),
                                  new eu.de4a.edm.jaxb.idk.ObjectFactory ()::createResponseLookupRoutingInformation);
  }
}
