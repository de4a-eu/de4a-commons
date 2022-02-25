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
package eu.de4a.iem.core;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.xml.bind.JAXBElement;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.core.jaxb.common.EventNotificationType;
import eu.de4a.iem.core.jaxb.common.RedirectUserType;
import eu.de4a.iem.core.jaxb.common.RequestEventSubscriptionType;
import eu.de4a.iem.core.jaxb.common.RequestExtractMultiEvidenceIMType;
import eu.de4a.iem.core.jaxb.common.RequestExtractMultiEvidenceLUType;
import eu.de4a.iem.core.jaxb.common.RequestExtractMultiEvidenceUSIType;
import eu.de4a.iem.core.jaxb.common.ResponseErrorType;
import eu.de4a.iem.core.jaxb.common.ResponseEventSubscriptionType;
import eu.de4a.iem.core.jaxb.common.ResponseExtractMultiEvidenceType;

/**
 * DE4A Marshaller factory for the core data format
 *
 * @author Philip Helger
 */
@Immutable
public class DE4AMarshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  public DE4AMarshaller (@Nonnull final Class <JAXBTYPE> aType,
                         @Nullable final List <? extends ClassPathResource> aXSDs,
                         @Nonnull final Function <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper)
  {
    super (aType, aXSDs, aJAXBElementWrapper);
    setNamespaceContext (DE4ANamespaceContext.getInstance ());
  }

  /**
   * Enable formatted output. Syntactic sugar.
   *
   * @return this for chaining
   */
  @Nonnull
  public final DE4AMarshaller <JAXBTYPE> formatted ()
  {
    setFormattedOutput (true);
    return this;
  }

  @Nonnull
  @Nonempty
  private static ICommonsList <ClassPathResource> _getXSDs (@Nonnull final ClassPathResource aCoreXSD,
                                                            @Nullable final ICommonsList <? extends ClassPathResource> aCanonicalEvidenceXSDs)
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJAXB.XSDS);
    ret.add (aCoreXSD);
    if (aCanonicalEvidenceXSDs != null)
      ret.addAll (aCanonicalEvidenceXSDs);
    return ret;
  }

  // DE - Data Evaluator

  @Nonnull
  public static DE4AMarshaller <ResponseExtractMultiEvidenceType> deRequestForwardEvidenceMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (ResponseExtractMultiEvidenceType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DE, aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.iem.core.jaxb.de.ObjectFactory ()::createRequestForwardEvidence);
  }

  @Nonnull
  public static DE4AMarshaller <RedirectUserType> deUsiRedirectUserMarshaller ()
  {
    return new DE4AMarshaller <> (RedirectUserType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DE, null),
                                  new eu.de4a.iem.core.jaxb.de.ObjectFactory ()::createUSIRedirectUser);
  }

  @Nonnull
  public static DE4AMarshaller <EventNotificationType> deEventNotificationMarshaller ()
  {
    return new DE4AMarshaller <> (EventNotificationType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DE, null),
                                  new eu.de4a.iem.core.jaxb.de.ObjectFactory ()::createEventNotification);
  }

  // DO - Data Owner

  @Nonnull
  public static DE4AMarshaller <RequestExtractMultiEvidenceIMType> doRequestExtractMultiEvidenceIMMarshaller ()
  {
    return new DE4AMarshaller <> (RequestExtractMultiEvidenceIMType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DO, null),
                                  new eu.de4a.iem.core.jaxb.do_.ObjectFactory ()::createRequestExtractMultiEvidenceIM);
  }

  @Nonnull
  public static DE4AMarshaller <RequestExtractMultiEvidenceLUType> doRequestExtractMultiEvidenceLUMarshaller ()
  {
    return new DE4AMarshaller <> (RequestExtractMultiEvidenceLUType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DO, null),
                                  new eu.de4a.iem.core.jaxb.do_.ObjectFactory ()::createRequestExtractMultiEvidenceLU);
  }

  @Nonnull
  public static DE4AMarshaller <RequestExtractMultiEvidenceUSIType> doRequestExtractMultiEvidenceUSIMarshaller ()
  {
    return new DE4AMarshaller <> (RequestExtractMultiEvidenceUSIType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DO, null),
                                  new eu.de4a.iem.core.jaxb.do_.ObjectFactory ()::createRequestExtractMultiEvidenceUSI);
  }

  @Nonnull
  public static DE4AMarshaller <RequestEventSubscriptionType> doRequestEventSubscriptionMarshaller ()
  {
    return new DE4AMarshaller <> (RequestEventSubscriptionType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DO, null),
                                  new eu.de4a.iem.core.jaxb.do_.ObjectFactory ()::createRequestEventSubscription);
  }

  // DR - Data Requestor

  @Nonnull
  public static DE4AMarshaller <RequestExtractMultiEvidenceIMType> drRequestTransferEvidenceIMMarshaller ()
  {
    return new DE4AMarshaller <> (RequestExtractMultiEvidenceIMType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DR, null),
                                  new eu.de4a.iem.core.jaxb.dr.ObjectFactory ()::createRequestTransferEvidenceIM);
  }

  @Nonnull
  public static DE4AMarshaller <RequestExtractMultiEvidenceLUType> drRequestTransferEvidenceLUMarshaller ()
  {
    return new DE4AMarshaller <> (RequestExtractMultiEvidenceLUType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DR, null),
                                  new eu.de4a.iem.core.jaxb.dr.ObjectFactory ()::createRequestTransferEvidenceLU);
  }

  @Nonnull
  public static DE4AMarshaller <RequestExtractMultiEvidenceUSIType> drRequestTransferEvidenceUSIMarshaller ()
  {
    return new DE4AMarshaller <> (RequestExtractMultiEvidenceUSIType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DR, null),
                                  new eu.de4a.iem.core.jaxb.dr.ObjectFactory ()::createRequestTransferEvidenceUSI);
  }

  @Nonnull
  public static DE4AMarshaller <RequestEventSubscriptionType> drRequestEventSubscriptionMarshaller ()
  {
    return new DE4AMarshaller <> (RequestEventSubscriptionType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DR, null),
                                  new eu.de4a.iem.core.jaxb.dr.ObjectFactory ()::createRequestEventSubscription);
  }

  // DT - Data Transferor

  @Nonnull
  public static DE4AMarshaller <RedirectUserType> dtUSIRedirectUserMarshaller ()
  {
    return new DE4AMarshaller <> (RedirectUserType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DT, null),
                                  new eu.de4a.iem.core.jaxb.dt.ObjectFactory ()::createUSIRedirectUser);
  }

  @Nonnull
  public static DE4AMarshaller <ResponseExtractMultiEvidenceType> dtResponseTransferEvidenceMarshaller (@Nonnull final IDE4ACanonicalEvidenceType aCanonicalEvidenceType)
  {
    return new DE4AMarshaller <> (ResponseExtractMultiEvidenceType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DT, aCanonicalEvidenceType.getAllXSDs ()),
                                  new eu.de4a.iem.core.jaxb.dt.ObjectFactory ()::createResponseTransferEvidence);
  }

  @Nonnull
  public static DE4AMarshaller <ResponseEventSubscriptionType> dtResponseEventSubscriptionMarshaller ()
  {
    return new DE4AMarshaller <> (ResponseEventSubscriptionType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DT, null),
                                  new eu.de4a.iem.core.jaxb.dt.ObjectFactory ()::createResponseEventSubscription);
  }

  @Nonnull
  public static DE4AMarshaller <EventNotificationType> dtEventNotificationMarshaller ()
  {
    return new DE4AMarshaller <> (EventNotificationType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DT, null),
                                  new eu.de4a.iem.core.jaxb.dt.ObjectFactory ()::createEventNotification);
  }

  // Default response

  @Nonnull
  public static DE4AMarshaller <ResponseErrorType> defResponseMessage ()
  {
    return new DE4AMarshaller <> (ResponseErrorType.class,
                                  _getXSDs (CDE4AJAXB.XSD_DEFAULT_RESPONSE, null),
                                  new eu.de4a.iem.core.jaxb.defresp.ObjectFactory ()::createResponseMessage);
  }
}
