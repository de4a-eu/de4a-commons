package eu.de4a.iem.cev.de4a.t42;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Singleton;
import com.helger.ubl23.UBL23NamespaceContext;
import com.helger.xml.namespace.MapBasedNamespaceContext;

/**
 * XML Namespace context for DE4A T4.2 v0.6
 *
 * @author Philip Helger
 */
@Singleton
public class DE4AT42NamespaceContext extends MapBasedNamespaceContext
{
  private static final class SingletonHolder
  {
    static final DE4AT42NamespaceContext INSTANCE = new DE4AT42NamespaceContext ();
  }

  protected DE4AT42NamespaceContext ()
  {
    addMappings (UBL23NamespaceContext.getInstance ());
    addMapping ("cvb", "http://www.w3.org/ns/corevocabulary/BasicComponents");
    addMapping ("dba", CT42.NS_URI_COMPANY_REGISTRATION);
  }

  @Nonnull
  public static DE4AT42NamespaceContext getInstance ()
  {
    return SingletonHolder.INSTANCE;
  }
}
