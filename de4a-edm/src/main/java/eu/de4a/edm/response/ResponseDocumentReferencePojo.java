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
package eu.de4a.edm.response;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.regrep.rim.ObjectRefType;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.rim.ValueType;

/**
 * Represents a single response object reference
 *
 * @author Konstantinos Douloudis
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
public class ResponseDocumentReferencePojo implements IEDMResponsePayloadDocumentReference
{
  private final String m_sRegistryObjectID;

  public ResponseDocumentReferencePojo (@Nonnull @Nonempty final String sRegistryObjectID)
  {
    ValueEnforcer.notEmpty (sRegistryObjectID, "RegistryObjectID");

    m_sRegistryObjectID = sRegistryObjectID;
  }

  @Nonnull
  @Nonempty
  public final String getRegistryObjectID ()
  {
    return m_sRegistryObjectID;
  }

  @Nonnull
  @Override
  public ObjectRefType getAsObjectRef ()
  {
    final ObjectRefType ret = new ObjectRefType ();
    ret.setId (m_sRegistryObjectID);

    return ret;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ResponseDocumentReferencePojo rhs = (ResponseDocumentReferencePojo) o;
    return EqualsHelper.equals (m_sRegistryObjectID, rhs.m_sRegistryObjectID);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sRegistryObjectID).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("RegistryObjectID", m_sRegistryObjectID).getToString ();
  }

  @Nonnull
  public static Builder builder ()
  {
    return new Builder ();
  }

  public static class Builder
  {
    private String m_sRegistryObjectID;

    protected Builder ()
    {}

    @Nonnull
    public Builder registryObjectID (@Nullable final String s)
    {
      m_sRegistryObjectID = s;
      return this;
    }

    @Nonnull
    public Builder randomRegistryObjectID ()
    {
      return registryObjectID (UUID.randomUUID ().toString ());
    }

    public void checkConsistency ()
    {
      if (StringHelper.hasNoText (m_sRegistryObjectID))
        throw new IllegalStateException ("RegistryObjectID must be present");
    }

    @Nonnull
    public ResponseDocumentReferencePojo build ()
    {
      checkConsistency ();

      return new ResponseDocumentReferencePojo (m_sRegistryObjectID);
    }
  }

  private static void _applySlots (@Nonnull final SlotType aSlot, @Nonnull final Builder aBuilder)
  {
    final String sName = aSlot.getName ();
    final ValueType aSlotValue = aSlot.getSlotValue ();
    switch (sName)
    {
      default:
        throw new IllegalStateException ("Found unsupported slot '" + sName + "'");
    }
  }

  @Nonnull
  public static Builder builder (@Nullable final ObjectRefType a)
  {
    final Builder ret = new Builder ();
    if (a != null)
    {
      ret.registryObjectID (a.getId ());
      for (final SlotType aSlot : a.getSlot ())
        _applySlots (aSlot, ret);
    }
    return ret;
  }
}
