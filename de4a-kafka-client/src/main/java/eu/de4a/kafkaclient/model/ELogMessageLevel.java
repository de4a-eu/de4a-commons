package eu.de4a.kafkaclient.model;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public enum ELogMessageLevel
{
  INFO ("INFO", "I"),
  DEBUG ("DEBUG", "D"),
  ERROR ("ERROR", "E"),
  WARNING ("WARNING", "W");

  private final String m_sName;
  private final String m_sCode;

  ELogMessageLevel (@Nonnull @Nonempty final String sName, @Nonnull @Nonempty final String sCode)
  {
    m_sName = sName;
    m_sCode = sCode;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  @Nonempty
  public String getCode ()
  {
    return m_sCode;
  }
}
