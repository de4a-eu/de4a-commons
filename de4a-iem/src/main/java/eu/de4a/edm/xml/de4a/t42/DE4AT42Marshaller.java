package eu.de4a.edm.xml.de4a.t42;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;

import com.helger.commons.functional.IFunction;
import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.edm.jaxb.t42.ContactPointType;
import eu.de4a.edm.jaxb.t42.LegalEntityType;

/**
 * Special marshaller for DE4A T4.2 pilot. This class can ONLY ready T4.2 stuff
 * without the surrounding core document.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        JAXB type to be read
 */
public class DE4AT42Marshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  public DE4AT42Marshaller (@Nonnull final Class <JAXBTYPE> aType,
                            @Nonnull final IFunction <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper)
  {
    super (aType, CT42.getAllXSDs (), aJAXBElementWrapper);
    setNamespaceContext (DE4AT42NamespaceContext.getInstance ());
  }

  @Nonnull
  public static DE4AT42Marshaller <LegalEntityType> legalEntity ()
  {
    return new DE4AT42Marshaller <> (LegalEntityType.class, new eu.de4a.edm.jaxb.t42.ObjectFactory ()::createLegalEntity);
  }

  @Nonnull
  public static DE4AT42Marshaller <ContactPointType> contactPoint ()
  {
    return new DE4AT42Marshaller <> (ContactPointType.class, new eu.de4a.edm.jaxb.t42.ObjectFactory ()::createContactPoint);
  }
}
