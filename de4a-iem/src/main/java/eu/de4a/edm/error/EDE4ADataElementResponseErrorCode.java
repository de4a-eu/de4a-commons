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
package eu.de4a.edm.error;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.lang.EnumHelper;

/**
 * Source: DataElementResponseErrorCode-CodeList.gc<br>
 * Content created by MainCreateJavaCode_DataElementResponseErrorCode_GC
 *
 * @author Philip Helger
 */
public enum EDE4ADataElementResponseErrorCode implements IDE4AErrorCode
{
  /** Unauthorized */
  DP_ELE_002 ("DP_ELE_002", "Unauthorized"),
  /** Unavailable */
  DP_ELE_004 ("DP_ELE_004", "Unavailable"),
  /** Internal processing error */
  DP_ELE_005 ("DP_ELE_005", "Internal processing error"),
  /** Insufficient input */
  DP_ELE_006 ("DP_ELE_006", "Insufficient input");

  private final String m_sID;
  private final String m_sDisplayName;

  EDE4ADataElementResponseErrorCode (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sDisplayName)
  {
    m_sID = sID;
    m_sDisplayName = sDisplayName;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  @Nullable
  public static EDE4ADataElementResponseErrorCode getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EDE4ADataElementResponseErrorCode.class, sID);
  }
}
