package eu.de4a.iem.core;

import javax.annotation.concurrent.Immutable;

/**
 * IEM constants
 *
 * @author Philip Helger
 * @since 0.2.1
 */
@Immutable
public final class CIEM
{
  // Iteration 2 identifier for the IEM "/SpecificationId" element
  public static final String SPECIFICATION_ID = "de4a-iem-v2";

  // Specific Document Type Identifier value for multi-item requests
  public static final String MULTI_ITEM_TYPE = "MultiItem:1.0";

  private CIEM ()
  {}
}
