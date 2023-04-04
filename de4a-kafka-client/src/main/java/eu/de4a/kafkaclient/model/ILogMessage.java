package eu.de4a.kafkaclient.model;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public interface ILogMessage
{
  @Nonnull
  @Nonempty
  default String getLogCode ()
  {
    return getModule ().getID () + getLevel ().getCode () + getCode ();
  }

  @Nonnull
  @Nonempty
  String getKey ();

  @Nonnull
  EExternalModule getModule ();

  @Nonnull
  ELogMessageLevel getLevel ();

  @Nonnull
  @Nonempty
  String getCode ();
}
