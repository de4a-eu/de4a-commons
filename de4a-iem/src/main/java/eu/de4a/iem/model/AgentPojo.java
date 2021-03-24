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

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * Represents an "Agent"
 *
 * @author Philip Helger
 */
@Immutable
public class AgentPojo
{
  private final String m_sID;
  private final String m_sIDSchemeID;
  private final String m_sName;
  private final AddressPojo m_aAddress;

  public AgentPojo (@Nullable final String sID,
                    @Nullable final String sIDSchemeID,
                    @Nullable final String sName,
                    @Nullable final AddressPojo aAddress)
  {
    m_sID = sID;
    m_sIDSchemeID = sIDSchemeID;
    m_sName = sName;
    m_aAddress = aAddress;
  }

  @Nullable
  public final String getID ()
  {
    return m_sID;
  }

  @Nullable
  public final String getIDSchemeID ()
  {
    return m_sIDSchemeID;
  }

  @Nullable
  public final String getName ()
  {
    return m_sName;
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
    final AgentPojo rhs = (AgentPojo) o;
    return EqualsHelper.equals (m_sID, rhs.m_sID) &&
           EqualsHelper.equals (m_sIDSchemeID, rhs.m_sIDSchemeID) &&
           EqualsHelper.equals (m_sName, rhs.m_sName) &&
           EqualsHelper.equals (m_aAddress, rhs.m_aAddress);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sID).append (m_sIDSchemeID).append (m_sName).append (m_aAddress).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", m_sID)
                                       .append ("IDSchemeID", m_sIDSchemeID)
                                       .append ("Name", m_sName)
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
  @NotThreadSafe
  public static class Builder
  {
    private String m_sID;
    private String m_sIDSchemeID;
    private String m_sName;
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

    @Nonnull
    public Builder name (@Nullable final String s)
    {
      m_sName = s;
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

    @Nonnull
    public AgentPojo build ()
    {
      return new AgentPojo (m_sID, m_sIDSchemeID, m_sName, m_aAddress);
    }
  }
}
