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
package eu.de4a.edm.xml.cccev;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.math.MathHelper;

import eu.de4a.edm.jaxb.cccev.CCCEVValueType;
import eu.de4a.edm.jaxb.cv.cac.PeriodType;
import eu.de4a.edm.jaxb.cv.cbc.AmountType;
import eu.de4a.edm.jaxb.cv.cbc.CodeType;
import eu.de4a.edm.jaxb.cv.cbc.DateType;
import eu.de4a.edm.jaxb.cv.cbc.IDType;
import eu.de4a.edm.jaxb.cv.cbc.IndicatorType;
import eu.de4a.edm.jaxb.cv.cbc.MeasureType;
import eu.de4a.edm.jaxb.cv.cbc.NumericType;
import eu.de4a.edm.jaxb.cv.cbc.QuantityType;
import eu.de4a.edm.jaxb.cv.cbc.TextType;
import eu.de4a.edm.jaxb.cv.cbc.TimeType;
import eu.de4a.edm.jaxb.cv.cbc.URIType;

/**
 * Helper class to easily create {@link CCCEVValueType} objects from Java types.
 *
 * @author Philip Helger
 */
@Immutable
public final class CCCEVValueHelper
{
  private CCCEVValueHelper ()
  {}

  @Nonnull
  public static CCCEVValueType createID (@Nonnull final String s)
  {
    return create (new IDType (s));
  }

  @Nonnull
  public static CCCEVValueType create (@Nonnull final IDType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setIdentifierValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType createAmount (@Nullable final BigDecimal aValue, @Nullable final String sCurrencyID)
  {
    final AmountType a = new AmountType ();
    a.setValue (aValue);
    a.setCurrencyID (sCurrencyID);
    return create (a);
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final AmountType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setAmountValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType createCode (@Nullable final String sValue)
  {
    return create (new CodeType (sValue));
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final CodeType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setCodeValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final LocalDate a)
  {
    return create (a == null ? null : new DateType (a));
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final DateType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setDateValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final Boolean a)
  {
    return createIndicator (a == null ? null : a.toString ());
  }

  @Nonnull
  public static CCCEVValueType create (final boolean b)
  {
    return createIndicator (Boolean.toString (b));
  }

  @Nonnull
  public static CCCEVValueType createIndicator (@Nullable final String s)
  {
    final IndicatorType x = new IndicatorType ();
    x.setValue (s);
    return create (x);
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final IndicatorType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setIndicatorValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType createMeasure (@Nullable final BigDecimal aValue, @Nullable final String sUnitCode)
  {
    final MeasureType a = new MeasureType ();
    a.setValue (aValue);
    a.setUnitCode (sUnitCode);
    return create (a);
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final MeasureType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setMeasureValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType create (final long n)
  {
    return create (MathHelper.toBigDecimal (n));
  }

  @Nonnull
  public static CCCEVValueType create (final double d)
  {
    return create (MathHelper.toBigDecimal (d));
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final BigDecimal a)
  {
    return create (new NumericType (a));
  }

  @Nonnull
  public static CCCEVValueType create (@Nonnull final NumericType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setNumericValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType createQuantity (@Nullable final BigDecimal aValue, @Nullable final String sUnitCode)
  {
    final QuantityType a = new QuantityType ();
    a.setValue (aValue);
    a.setUnitCode (sUnitCode);
    return create (a);
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final QuantityType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setQuantityValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType createText (@Nullable final String... a)
  {
    return create (new CommonsArrayList <> (a, TextType::new));
  }

  @Nonnull
  public static CCCEVValueType createText (@Nullable final Collection <String> a)
  {
    return create (new CommonsArrayList <> (a, TextType::new));
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final TextType... a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    if (a != null)
      for (final TextType aItem : a)
        ret.addTextValue (aItem);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final Collection <TextType> a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    if (a != null)
      ret.getTextValue ().addAll (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final LocalTime a)
  {
    return create (a == null ? null : new TimeType (a));
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final TimeType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setTimeValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType createURI (@Nullable final String s)
  {
    return create (new URIType (s));
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final URIType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setUriValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType create (@Nullable final PeriodType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setPeriodValue (a);
    return ret;
  }

  @Nonnull
  public static CCCEVValueType createError (@Nullable final String sValue)
  {
    return createError (new CodeType (sValue));
  }

  @Nonnull
  public static CCCEVValueType createError (@Nullable final CodeType a)
  {
    final CCCEVValueType ret = new CCCEVValueType ();
    ret.setError (a);
    return ret;
  }
}
