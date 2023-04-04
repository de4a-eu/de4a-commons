/*
 * Copyright (C) 2023, Partners of the EU funded DE4A project consortium
 *   (https://www.de4a.eu/consortium), under Grant Agreement No.870635
 * Author: Austrian Federal Computing Center (BRZ)
 *
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
package eu.de4a.kafkaclient.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;

public enum EExternalModule implements IHasID <String>
{
  IDK ("01", "IDK"),
  SMP ("02", "SMP"),
  CONNECTOR_DR ("DR", "CONNECTOR DR"),
  CONNECTOR_DT ("DT", "CONNECTOR DT"),
  DATA_OWNER ("DO", "DATA OWNER"),
  DATA_EVALUATOR ("DE", "DATA EVALUATOR"),
  AS4 ("AS", "AS4 GATEWAY"),
  MOR ("08", "MOR"),
  IAL ("09", "IAL"),
  NONE ("00", "NONE");

  private final String m_sID;
  private final String m_sLabel;

  EExternalModule (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sLabel)
  {
    m_sID = sID;
    m_sLabel = sLabel;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getLabel ()
  {
    return m_sLabel;
  }

  @Nullable
  public static EExternalModule getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EExternalModule.class, sID);
  }
}
