package eu.de4a.iem.cev.de4a.t43;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.Singleton;
import com.helger.ubl23.UBL23NamespaceContext;
import com.helger.xml.namespace.MapBasedNamespaceContext;

/**
 * XML Namespace context for DE4A T4.3 v1.6
 *
 * @author Philip Helger
 */
@Singleton
public class DE4AT43NamespaceContext extends MapBasedNamespaceContext
{
  private static final class SingletonHolder
  {
    static final DE4AT43NamespaceContext INSTANCE = new DE4AT43NamespaceContext ();
  }

  protected DE4AT43NamespaceContext ()
  {
    addMappings (UBL23NamespaceContext.getInstance ());
    addMapping ("cva", "http://www.w3.org/ns/corevocabulary/AggregateComponents");
    addMapping ("cvb", "http://www.w3.org/ns/corevocabulary/BasicComponents");
    addMapping ("business", "http://www.w3.org/ns/corevocabulary/business");
    addMapping ("location", "http://www.w3.org/ns/corevocabulary/location");
    addMapping ("person", "http://www.w3.org/ns/corevocabulary/person");
  }

  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext getBirthEvidenceInstance ()
  {
    final MapBasedNamespaceContext ret = SingletonHolder.INSTANCE.getClone ();
    ret.addDefaultNamespaceURI (CT43.NS_URI_BIRTH_EVIDENCE);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext getDomicileRegistrationEvidenceInstance ()
  {
    final MapBasedNamespaceContext ret = SingletonHolder.INSTANCE.getClone ();
    ret.addDefaultNamespaceURI (CT43.NS_URI_DOMICILE_REGISTRATION_EVIDENCE);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext getMarriageEvidenceInstance ()
  {
    final MapBasedNamespaceContext ret = SingletonHolder.INSTANCE.getClone ();
    ret.addDefaultNamespaceURI (CT43.NS_URI_MARRIAGE_EVIDENCE);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext getPensionMeansOfLivingEvidenceInstance ()
  {
    final MapBasedNamespaceContext ret = SingletonHolder.INSTANCE.getClone ();
    ret.addDefaultNamespaceURI (CT43.NS_URI_PENSION_MOL_EVIDENCE);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext getUnemploymentMeansOfLivingEvidenceInstance ()
  {
    final MapBasedNamespaceContext ret = SingletonHolder.INSTANCE.getClone ();
    ret.addDefaultNamespaceURI (CT43.NS_URI_UNEMPLOYMENT_MOL_EVIDENCE);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext getWorkingLifeMeansOfLivingEvidenceInstance ()
  {
    final MapBasedNamespaceContext ret = SingletonHolder.INSTANCE.getClone ();
    ret.addDefaultNamespaceURI (CT43.NS_URI_WORKING_LIFE_MOL_EVIDENCE);
    return ret;
  }
}
