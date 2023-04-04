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
