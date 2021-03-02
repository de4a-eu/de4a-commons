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
package eu.de4a.iem.slot;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.slot.ISlotProvider;
import com.helger.regrep.slot.SlotBuilder;

/**
 * "IssueDateTime" slot
 *
 * @author Philip Helger
 */
public class SlotIssueDateTime implements ISlotProvider
{
  public static final String NAME = "IssueDateTime";

  private final LocalDateTime m_aLDT;

  public SlotIssueDateTime (@Nonnull final LocalDateTime aLDT)
  {
    ValueEnforcer.notNull (aLDT, "LDT");
    m_aLDT = aLDT;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return NAME;
  }

  @Nonnull
  public SlotType createSlot ()
  {
    return new SlotBuilder ().setName (NAME).setValue (m_aLDT).build ();
  }
}
