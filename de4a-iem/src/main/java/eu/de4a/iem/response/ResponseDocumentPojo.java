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
package eu.de4a.iem.response;

import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.regrep.rim.ExtrinsicObjectType;
import com.helger.regrep.rim.SimpleLinkType;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.rim.ValueType;

import eu.de4a.iem.model.RepositoryItemRefPojo;

/**
 * Represents a single response object
 *
 * @author Konstantinos Douloudis
 * @author Philip Helger
 * @since 2.0.0-beta3
 */
public class ResponseDocumentPojo implements IEDMResponsePayloadDocument
{
  private final String m_sRegistryObjectID;
  private final RepositoryItemRefPojo m_aRepositoryItemRef;

  public ResponseDocumentPojo (@Nonnull @Nonempty final String sRegistryObjectID, @Nonnull final RepositoryItemRefPojo aRepositoryItemRef)
  {
    ValueEnforcer.notEmpty (sRegistryObjectID, "RegistryObjectID");
    ValueEnforcer.notNull (aRepositoryItemRef, "RepositoryItemRef");

    m_sRegistryObjectID = sRegistryObjectID;
    m_aRepositoryItemRef = aRepositoryItemRef;
  }

  @Nonnull
  @Nonempty
  public final String getRegistryObjectID ()
  {
    return m_sRegistryObjectID;
  }

  @Nonnull
  public final RepositoryItemRefPojo getRepositoryItemRef ()
  {
    return m_aRepositoryItemRef;
  }

  @Nonnull
  @Override
  public ExtrinsicObjectType getAsRegistryObject ()
  {
    final ExtrinsicObjectType ret = new ExtrinsicObjectType ();
    ret.setId (m_sRegistryObjectID);

    // Reference to AS4 artifact
    ret.setRepositoryItemRef (m_aRepositoryItemRef.getAsSimpleLink ());

    return ret;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ResponseDocumentPojo rhs = (ResponseDocumentPojo) o;
    return EqualsHelper.equals (m_sRegistryObjectID, rhs.m_sRegistryObjectID) &&
           EqualsHelper.equals (m_aRepositoryItemRef, rhs.m_aRepositoryItemRef);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sRegistryObjectID).append (m_aRepositoryItemRef).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("RegistryObjectID", m_sRegistryObjectID)
                                       .append ("RepositoryItemRef", m_aRepositoryItemRef)
                                       .getToString ();
  }

  @Nonnull
  public static Builder builder ()
  {
    return new Builder ();
  }

  public static class Builder
  {
    private String m_sRegistryObjectID;
    private RepositoryItemRefPojo m_aRepositoryItemRef;

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

    @Nonnull
    public Builder repositoryItemRef (@Nullable final Consumer <? super RepositoryItemRefPojo.Builder> a)
    {
      if (a != null)
      {
        final RepositoryItemRefPojo.Builder aBuilder = RepositoryItemRefPojo.builder ();
        a.accept (aBuilder);
        repositoryItemRef (aBuilder);
      }
      return this;
    }

    @Nonnull
    public Builder repositoryItemRef (@Nullable final RepositoryItemRefPojo.Builder a)
    {
      return repositoryItemRef (a == null ? null : a.build ());
    }

    @Nonnull
    public Builder repositoryItemRef (@Nullable final RepositoryItemRefPojo a)
    {
      m_aRepositoryItemRef = a;
      return this;
    }

    @Nonnull
    public Builder repositoryItemRef (@Nullable final SimpleLinkType a)
    {
      return repositoryItemRef (a == null ? null : RepositoryItemRefPojo.builder (a));
    }

    public void checkConsistency ()
    {
      if (StringHelper.hasNoText (m_sRegistryObjectID))
        throw new IllegalStateException ("RegistryObjectID must be present");
      if (m_aRepositoryItemRef == null)
        throw new IllegalStateException ("RepositoryItemRef MUST be present");
    }

    @Nonnull
    public ResponseDocumentPojo build ()
    {
      checkConsistency ();

      return new ResponseDocumentPojo (m_sRegistryObjectID, m_aRepositoryItemRef);
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
  public static Builder builder (@Nullable final ExtrinsicObjectType a)
  {
    final Builder ret = new Builder ();
    if (a != null)
    {
      ret.registryObjectID (a.getId ());
      for (final SlotType aSlot : a.getSlot ())
        _applySlots (aSlot, ret);

      ret.repositoryItemRef (a.getRepositoryItemRef ());
    }
    return ret;
  }
}
