package eu.de4a.iem.core;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Singleton;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;

/**
 * XML Namespace context for DE4A
 *
 * @author Philip Helger
 */
@Singleton
public class DE4ACoreNamespaceContext extends MapBasedNamespaceContext
{
  private static final class SingletonHolder
  {
    static final DE4ACoreNamespaceContext INSTANCE = new DE4ACoreNamespaceContext ();
  }

  protected DE4ACoreNamespaceContext ()
  {
    addMapping ("eilp", "http://eidas.europa.eu/attributes/legalperson");
    addMapping ("einp", "http://eidas.europa.eu/attributes/naturalperson");
    addMapping ("de4a", "http://www.de4a.eu/2020/commons/type/v2");
    addMapping ("cv", "https://data.europe.eu/semanticassets/ns/cv/common/dataTypes-2.0.0#");
    addMapping ("ct", CCCTS.NAMESPACE_URI);
  }

  @Nonnull
  public static DE4ACoreNamespaceContext getInstance ()
  {
    return SingletonHolder.INSTANCE;
  }
}
