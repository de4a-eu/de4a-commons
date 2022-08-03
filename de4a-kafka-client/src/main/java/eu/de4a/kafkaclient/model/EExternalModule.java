package eu.de4a.kafkaclient.model;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public enum EExternalModule
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

  private final String id;
  private final String label;

  EExternalModule (@Nonnull @Nonempty final String id, @Nonnull @Nonempty final String label)
  {
    this.id = id;
    this.label = label;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return id;
  }

  @Nonnull
  @Nonempty
  public String getLabel ()
  {
    return label;
  }
}
