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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

/**
 * Response option. Used in the request to determine the desired response
 * layout. Used in the response to depict the actually used layout.
 *
 * @since 2.0.0-beta3
 * @author Philip Helger
 */
public enum EDE4AResponseOptionType implements IHasID <String>
{
  /**
   * Response payload is part of the response.
   */
  INLINE ("LeafClassWithRepositoryItem"),
  /**
   * Response payload is referenced from within the response.
   */
  REFERENCE ("ObjectRef");

  private final String m_sID;

  EDE4AResponseOptionType (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public static EDE4AResponseOptionType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EDE4AResponseOptionType.class, sID);
  }
}
