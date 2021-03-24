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
package eu.de4a.iem.model;

import java.time.LocalDate;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.Immutable;
import javax.xml.datatype.XMLGregorianCalendar;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.util.PDTXMLConverter;

/**
 * Representation of a "Person"
 *
 * @author Philip Helger
 */
@Immutable
public class PersonPojo
{
  private final String m_sID;
  private final String m_sIDSchemeID;
  private final String m_sFamilyName;
  private final String m_sGivenName;
  private final String m_sGenderCode;
  private final String m_sBirthName;
  private final LocalDate m_aBirthDate;
  private final String m_sBirthTown;
  private final AddressPojo m_aAddress;

  public PersonPojo (@Nonnull final String sID,
                     @Nonnull final String sIDSchemeID,
                     @Nonnull final String sFamilyName,
                     @Nonnull final String sGivenName,
                     @Nullable final String sGenderCode,
                     @Nullable final String sBirthName,
                     @Nonnull final LocalDate aBirthDate,
                     @Nullable final String sBirthTown,
                     @Nullable final AddressPojo aAddress)
  {
    ValueEnforcer.notNull (sID, "ID");
    ValueEnforcer.notNull (sIDSchemeID, "IDSchemeID");
    ValueEnforcer.notNull (sFamilyName, "FamilyName");
    ValueEnforcer.notNull (sGivenName, "GivenName");
    ValueEnforcer.notNull (aBirthDate, "BirthDate");

    m_sID = sID;
    m_sIDSchemeID = sIDSchemeID;
    m_sFamilyName = sFamilyName;
    m_sGivenName = sGivenName;
    m_sGenderCode = sGenderCode;
    m_sBirthName = sBirthName;
    m_aBirthDate = aBirthDate;
    m_sBirthTown = sBirthTown;
    m_aAddress = aAddress;
  }

  @Nonnull
  public final String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public final String getIDSchemeID ()
  {
    return m_sIDSchemeID;
  }

  @Nonnull
  public final String getFamilyName ()
  {
    return m_sFamilyName;
  }

  @Nonnull
  public final String getGivenName ()
  {
    return m_sGivenName;
  }

  @Nullable
  public final String getGenderCode ()
  {
    return m_sGenderCode;
  }

  @Nullable
  public final String getBirthName ()
  {
    return m_sBirthName;
  }

  @Nonnull
  public final LocalDate getBirthDate ()
  {
    return m_aBirthDate;
  }

  @Nullable
  public final String getBirthTown ()
  {
    return m_sBirthTown;
  }

  @Nullable
  public final AddressPojo getAddress ()
  {
    return m_aAddress;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PersonPojo rhs = (PersonPojo) o;
    return EqualsHelper.equals (m_sID, rhs.m_sID) &&
           EqualsHelper.equals (m_sIDSchemeID, rhs.m_sIDSchemeID) &&
           EqualsHelper.equals (m_sFamilyName, rhs.m_sFamilyName) &&
           EqualsHelper.equals (m_sGivenName, rhs.m_sGivenName) &&
           EqualsHelper.equals (m_sGenderCode, rhs.m_sGenderCode) &&
           EqualsHelper.equals (m_sBirthName, rhs.m_sBirthName) &&
           EqualsHelper.equals (m_aBirthDate, rhs.m_aBirthDate) &&
           EqualsHelper.equals (m_sBirthTown, rhs.m_sBirthTown) &&
           EqualsHelper.equals (m_aAddress, rhs.m_aAddress);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sID)
                                       .append (m_sIDSchemeID)
                                       .append (m_sFamilyName)
                                       .append (m_sGivenName)
                                       .append (m_sGenderCode)
                                       .append (m_sBirthName)
                                       .append (m_aBirthDate)
                                       .append (m_sBirthTown)
                                       .append (m_aAddress)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", m_sID)
                                       .append ("IDSchemeID", m_sIDSchemeID)
                                       .append ("FamilyName", m_sFamilyName)
                                       .append ("GivenName", m_sGivenName)
                                       .append ("GenderCode", m_sGenderCode)
                                       .append ("BirthName", m_sBirthName)
                                       .append ("BirthDate", m_aBirthDate)
                                       .append ("BirthTown", m_sBirthTown)
                                       .append ("Address", m_aAddress)
                                       .getToString ();
  }

  @Nonnull
  public static Builder builder ()
  {
    return new Builder ();
  }

  /**
   * A builder for this class
   *
   * @author Philip Helger
   */
  public static class Builder
  {
    private String m_sID;
    private String m_sIDSchemeID;
    private String m_sFamilyName;
    private String m_sGivenName;
    private String m_sGenderCode;
    private String m_sBirthName;
    private LocalDate m_aBirthDate;
    private String m_sBirthTown;
    private AddressPojo m_aAddress;

    public Builder ()
    {}

    @Nonnull
    public Builder id (@Nullable final String s)
    {
      m_sID = s;
      return this;
    }

    @Nonnull
    public Builder idSchemeID (@Nullable final EDE4AIdentifierType e)
    {
      return idSchemeID (e == null ? null : e.getID ());
    }

    @Nonnull
    public Builder idSchemeID (@Nullable final String s)
    {
      m_sIDSchemeID = s;
      return this;
    }

    /**
     * Alias for {@link #familyName(String)}
     *
     * @param s
     *        Last or family name
     * @return this for chaining
     */
    @Nonnull
    public Builder lastName (@Nullable final String s)
    {
      return familyName (s);
    }

    @Nonnull
    public Builder familyName (@Nullable final String s)
    {
      m_sFamilyName = s;
      return this;
    }

    /**
     * Alias for {@link #givenName(String)}
     *
     * @param s
     *        First or given name
     * @return this for chaining
     */
    @Nonnull
    public Builder firstName (@Nullable final String s)
    {
      return givenName (s);
    }

    @Nonnull
    public Builder givenName (@Nullable final String s)
    {
      m_sGivenName = s;
      return this;
    }

    @Nonnull
    public Builder genderCode (@Nullable final EDE4AGenderCode e)
    {
      return genderCode (e == null ? null : e.getID ());
    }

    @Nonnull
    public Builder genderCode (@Nullable final String s)
    {
      m_sGenderCode = s;
      return this;
    }

    @Nonnull
    public Builder birthName (@Nullable final String s)
    {
      m_sBirthName = s;
      return this;
    }

    @Nonnull
    public Builder birthDate (@Nullable final XMLGregorianCalendar a)
    {
      return birthDate (PDTXMLConverter.getLocalDate (a));
    }

    @Nonnull
    public Builder birthDate (@Nullable final LocalDate a)
    {
      m_aBirthDate = a;
      return this;
    }

    @Nonnull
    public Builder birthTown (@Nullable final String s)
    {
      m_sBirthTown = s;
      return this;
    }

    @Nonnull
    public Builder address (@Nullable final Consumer <? super AddressPojo.Builder> a)
    {
      if (a != null)
      {
        final AddressPojo.Builder aBuilder = AddressPojo.builder ();
        a.accept (aBuilder);
        address (aBuilder);
      }
      return this;
    }

    @Nonnull
    public Builder address (@Nullable final AddressPojo.Builder a)
    {
      return address (a == null ? null : a.build ());
    }

    @Nonnull
    public Builder address (@Nullable final AddressPojo a)
    {
      m_aAddress = a;
      return this;
    }

    @OverridingMethodsMustInvokeSuper
    public void checkConsistency ()
    {
      if (m_sID == null)
        throw new IllegalStateException ("ID must be present");
      if (m_sIDSchemeID == null)
        throw new IllegalStateException ("ID SchemeID must be present");
      if (m_sFamilyName == null)
        throw new IllegalStateException ("Family Name must be present");
      if (m_sGivenName == null)
        throw new IllegalStateException ("Given Name must be present");
      if (m_aBirthDate == null)
        throw new IllegalStateException ("Birth Date must be present");
    }

    @Nonnull
    public PersonPojo build ()
    {
      checkConsistency ();

      return new PersonPojo (m_sID,
                             m_sIDSchemeID,
                             m_sFamilyName,
                             m_sGivenName,
                             m_sGenderCode,
                             m_sBirthName,
                             m_aBirthDate,
                             m_sBirthTown,
                             m_aAddress);
    }
  }
}
