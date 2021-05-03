package eu.de4a.iem.xml;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.helger.commons.exception.InitializationException;

@Immutable
public class XSDDataTypeHelper
{
  private static final DatatypeFactory DTF;

  static
  {
    try
    {
      DTF = DatatypeFactory.newInstance ();
    }
    catch (final DatatypeConfigurationException ex)
    {
      throw new InitializationException ("Failed to init XSD DataTypeFactory", ex);
    }
  }

  private XSDDataTypeHelper ()
  {}

  @Nonnull
  public static DatatypeFactory getFactory ()
  {
    return DTF;
  }
}
