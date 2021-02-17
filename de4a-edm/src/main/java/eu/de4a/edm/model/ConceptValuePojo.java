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
package eu.de4a.edm.model;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.datatype.XMLGregorianCalendar;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.math.MathHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.util.PDTXMLConverter;

import eu.de4a.edm.error.IDE4AErrorCode;
import eu.de4a.edm.jaxb.cccev.CCCEVValueType;
import eu.de4a.edm.jaxb.cv.cbc.TextType;
import eu.de4a.edm.xml.cccev.CCCEVValueHelper;

/**
 * Represents a single "Value" in a concept. Usually only used in responses.
 *
 * @author Philip Helger
 */
@Immutable
public class ConceptValuePojo
{
  private final String m_sIdentifier;
  private final AmountPojo m_aAmount;
  private final String m_sCode;
  private final LocalDate m_aDate;
  private final Boolean m_aIndicator;
  private final MeasurePojo m_aMeasure;
  private final BigDecimal m_aNumeric;
  private final PeriodPojo m_aPeriod;
  private final QuantityPojo m_aQuantity;
  private final ICommonsList <String> m_aText = new CommonsArrayList <> ();
  private final LocalTime m_aTime;
  private final String m_sURI;
  private final String m_sErrorCode;

  public ConceptValuePojo (@Nullable final String sIdentifier,
                           @Nullable final AmountPojo aAmount,
                           @Nullable final String sCode,
                           @Nullable final LocalDate aDate,
                           @Nullable final Boolean aIndicator,
                           @Nullable final MeasurePojo aMeasure,
                           @Nullable final BigDecimal aNumeric,
                           @Nullable final PeriodPojo aPeriod,
                           @Nullable final QuantityPojo aQuantity,
                           @Nullable final List <String> aText,
                           @Nullable final LocalTime aTime,
                           @Nullable final String sURI,
                           @Nullable final String sErrorCode)
  {
    m_sIdentifier = sIdentifier;
    m_aAmount = aAmount;
    m_sCode = sCode;
    m_aDate = aDate;
    m_aIndicator = aIndicator;
    m_aMeasure = aMeasure;
    m_aNumeric = aNumeric;
    m_aPeriod = aPeriod;
    m_aQuantity = aQuantity;
    if (aText != null)
      m_aText.addAll (aText);
    m_aTime = aTime;
    m_sURI = sURI;
    m_sErrorCode = sErrorCode;
  }

  @Nullable
  public final String getIdentifier ()
  {
    return m_sIdentifier;
  }

  @Nullable
  public final AmountPojo getAmount ()
  {
    return m_aAmount;
  }

  @Nullable
  public final String getCode ()
  {
    return m_sCode;
  }

  @Nullable
  public final LocalDate getDate ()
  {
    return m_aDate;
  }

  @Nullable
  public final Boolean getBoolean ()
  {
    return m_aIndicator;
  }

  @Nullable
  public final MeasurePojo getMeasure ()
  {
    return m_aMeasure;
  }

  @Nullable
  public final BigDecimal getNumeric ()
  {
    return m_aNumeric;
  }

  @Nullable
  public final PeriodPojo getPeriod ()
  {
    return m_aPeriod;
  }

  @Nullable
  public final QuantityPojo getQuantity ()
  {
    return m_aQuantity;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <String> text ()
  {
    return m_aText;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <String> getAllTexts ()
  {
    return m_aText.getClone ();
  }

  @Nullable
  public final LocalTime getTime ()
  {
    return m_aTime;
  }

  @Nullable
  public final String getURI ()
  {
    return m_sURI;
  }

  @Nullable
  public final String getErrorCode ()
  {
    return m_sErrorCode;
  }

  public final boolean isErrorCodeValue ()
  {
    return StringHelper.hasText (m_sErrorCode);
  }

  /**
   * @return The string representation of this concept value or
   *         <code>null</code> if no value is part.
   */
  @Nullable
  public String getAsString ()
  {
    if (m_sIdentifier != null)
      return m_sIdentifier;
    if (m_aAmount != null)
      return m_aAmount.getAsString ();
    if (m_sCode != null)
      return m_sCode;
    if (m_aDate != null)
      return m_aDate.toString ();
    if (m_aIndicator != null)
      return m_aIndicator.toString ();
    if (m_aMeasure != null)
      return m_aMeasure.getAsString ();
    if (m_aNumeric != null)
      return m_aNumeric.toString ();
    if (m_aPeriod != null)
      return m_aPeriod.getAsString ();
    if (m_aQuantity != null)
      return m_aQuantity.getAsString ();
    if (m_aText.isNotEmpty ())
      return StringHelper.getImploded ('\n', m_aText);
    if (m_aTime != null)
      return m_aTime.toString ();
    if (m_sURI != null)
      return m_sURI;
    if (m_sErrorCode != null)
      return m_sErrorCode;
    return null;
  }

  @Nullable
  public CCCEVValueType getAsCCCEVValueType ()
  {
    if (m_sIdentifier != null)
      return CCCEVValueHelper.createID (m_sIdentifier);
    if (m_aAmount != null)
      return CCCEVValueHelper.create (m_aAmount.getAsAmount ());
    if (m_sCode != null)
      return CCCEVValueHelper.createCode (m_sCode);
    if (m_aDate != null)
      return CCCEVValueHelper.create (m_aDate);
    if (m_aIndicator != null)
      return CCCEVValueHelper.create (m_aIndicator);
    if (m_aMeasure != null)
      return CCCEVValueHelper.create (m_aMeasure.getAsMeasure ());
    if (m_aNumeric != null)
      return CCCEVValueHelper.create (m_aNumeric);
    if (m_aPeriod != null)
      return CCCEVValueHelper.create (m_aPeriod.getAsPeriod ());
    if (m_aQuantity != null)
      return CCCEVValueHelper.create (m_aQuantity.getAsQuantity ());
    if (m_aText.isNotEmpty ())
      return CCCEVValueHelper.createText (m_aText);
    if (m_aTime != null)
      return CCCEVValueHelper.create (m_aTime);
    if (m_sURI != null)
      return CCCEVValueHelper.createURI (m_sURI);
    if (m_sErrorCode != null)
      return CCCEVValueHelper.createError (m_sErrorCode);
    return null;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ConceptValuePojo rhs = (ConceptValuePojo) o;
    return EqualsHelper.equals (m_sIdentifier, rhs.m_sIdentifier) &&
           EqualsHelper.equals (m_aAmount, rhs.m_aAmount) &&
           EqualsHelper.equals (m_sCode, rhs.m_sCode) &&
           EqualsHelper.equals (m_aDate, rhs.m_aDate) &&
           EqualsHelper.equals (m_aIndicator, rhs.m_aIndicator) &&
           EqualsHelper.equals (m_aMeasure, rhs.m_aMeasure) &&
           EqualsHelper.equals (m_aNumeric, rhs.m_aNumeric) &&
           EqualsHelper.equals (m_aPeriod, rhs.m_aPeriod) &&
           EqualsHelper.equals (m_aQuantity, rhs.m_aQuantity) &&
           EqualsHelper.equals (m_aText, rhs.m_aText) &&
           EqualsHelper.equals (m_aTime, rhs.m_aTime) &&
           EqualsHelper.equals (m_sURI, rhs.m_sURI) &&
           EqualsHelper.equals (m_sErrorCode, rhs.m_sErrorCode);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sIdentifier)
                                       .append (m_aAmount)
                                       .append (m_sCode)
                                       .append (m_aDate)
                                       .append (m_aIndicator)
                                       .append (m_aMeasure)
                                       .append (m_aNumeric)
                                       .append (m_aPeriod)
                                       .append (m_aQuantity)
                                       .append (m_aText)
                                       .append (m_aTime)
                                       .append (m_sURI)
                                       .append (m_sErrorCode)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("Identifier", m_sIdentifier)
                                       .appendIfNotNull ("Amount", m_aAmount)
                                       .appendIfNotNull ("Code", m_sCode)
                                       .appendIfNotNull ("Date", m_aDate)
                                       .appendIfNotNull ("Indicator", m_aIndicator)
                                       .appendIfNotNull ("Measure", m_aMeasure)
                                       .appendIfNotNull ("Numeric", m_aNumeric)
                                       .appendIfNotNull ("Period", m_aPeriod)
                                       .appendIfNotNull ("Quantity", m_aQuantity)
                                       .appendIfNotNull ("Text", m_aText)
                                       .appendIfNotNull ("Time", m_aTime)
                                       .appendIfNotNull ("URI", m_sURI)
                                       .appendIfNotNull ("ErrorCode", m_sErrorCode)
                                       .getToString ();
  }

  @Nonnull
  public static Builder builder ()
  {
    return new Builder ();
  }

  @Nonnull
  public static Builder builder (@Nullable final CCCEVValueType a)
  {
    final Builder ret = new Builder ();
    if (a != null)
    {
      ret.identifier (a.getIdentifierValueValue ())
         .amount (a.getAmountValue () == null ? null : AmountPojo.builder (a.getAmountValue ()))
         .code (a.getCodeValueValue ())
         .date (a.getDateValueValue ())
         .indicator (a.getIndicatorValue () == null ? null : StringParser.parseBoolObj (a.getIndicatorValue ().getValue ()))
         .measure (a.getMeasureValue () == null ? null : MeasurePojo.builder (a.getMeasureValue ()))
         .numeric (a.getNumericValueValue ())
         .period (a.getPeriodValue () == null ? null : PeriodPojo.builder (a.getPeriodValue ()).build ())
         .quantity (a.getQuantityValue () == null ? null : QuantityPojo.builder (a.getQuantityValue ()))
         .text (new CommonsArrayList <> (a.getTextValue (), TextType::getValue))
         .time (a.getTimeValueValue ())
         .uri (a.getUriValueValue ())
         .errorCode (a.getErrorValue ());
    }
    return ret;
  }

  /**
   * A builder for this class
   *
   * @author Philip Helger
   */
  @NotThreadSafe
  public static class Builder
  {
    private String m_sIdentifier;
    private AmountPojo m_aAmount;
    private String m_sCode;
    private LocalDate m_aDate;
    private Boolean m_aIndicator;
    private MeasurePojo m_aMeasure;
    private BigDecimal m_aNumeric;
    private PeriodPojo m_aPeriod;
    private QuantityPojo m_aQuantity;
    private final ICommonsList <String> m_aText = new CommonsArrayList <> ();
    private LocalTime m_aTime;
    private String m_sURI;
    private String m_sErrorCode;

    public Builder ()
    {}

    @Nonnull
    public Builder identifier (@Nullable final String s)
    {
      m_sIdentifier = s;
      return this;
    }

    @Nonnull
    public Builder amount (@Nullable final Consumer <? super AmountPojo.Builder> a)
    {
      if (a != null)
      {
        final AmountPojo.Builder aBuilder = AmountPojo.builder ();
        a.accept (aBuilder);
        amount (aBuilder);
      }
      return this;
    }

    @Nonnull
    public Builder amount (@Nullable final BigDecimal aValue, @Nullable final String sCurrencyID)
    {
      return amount (new AmountPojo (aValue, sCurrencyID));
    }

    @Nonnull
    public Builder amount (@Nullable final AmountPojo.Builder a)
    {
      return amount (a == null ? null : a.build ());
    }

    @Nonnull
    public Builder amount (@Nullable final AmountPojo a)
    {
      m_aAmount = a;
      return this;
    }

    @Nonnull
    public Builder code (@Nullable final String s)
    {
      m_sCode = s;
      return this;
    }

    @Nonnull
    public Builder date (@Nullable final XMLGregorianCalendar a)
    {
      return date (PDTXMLConverter.getLocalDate (a));
    }

    @Nonnull
    public Builder date (@Nullable final LocalDate a)
    {
      m_aDate = a;
      return this;
    }

    @Nonnull
    public Builder indicator (final boolean b)
    {
      return indicator (Boolean.valueOf (b));
    }

    @Nonnull
    public Builder indicator (@Nullable final Boolean a)
    {
      m_aIndicator = a;
      return this;
    }

    @Nonnull
    public Builder measure (@Nullable final BigDecimal aValue, @Nullable final String sUnitCode)
    {
      return measure (new MeasurePojo (aValue, sUnitCode));
    }

    @Nonnull
    public Builder measure (@Nullable final Consumer <? super MeasurePojo.Builder> a)
    {
      if (a != null)
      {
        final MeasurePojo.Builder aBuilder = MeasurePojo.builder ();
        a.accept (aBuilder);
        measure (aBuilder);
      }
      return this;
    }

    @Nonnull
    public Builder measure (@Nullable final MeasurePojo.Builder a)
    {
      return measure (a == null ? null : a.build ());
    }

    @Nonnull
    public Builder measure (@Nullable final MeasurePojo a)
    {
      m_aMeasure = a;
      return this;
    }

    @Nonnull
    public Builder numeric (final long n)
    {
      return numeric (MathHelper.toBigDecimal (n));
    }

    @Nonnull
    public Builder numeric (final double d)
    {
      return numeric (MathHelper.toBigDecimal (d));
    }

    @Nonnull
    public Builder numeric (@Nullable final BigDecimal a)
    {
      m_aNumeric = a;
      return this;
    }

    @Nonnull
    public Builder period (@Nullable final LocalDateTime aStartDate, @Nullable final LocalDateTime aEndDate)
    {
      return period (PeriodPojo.builder ().startDateTime (aStartDate).endDateTime (aEndDate));
    }

    @Nonnull
    public Builder period (@Nullable final Consumer <? super PeriodPojo.Builder> a)
    {
      if (a != null)
      {
        final PeriodPojo.Builder aBuilder = PeriodPojo.builder ();
        a.accept (aBuilder);
        period (aBuilder);
      }
      return this;
    }

    @Nonnull
    public Builder period (@Nullable final PeriodPojo.Builder a)
    {
      return period (a == null ? null : a.build ());
    }

    @Nonnull
    public Builder period (@Nullable final PeriodPojo a)
    {
      m_aPeriod = a;
      return this;
    }

    @Nonnull
    public Builder quantity (@Nullable final BigDecimal aValue, @Nullable final String sUnitCode)
    {
      return quantity (new QuantityPojo (aValue, sUnitCode));
    }

    @Nonnull
    public Builder quantity (@Nullable final Consumer <? super QuantityPojo.Builder> a)
    {
      if (a != null)
      {
        final QuantityPojo.Builder aBuilder = QuantityPojo.builder ();
        a.accept (aBuilder);
        quantity (aBuilder);
      }
      return this;
    }

    @Nonnull
    public Builder quantity (@Nullable final QuantityPojo.Builder a)
    {
      return quantity (a == null ? null : a.build ());
    }

    @Nonnull
    public Builder quantity (@Nullable final QuantityPojo a)
    {
      m_aQuantity = a;
      return this;
    }

    @Nonnull
    public Builder text (@Nullable final String... a)
    {
      m_aText.setAll (a);
      return this;
    }

    @Nonnull
    public Builder text (@Nullable final Iterable <String> a)
    {
      m_aText.setAll (a);
      return this;
    }

    @Nonnull
    public <T> Builder text (@Nullable final Iterable <? extends T> a, @Nonnull final Function <? super T, String> aMapper)
    {
      m_aText.setAllMapped (a, aMapper);
      return this;
    }

    @Nonnull
    public Builder time (@Nullable final XMLGregorianCalendar a)
    {
      return time (PDTXMLConverter.getLocalTime (a));
    }

    @Nonnull
    public Builder time (@Nullable final LocalTime a)
    {
      m_aTime = a == null ? null : a.truncatedTo (ChronoUnit.MILLIS);
      return this;
    }

    @Nonnull
    public Builder uri (@Nullable final URI a)
    {
      return uri (a == null ? null : a.toString ());
    }

    @Nonnull
    public Builder uri (@Nullable final URL a)
    {
      return uri (a == null ? null : a.toExternalForm ());
    }

    @Nonnull
    public Builder uri (@Nullable final String s)
    {
      m_sURI = s;
      return this;
    }

    @Nonnull
    public Builder errorCode (@Nullable final IDE4AErrorCode a)
    {
      return errorCode (a == null ? null : a.getID ());
    }

    @Nonnull
    public Builder errorCode (@Nullable final String s)
    {
      m_sErrorCode = s;
      return this;
    }

    @OverridingMethodsMustInvokeSuper
    public void checkConsistency ()
    {
      int nCount = 0;
      if (m_sIdentifier != null)
        nCount++;
      if (m_aAmount != null)
        nCount++;
      if (m_sCode != null)
        nCount++;
      if (m_aDate != null)
        nCount++;
      if (m_aIndicator != null)
        nCount++;
      if (m_aMeasure != null)
        nCount++;
      if (m_aNumeric != null)
        nCount++;
      if (m_aPeriod != null)
        nCount++;
      if (m_aQuantity != null)
        nCount++;
      if (m_aText.isNotEmpty ())
        nCount++;
      if (m_aTime != null)
        nCount++;
      if (m_sURI != null)
        nCount++;
      if (m_sErrorCode != null)
        nCount++;
      if (nCount == 0)
        throw new IllegalStateException ("No value was provided to the Concept Value");
      if (nCount > 1)
        throw new IllegalStateException ("The Concept Value must have exactly one value");
    }

    @Nonnull
    public ConceptValuePojo build ()
    {
      checkConsistency ();

      return new ConceptValuePojo (m_sIdentifier,
                                   m_aAmount,
                                   m_sCode,
                                   m_aDate,
                                   m_aIndicator,
                                   m_aMeasure,
                                   m_aNumeric,
                                   m_aPeriod,
                                   m_aQuantity,
                                   m_aText,
                                   m_aTime,
                                   m_sURI,
                                   m_sErrorCode);
    }
  }
}
