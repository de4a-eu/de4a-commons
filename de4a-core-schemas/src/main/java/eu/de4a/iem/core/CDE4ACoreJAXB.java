package eu.de4a.iem.core;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for handling DE4A stuff
 *
 * @author Philip Helger
 */
public final class CDE4ACoreJAXB
{
  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CDE4ACoreJAXB.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS;
  static
  {
    final ICommonsList <ClassPathResource> a = new CommonsArrayList <> ();
    a.add (CXML_XSD.getXSDResource ());
    a.add (CCCTS.getXSDResource ());
    a.add (new ClassPathResource ("schemas/core/external/CV-DataTypes.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/core/external/eidas-LP.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/core/external/eidas-NP.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/core/de4a/common-types.xsd", _getCL ()));
    XSDS = a.getAsUnmodifiable ();
  }

  public static final ClassPathResource XSD_DE = new ClassPathResource ("schemas/core/DE.xsd", _getCL ());
  public static final ClassPathResource XSD_DR = new ClassPathResource ("schemas/core/DR.xsd", _getCL ());
  public static final ClassPathResource XSD_DT = new ClassPathResource ("schemas/core/DT.xsd", _getCL ());
  public static final ClassPathResource XSD_DO = new ClassPathResource ("schemas/core/DO.xsd", _getCL ());

  public static final ClassPathResource XSD_DEFAULT_RESPONSE = new ClassPathResource ("schemas/core/def-response.xsd",
                                                                                      _getCL ());

  private CDE4ACoreJAXB ()
  {}
}
