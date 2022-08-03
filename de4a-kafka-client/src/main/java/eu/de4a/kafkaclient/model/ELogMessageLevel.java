package eu.de4a.kafkaclient.model;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public enum ELogMessageLevel
{
  INFO ("INFO", "I"),
  DEBUG ("DEBUG", "D"),
  ERROR ("ERROR", "E"),
  WARNING ("WARNING", "W");

  private final String name;
  private final String code;

  ELogMessageLevel (@Nonnull @Nonempty final String name, @Nonnull @Nonempty final String code)
  {
    this.name = name;
    this.code = code;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return this.name;
  }

  @Nonnull
  @Nonempty
  public String getCode ()
  {
    return this.code;
  }
}
