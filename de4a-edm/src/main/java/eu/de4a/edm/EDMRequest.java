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
package eu.de4a.edm;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.w3c.dom.Node;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.traits.IGenericImplTrait;
import com.helger.regrep.RegRep4Reader;
import com.helger.regrep.RegRep4Writer;
import com.helger.regrep.RegRepHelper;
import com.helger.regrep.query.QueryRequest;
import com.helger.regrep.query.ResponseOptionType;
import com.helger.regrep.rim.AnyValueType;
import com.helger.regrep.rim.DateTimeValueType;
import com.helger.regrep.rim.InternationalStringType;
import com.helger.regrep.rim.InternationalStringValueType;
import com.helger.regrep.rim.LocalizedStringType;
import com.helger.regrep.rim.QueryType;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.rim.StringValueType;
import com.helger.regrep.rim.ValueType;
import com.helger.regrep.slot.ISlotProvider;
import com.helger.regrep.slot.SlotHelper;
import com.helger.regrep.slot.predefined.SlotId;

import eu.de4a.edm.jaxb.cv.agent.AgentType;
import eu.de4a.edm.jaxb.w3.cv.ac.CoreBusinessType;
import eu.de4a.edm.jaxb.w3.cv.ac.CorePersonType;
import eu.de4a.edm.model.AgentPojo;
import eu.de4a.edm.model.BusinessPojo;
import eu.de4a.edm.model.EDE4ALanguageCode;
import eu.de4a.edm.model.EDE4AQueryDefinitionType;
import eu.de4a.edm.model.EDE4AResponseOptionType;
import eu.de4a.edm.model.PersonPojo;
import eu.de4a.edm.request.EDMRequestPayloadDocumentID;
import eu.de4a.edm.request.IEDMRequestPayloadProvider;
import eu.de4a.edm.slot.SlotAuthorizedRepresentative;
import eu.de4a.edm.slot.SlotConsentToken;
import eu.de4a.edm.slot.SlotDataConsumer;
import eu.de4a.edm.slot.SlotDataSubjectLegalPerson;
import eu.de4a.edm.slot.SlotDataSubjectNaturalPerson;
import eu.de4a.edm.slot.SlotDatasetIdentifier;
import eu.de4a.edm.slot.SlotIssueDateTime;
import eu.de4a.edm.slot.SlotProcedure;
import eu.de4a.edm.slot.SlotSpecificationIdentifier;
import eu.de4a.edm.xml.IJAXBVersatileReader;
import eu.de4a.edm.xml.IVersatileWriter;
import eu.de4a.edm.xml.JAXBVersatileReader;
import eu.de4a.edm.xml.JAXBVersatileWriter;
import eu.de4a.edm.xml.cagv.AgentMarshaller;
import eu.de4a.edm.xml.cagv.CCAGV;
import eu.de4a.edm.xml.cv.BusinessMarshaller;
import eu.de4a.edm.xml.cv.PersonMarshaller;

/**
 * This class contains the data model for a single DE4A EDM Request. It requires
 * at least the following fields:
 * <ul>
 * <li>QueryDefinition - Document query?</li>
 * <li>Request ID - the internal ID of the request that must be part of the
 * response. Can be a UUID.</li>
 * <li>Specification Identifier - must be the value
 * {@link CDE4AEDM#SPECIFICATION_IDENTIFIER_DE4A_EDM_V01}.</li>
 * <li>Issue date time - when the request was created. Ideally in UTC.</li>
 * <li>Data Consumer - the basic infos of the DC</li>
 * <li>Data Subject - either as Legal Person or as Natural Person, but not
 * both.</li>
 * <li>If it is a "DocumentQuery" the request distribution must be
 * provided.</li>
 * <li>If it is a "ObjectReference" the request Document ID must be
 * provided.</li>
 * </ul>
 * It is recommended to use the <code>builder*()</code> methods to create the
 * EDM request using the builder pattern with a fluent API.
 *
 * @author Philip Helger
 * @author Konstantinos Douloudis
 */
public class EDMRequest implements IEDMTopLevelObject
{
  private static final ICommonsOrderedSet <String> TOP_LEVEL_SLOTS = new CommonsLinkedHashSet <> (SlotSpecificationIdentifier.NAME,
                                                                                                  SlotIssueDateTime.NAME,
                                                                                                  SlotProcedure.NAME,
                                                                                                  SlotConsentToken.NAME,
                                                                                                  SlotDatasetIdentifier.NAME,
                                                                                                  SlotDataConsumer.NAME);

  private final EDE4AQueryDefinitionType m_eQueryDefinition;
  private final String m_sRequestID;
  private final EDE4AResponseOptionType m_eResponseOption;
  private final String m_sSpecificationIdentifier;
  private final LocalDateTime m_aIssueDateTime;
  private final InternationalStringType m_aProcedure;
  private final AgentPojo m_aDataConsumer;
  private final String m_sConsentToken;
  private final String m_sDatasetIdentifier;
  private final BusinessPojo m_aDataSubjectLegalPerson;
  private final PersonPojo m_aDataSubjectNaturalPerson;
  private final PersonPojo m_aAuthorizedRepresentative;
  private final IEDMRequestPayloadProvider m_aPayloadProvider;

  protected EDMRequest (@Nonnull final EDE4AQueryDefinitionType eQueryDefinition,
                        @Nonnull @Nonempty final String sRequestID,
                        @Nonnull final EDE4AResponseOptionType eResponseOption,
                        @Nonnull @Nonempty final String sSpecificationIdentifier,
                        @Nonnull final LocalDateTime aIssueDateTime,
                        @Nullable final InternationalStringType aProcedure,
                        @Nonnull final AgentPojo aDataConsumer,
                        @Nullable final String sConsentToken,
                        @Nullable final String sDatasetIdentifier,
                        @Nullable final BusinessPojo aDataSubjectLegalPerson,
                        @Nullable final PersonPojo aDataSubjectNaturalPerson,
                        @Nullable final PersonPojo aAuthorizedRepresentative,
                        @Nonnull final IEDMRequestPayloadProvider aRPP)
  {
    ValueEnforcer.notNull (eQueryDefinition, "QueryDefinition");
    ValueEnforcer.notNull (eResponseOption, "ResponseOption");
    ValueEnforcer.notEmpty (sRequestID, "RequestID");
    ValueEnforcer.notEmpty (sSpecificationIdentifier, "SpecificationIdentifier");
    ValueEnforcer.notNull (aIssueDateTime, "IssueDateTime");
    ValueEnforcer.notNull (aDataConsumer, "DataConsumer");
    if (!eQueryDefinition.isDataSujectOptional ())
      ValueEnforcer.isFalse (aDataSubjectLegalPerson == null && aDataSubjectNaturalPerson == null, "A DataSubject must be set");
    ValueEnforcer.isFalse (aDataSubjectLegalPerson != null && aDataSubjectNaturalPerson != null,
                           "Not more than one DataSubject must be set");
    ValueEnforcer.notNull (aRPP, "RequestPayloadProvider");

    m_eQueryDefinition = eQueryDefinition;
    m_sRequestID = sRequestID;
    m_eResponseOption = eResponseOption;
    m_sSpecificationIdentifier = sSpecificationIdentifier;
    m_aIssueDateTime = aIssueDateTime;
    m_aProcedure = aProcedure;
    m_aDataConsumer = aDataConsumer;
    m_sConsentToken = sConsentToken;
    m_sDatasetIdentifier = sDatasetIdentifier;
    m_aDataSubjectLegalPerson = aDataSubjectLegalPerson;
    m_aDataSubjectNaturalPerson = aDataSubjectNaturalPerson;
    m_aAuthorizedRepresentative = aAuthorizedRepresentative;
    m_aPayloadProvider = aRPP;
  }

  /**
   * @return The query definition type as provided in the constructor. Never
   *         <code>null</code>.
   */
  @Nonnull
  public final EDE4AQueryDefinitionType getQueryDefinition ()
  {
    return m_eQueryDefinition;
  }

  /**
   * @return The DE4A internal request ID. Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public final String getRequestID ()
  {
    return m_sRequestID;
  }

  /**
   * @return The response option to be used. Never <code>null</code>.
   */
  @Nonnull
  public final EDE4AResponseOptionType getResponseOption ()
  {
    return m_eResponseOption;
  }

  /**
   * @return The specification identifier that identifies the data model in use.
   *         See {@link CDE4AEDM#SPECIFICATION_IDENTIFIER_DE4A_EDM_V01} for the
   *         current one.
   */
  @Nonnull
  @Nonempty
  public final String getSpecificationIdentifier ()
  {
    return m_sSpecificationIdentifier;
  }

  /**
   * @return The local date and time when the EDM request was created. Never
   *         <code>null</code>.
   */
  @Nonnull
  public final LocalDateTime getIssueDateTime ()
  {
    return m_aIssueDateTime;
  }

  @Nullable
  public final InternationalStringType getProcedure ()
  {
    return m_aProcedure;
  }

  @Nonnull
  public final AgentPojo getDataConsumer ()
  {
    return m_aDataConsumer;
  }

  @Nullable
  public final String getConsentToken ()
  {
    return m_sConsentToken;
  }

  @Nullable
  public final String getDatasetIdentifier ()
  {
    return m_sDatasetIdentifier;
  }

  @Nullable
  public final BusinessPojo getDataSubjectLegalPerson ()
  {
    return m_aDataSubjectLegalPerson;
  }

  @Nullable
  public final PersonPojo getDataSubjectNaturalPerson ()
  {
    return m_aDataSubjectNaturalPerson;
  }

  @Nullable
  public final PersonPojo getAuthorizedRepresentative ()
  {
    return m_aAuthorizedRepresentative;
  }

  /**
   * @return The request payload provider. Never <code>null</code>. This is one
   *         of {@link eu.de4a.edm.request.IEDMRequestPayloadDocumentID}.
   */
  @Nonnull
  public final IEDMRequestPayloadProvider getPayloadProvider ()
  {
    return m_aPayloadProvider;
  }

  @Nonnull
  private QueryRequest _createQueryRequest (@Nonnull final ICommonsList <ISlotProvider> aProviders)
  {
    ValueEnforcer.notNull (m_eQueryDefinition, "QueryDefinition");
    ValueEnforcer.notEmpty (m_sRequestID, "RequestID");
    ValueEnforcer.noNullValue (aProviders, "Providers");

    // Maintain original order
    final ICommonsOrderedMap <String, ISlotProvider> aProviderMap = new CommonsLinkedHashMap <> ();
    for (final ISlotProvider aItem : aProviders)
    {
      final String sName = aItem.getName ();
      if (aProviderMap.containsKey (sName))
        throw new IllegalArgumentException ("A slot provider for name '" + sName + "' is already present");
      aProviderMap.put (sName, aItem);
    }

    final QueryRequest ret = RegRepHelper.createEmptyQueryRequest ();
    ret.setId (m_sRequestID);
    ret.getResponseOption ().setReturnType (m_eResponseOption.getID ());

    // All top-level slots outside of query
    for (final String sTopLevel : TOP_LEVEL_SLOTS)
    {
      final ISlotProvider aSP = aProviderMap.get (sTopLevel);
      if (aSP != null)
        ret.addSlot (aSP.createSlot ());
    }

    {
      final QueryType aQuery = new QueryType ();
      aQuery.setQueryDefinition (m_eQueryDefinition.getID ());

      // All slots inside of query
      for (final Map.Entry <String, ISlotProvider> aEntry : aProviderMap.entrySet ())
        if (!TOP_LEVEL_SLOTS.contains (aEntry.getKey ()))
          aQuery.addSlot (aEntry.getValue ().createSlot ());

      ret.setQuery (aQuery);
    }

    return ret;
  }

  @Nonnull
  public QueryRequest getAsQueryRequest ()
  {
    final ICommonsList <ISlotProvider> aSlots = new CommonsArrayList <> ();

    // Top-level slots
    if (m_sSpecificationIdentifier != null)
      aSlots.add (new SlotSpecificationIdentifier (m_sSpecificationIdentifier));
    if (m_aIssueDateTime != null)
      aSlots.add (new SlotIssueDateTime (m_aIssueDateTime));
    if (m_aProcedure != null)
      aSlots.add (new SlotProcedure (m_aProcedure));
    if (m_sConsentToken != null)
      aSlots.add (new SlotConsentToken (m_sConsentToken));
    if (m_sDatasetIdentifier != null)
      aSlots.add (new SlotDatasetIdentifier (m_sDatasetIdentifier));
    if (m_aDataConsumer != null)
      aSlots.add (new SlotDataConsumer (m_aDataConsumer));

    // Commons Query slots
    if (m_aDataSubjectLegalPerson != null)
      aSlots.add (new SlotDataSubjectLegalPerson (m_aDataSubjectLegalPerson));
    if (m_aDataSubjectNaturalPerson != null)
      aSlots.add (new SlotDataSubjectNaturalPerson (m_aDataSubjectNaturalPerson));
    if (m_aAuthorizedRepresentative != null)
      aSlots.add (new SlotAuthorizedRepresentative (m_aAuthorizedRepresentative));

    // Request payload slot
    aSlots.add (m_aPayloadProvider.getAsSlotProvider ());

    return _createQueryRequest (aSlots);
  }

  @Nonnull
  public IVersatileWriter <QueryRequest> getWriter ()
  {
    return new JAXBVersatileWriter <> (getAsQueryRequest (), RegRep4Writer.queryRequest (CCAGV.XSDS).setFormattedOutput (true));
  }

  @Nonnull
  public static IJAXBVersatileReader <EDMRequest> reader ()
  {
    return new JAXBVersatileReader <> (RegRep4Reader.queryRequest (CCAGV.XSDS), EDMRequest::create);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass () != o.getClass ())
      return false;
    final EDMRequest rhs = (EDMRequest) o;
    return EqualsHelper.equals (m_eQueryDefinition, rhs.m_eQueryDefinition) &&
           EqualsHelper.equals (m_eResponseOption, rhs.m_eResponseOption) &&
           EqualsHelper.equals (m_sRequestID, rhs.m_sRequestID) &&
           EqualsHelper.equals (m_sSpecificationIdentifier, rhs.m_sSpecificationIdentifier) &&
           EqualsHelper.equals (m_aIssueDateTime, rhs.m_aIssueDateTime) &&
           EqualsHelper.equals (m_aProcedure, rhs.m_aProcedure) &&
           EqualsHelper.equals (m_aDataConsumer, rhs.m_aDataConsumer) &&
           EqualsHelper.equals (m_sConsentToken, rhs.m_sConsentToken) &&
           EqualsHelper.equals (m_sDatasetIdentifier, rhs.m_sDatasetIdentifier) &&
           EqualsHelper.equals (m_aDataSubjectLegalPerson, rhs.m_aDataSubjectLegalPerson) &&
           EqualsHelper.equals (m_aDataSubjectNaturalPerson, rhs.m_aDataSubjectNaturalPerson) &&
           EqualsHelper.equals (m_aAuthorizedRepresentative, rhs.m_aAuthorizedRepresentative) &&
           EqualsHelper.equals (m_aPayloadProvider, rhs.m_aPayloadProvider);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eQueryDefinition)
                                       .append (m_eResponseOption)
                                       .append (m_sRequestID)
                                       .append (m_sSpecificationIdentifier)
                                       .append (m_aIssueDateTime)
                                       .append (m_aProcedure)
                                       .append (m_aDataConsumer)
                                       .append (m_sConsentToken)
                                       .append (m_sDatasetIdentifier)
                                       .append (m_aDataSubjectLegalPerson)
                                       .append (m_aDataSubjectNaturalPerson)
                                       .append (m_aAuthorizedRepresentative)
                                       .append (m_aPayloadProvider)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("QueryDefinition", m_eQueryDefinition)
                                       .append ("ResponseOption", m_eResponseOption)
                                       .append ("RequestID", m_sRequestID)
                                       .append ("SpecificationIdentifier", m_sSpecificationIdentifier)
                                       .append ("IssueDateTime", m_aIssueDateTime)
                                       .append ("Procedure", m_aProcedure)
                                       .append ("DataConsumer", m_aDataConsumer)
                                       .append ("ConsentToken", m_sConsentToken)
                                       .append ("DatasetIdentifier", m_sDatasetIdentifier)
                                       .append ("DataSubjectLegalPerson", m_aDataSubjectLegalPerson)
                                       .append ("DataSubjectNaturalPerson", m_aDataSubjectNaturalPerson)
                                       .append ("AuthorizedRepresentative", m_aAuthorizedRepresentative)
                                       .append ("RequestPayloadProvider", m_aPayloadProvider)
                                       .getToString ();
  }

  /**
   * @return A new builder for an EDM Request that is request with a an ID only.
   */
  @Nonnull
  public static BuilderDocumentByID builderDocumentByID ()
  {
    return new BuilderDocumentByID ().specificationIdentifier (CDE4AEDM.SPECIFICATION_IDENTIFIER_DE4A_EDM_V01)
                                     .responseOption (EDE4AResponseOptionType.INLINE);
  }

  /**
   * Generic Builder for an EDM request
   *
   * @author Philip Helger
   * @param <IMPLTYPE>
   *        The implementation class of this class
   */
  public abstract static class AbstractBuilder <IMPLTYPE extends AbstractBuilder <IMPLTYPE>> implements IGenericImplTrait <IMPLTYPE>
  {
    protected final EDE4AQueryDefinitionType m_eQueryDefinition;
    protected String m_sRequestID;
    protected EDE4AResponseOptionType m_eResponseOption;
    protected String m_sSpecificationIdentifier;
    protected LocalDateTime m_aIssueDateTime;
    protected InternationalStringType m_aProcedure;
    protected AgentPojo m_aDataConsumer;
    protected String m_sConsentToken;
    protected String m_sDatasetIdentifier;
    protected BusinessPojo m_aDataSubjectLegalPerson;
    protected PersonPojo m_aDataSubjectNaturalPerson;
    protected PersonPojo m_aAuthorizedRepresentative;

    protected AbstractBuilder (@Nonnull final EDE4AQueryDefinitionType e)
    {
      ValueEnforcer.notNull (e, "QueryDefinitionType");
      m_eQueryDefinition = e;
    }

    @Nonnull
    public final IMPLTYPE randomID ()
    {
      return id (UUID.randomUUID ());
    }

    @Nonnull
    public final IMPLTYPE id (@Nullable final UUID a)
    {
      return id (a == null ? null : a.toString ());
    }

    @Nonnull
    public final IMPLTYPE id (@Nullable final String s)
    {
      m_sRequestID = s;
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE responseOption (@Nullable final EDE4AResponseOptionType e)
    {
      m_eResponseOption = e;
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE specificationIdentifier (@Nullable final String s)
    {
      m_sSpecificationIdentifier = s;
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE issueDateTimeNow ()
    {
      return issueDateTime (PDTFactory.getCurrentLocalDateTime ());
    }

    @Nonnull
    public final IMPLTYPE issueDateTime (@Nullable final LocalDateTime a)
    {
      m_aIssueDateTime = a == null ? null : a.truncatedTo (ChronoUnit.MILLIS);
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE procedure (@Nullable final LocalizedStringType... a)
    {
      return procedure (a == null ? null : SlotHelper.createInternationalStringType (a));
    }

    @Nonnull
    public final IMPLTYPE procedure (@Nonnull final Locale aLocale, @Nonnull final String sText)
    {
      return procedure (SlotHelper.createLocalizedString (aLocale, sText));
    }

    @Nonnull
    public final IMPLTYPE procedure (@Nonnull @Nonempty final String sLanguage, @Nonnull final String sText)
    {
      return procedure (SlotHelper.createLocalizedString (sLanguage, sText));
    }

    @Nonnull
    public final IMPLTYPE procedure (@Nonnull final EDE4ALanguageCode eLanguage, @Nonnull final String sText)
    {
      return procedure (SlotHelper.createLocalizedString (eLanguage.getID (), sText));
    }

    @Nonnull
    public final IMPLTYPE procedure (@Nullable final Map <String, String> a)
    {
      return procedure (a == null ? null : SlotHelper.createInternationalStringType (a));
    }

    @Nonnull
    public final IMPLTYPE procedure (@Nullable final InternationalStringType a)
    {
      m_aProcedure = a;
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE consentToken (@Nullable final String s)
    {
      m_sConsentToken = s;
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE datasetIdentifier (@Nullable final String s)
    {
      m_sDatasetIdentifier = s;
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE dataConsumer (@Nullable final Consumer <? super AgentPojo.Builder> a)
    {
      if (a != null)
      {
        final AgentPojo.Builder aBuilder = AgentPojo.builder ();
        a.accept (aBuilder);
        dataConsumer (aBuilder.build ());
      }
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE dataConsumer (@Nullable final AgentType a)
    {
      return dataConsumer (a == null ? null : AgentPojo.builder (a));
    }

    @Nonnull
    public final IMPLTYPE dataConsumer (@Nullable final AgentPojo.Builder a)
    {
      return dataConsumer (a == null ? null : a.build ());
    }

    @Nonnull
    public final IMPLTYPE dataConsumer (@Nullable final AgentPojo a)
    {
      m_aDataConsumer = a;
      return thisAsT ();
    }

    /**
     * Set a legal person as data subject. This method nulls an eventually set
     * natural person data subject.
     *
     * @param a
     *        The value to set. May be <code>null</code>.
     * @return this for chaining.
     */
    @Nonnull
    public final IMPLTYPE dataSubjectBusiness (@Nullable final Consumer <? super BusinessPojo.Builder> a)
    {
      if (a != null)
      {
        final BusinessPojo.Builder aBuilder = BusinessPojo.builder ();
        a.accept (aBuilder);
        dataSubject (aBuilder.build ());
      }
      return thisAsT ();
    }

    /**
     * Set a legal person as data subject. This method nulls an eventually set
     * natural person data subject.
     *
     * @param a
     *        The value to set. May be <code>null</code>.
     * @return this for chaining.
     */
    @Nonnull
    public final IMPLTYPE dataSubject (@Nullable final CoreBusinessType a)
    {
      return dataSubject (a == null ? null : BusinessPojo.builder (a));
    }

    /**
     * Set a legal person as data subject. This method nulls an eventually set
     * natural person data subject.
     *
     * @param a
     *        The value to set. May be <code>null</code>.
     * @return this for chaining.
     */
    @Nonnull
    public final IMPLTYPE dataSubject (@Nullable final BusinessPojo.Builder a)
    {
      return dataSubject (a == null ? null : a.build ());
    }

    /**
     * Set a legal person as data subject. This method nulls an eventually set
     * natural person data subject.
     *
     * @param a
     *        The value to set. May be <code>null</code>.
     * @return this for chaining.
     */
    @Nonnull
    public final IMPLTYPE dataSubject (@Nullable final BusinessPojo a)
    {
      m_aDataSubjectLegalPerson = a;
      m_aDataSubjectNaturalPerson = null;
      return thisAsT ();
    }

    /**
     * Set a natural person as data subject. This method nulls an eventually set
     * legal person data subject.
     *
     * @param a
     *        The value to set. May be <code>null</code>.
     * @return this for chaining.
     */
    @Nonnull
    public final IMPLTYPE dataSubjectPerson (@Nullable final Consumer <? super PersonPojo.Builder> a)
    {
      if (a != null)
      {
        final PersonPojo.Builder aBuilder = PersonPojo.builder ();
        a.accept (aBuilder);
        dataSubject (aBuilder.build ());
      }
      return thisAsT ();
    }

    /**
     * Set a natural person as data subject. This method nulls an eventually set
     * legal person data subject.
     *
     * @param a
     *        The value to set. May be <code>null</code>.
     * @return this for chaining.
     */
    @Nonnull
    public final IMPLTYPE dataSubject (@Nullable final CorePersonType a)
    {
      return dataSubject (a == null ? null : PersonPojo.builder (a));
    }

    /**
     * Set a natural person as data subject. This method nulls an eventually set
     * legal person data subject.
     *
     * @param a
     *        The value to set. May be <code>null</code>.
     * @return this for chaining.
     */
    @Nonnull
    public final IMPLTYPE dataSubject (@Nullable final PersonPojo.Builder a)
    {
      return dataSubject (a == null ? null : a.build ());
    }

    /**
     * Set a natural person as data subject. This method nulls an eventually set
     * legal person data subject.
     *
     * @param a
     *        The value to set. May be <code>null</code>.
     * @return this for chaining.
     */
    @Nonnull
    public final IMPLTYPE dataSubject (@Nullable final PersonPojo a)
    {
      m_aDataSubjectLegalPerson = null;
      m_aDataSubjectNaturalPerson = a;
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE authorizedRepresentative (@Nullable final Consumer <? super PersonPojo.Builder> a)
    {
      if (a != null)
      {
        final PersonPojo.Builder aBuilder = PersonPojo.builder ();
        a.accept (aBuilder);
        authorizedRepresentative (aBuilder.build ());
      }
      return thisAsT ();
    }

    @Nonnull
    public final IMPLTYPE authorizedRepresentative (@Nullable final CorePersonType a)
    {
      return authorizedRepresentative (a == null ? null : PersonPojo.builder (a));
    }

    @Nonnull
    public final IMPLTYPE authorizedRepresentative (@Nullable final PersonPojo.Builder a)
    {
      return authorizedRepresentative (a == null ? null : a.build ());
    }

    @Nonnull
    public final IMPLTYPE authorizedRepresentative (@Nullable final PersonPojo a)
    {
      m_aAuthorizedRepresentative = a;
      return thisAsT ();
    }

    @OverridingMethodsMustInvokeSuper
    public void checkConsistency ()
    {
      if (m_eQueryDefinition == null)
        throw new IllegalStateException ("Query Definition must be present");
      if (StringHelper.hasNoText (m_sRequestID))
        throw new IllegalStateException ("ID must be present");
      if (m_eResponseOption == null)
        throw new IllegalStateException ("Response Option must be present");
      if (StringHelper.hasNoText (m_sSpecificationIdentifier))
        throw new IllegalStateException ("SpecificationIdentifier must be present");
      if (m_aIssueDateTime == null)
        throw new IllegalStateException ("Issue Date Time must be present");
      if (m_aDataConsumer == null)
        throw new IllegalStateException ("Cata Consumer must be present");

      if (m_aDataSubjectLegalPerson == null && m_aDataSubjectNaturalPerson == null)
      {
        // No DS is present
        if (!m_eQueryDefinition.isDataSujectOptional ())
          throw new IllegalStateException ("Data Subject must be present");
      }
      else
      {
        // More than one DS is present?
        if (m_aDataSubjectLegalPerson != null && m_aDataSubjectNaturalPerson != null)
          throw new IllegalStateException ("Data Subject MUST be either legal person OR natural person");
      }
    }

    @Nonnull
    public abstract EDMRequest build ();
  }

  /**
   * Builder for a "Document by ID request". Request 1 document directly.
   *
   * @author Philip Helger
   */
  public static class BuilderDocumentByID extends AbstractBuilder <BuilderDocumentByID>
  {
    private String m_sDocumentID;

    protected BuilderDocumentByID ()
    {
      super (EDE4AQueryDefinitionType.DOCUMENT_BY_ID);
    }

    @Nonnull
    public BuilderDocumentByID documentID (@Nullable final String s)
    {
      m_sDocumentID = s;
      return this;
    }

    @Override
    public void checkConsistency ()
    {
      super.checkConsistency ();

      if (m_sDocumentID == null)
        throw new IllegalStateException ("A Query Definition of type 'GetObjectByID' must contain a Document ID");
    }

    @Override
    @Nonnull
    public EDMRequest build ()
    {
      checkConsistency ();

      return new EDMRequest (m_eQueryDefinition,
                             m_sRequestID,
                             m_eResponseOption,
                             m_sSpecificationIdentifier,
                             m_aIssueDateTime,
                             m_aProcedure,
                             m_aDataConsumer,
                             m_sConsentToken,
                             m_sDatasetIdentifier,
                             m_aDataSubjectLegalPerson,
                             m_aDataSubjectNaturalPerson,
                             m_aAuthorizedRepresentative,
                             new EDMRequestPayloadDocumentID (m_sDocumentID));
    }
  }

  private static void _applySlots (@Nonnull final SlotType aSlot, @Nonnull final EDMRequest.AbstractBuilder <?> aBuilder)
  {
    final String sName = aSlot.getName ();
    final ValueType aSlotValue = aSlot.getSlotValue ();
    switch (sName)
    {
      case SlotSpecificationIdentifier.NAME:
        if (aSlotValue instanceof StringValueType)
        {
          final String sValue = ((StringValueType) aSlotValue).getValue ();
          aBuilder.specificationIdentifier (sValue);
        }
        break;
      case SlotIssueDateTime.NAME:
        if (aSlotValue instanceof DateTimeValueType)
        {
          final LocalDateTime aCal = ((DateTimeValueType) aSlotValue).getValue ();
          aBuilder.issueDateTime (aCal);
        }
        break;
      case SlotProcedure.NAME:
        if (aSlotValue instanceof InternationalStringValueType)
        {
          final InternationalStringType aIntString = ((InternationalStringValueType) aSlotValue).getValue ();
          aBuilder.procedure (aIntString);
        }
        break;
      case SlotConsentToken.NAME:
        if (aSlotValue instanceof StringValueType)
        {
          final String sValue = ((StringValueType) aSlotValue).getValue ();
          aBuilder.consentToken (sValue);
        }
        break;
      case SlotDatasetIdentifier.NAME:
        if (aSlotValue instanceof StringValueType)
        {
          final String sValue = ((StringValueType) aSlotValue).getValue ();
          aBuilder.datasetIdentifier (sValue);
        }
        break;
      case SlotDataConsumer.NAME:
        if (aSlotValue instanceof AnyValueType)
        {
          final Node aAny = (Node) ((AnyValueType) aSlotValue).getAny ();
          aBuilder.dataConsumer (AgentPojo.builder (new AgentMarshaller ().read (aAny)));
        }
        break;
      case SlotDataSubjectLegalPerson.NAME:
        if (aSlotValue instanceof AnyValueType)
        {
          final Node aAny = (Node) ((AnyValueType) aSlotValue).getAny ();
          aBuilder.dataSubject (BusinessPojo.builder (new BusinessMarshaller ().read (aAny)));
        }
        break;
      case SlotDataSubjectNaturalPerson.NAME:
        if (aSlotValue instanceof AnyValueType)
        {
          final Node aAny = (Node) ((AnyValueType) aSlotValue).getAny ();
          aBuilder.dataSubject (PersonPojo.builder (new PersonMarshaller ().read (aAny)));
        }
        break;
      case SlotAuthorizedRepresentative.NAME:
        if (aSlotValue instanceof AnyValueType)
        {
          final Node aAny = (Node) ((AnyValueType) aSlotValue).getAny ();
          aBuilder.authorizedRepresentative (PersonPojo.builder (new PersonMarshaller ().read (aAny)));
        }
        break;
      case SlotId.NAME:
        if (aSlotValue instanceof StringValueType)
        {
          final String sValue = ((StringValueType) aSlotValue).getValue ();
          ((EDMRequest.BuilderDocumentByID) aBuilder).documentID (sValue);
        }
        break;
      default:
        throw new IllegalStateException ("Found unsupported slot '" + sName + "'");
    }
  }

  @Nonnull
  public static EDMRequest create (@Nonnull final QueryRequest aQueryRequest)
  {
    ValueEnforcer.notNull (aQueryRequest, "QueryRequest");
    final QueryType aQuery = aQueryRequest.getQuery ();
    ValueEnforcer.notNull (aQuery, "QueryRequest.Query");

    final ICommonsSet <String> aQuerySlotNames = new CommonsHashSet <> (aQuery.getSlot (), SlotType::getName);

    // Enforce a default response option
    final EDMRequest.AbstractBuilder <?> aBuilder;
    if (aQuerySlotNames.contains (SlotId.NAME))
      aBuilder = builderDocumentByID ();
    else
      throw new IllegalStateException ("Cannot read this QueryRequest as a DE4A EDM request");

    // Request ID
    aBuilder.id (aQueryRequest.getId ());

    // Top level slots
    for (final SlotType aSlot : aQueryRequest.getSlot ())
      _applySlots (aSlot, aBuilder);

    // Query slots
    for (final SlotType aSlot : aQuery.getSlot ())
      if (aSlot != null)
        _applySlots (aSlot, aBuilder);

    // Default response option is "CONTAINED" for backwards compatibility from
    // beta3 to beta2
    EDE4AResponseOptionType eResponseOption = null;
    final ResponseOptionType aResponseOption = aQueryRequest.getResponseOption ();
    if (aResponseOption != null && aResponseOption.getReturnType () != null)
    {
      eResponseOption = EDE4AResponseOptionType.getFromIDOrNull (aResponseOption.getReturnType ());
    }
    aBuilder.responseOption (eResponseOption != null ? eResponseOption : EDE4AResponseOptionType.INLINE);

    return aBuilder.build ();
  }
}
