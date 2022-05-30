package eu.de4a.iem.cev.de4a.t42;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.ubl23.CUBL23;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for handling DE4A T4.2 pilot stuff
 *
 * @author Philip Helger
 */
public final class CT42
{
  public static final String NS_URI_COMPANY_REGISTRATION = "urn:eu-de4a:xsd:CanonicalEvidenceType::CompanyRegistration:v0.6";

  private CT42 ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CT42.class.getClassLoader ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllXSDs ()
  {
    final ICommonsList <ClassPathResource> a = new CommonsArrayList <> ();
    a.add (CXML_XSD.getXSDResource ());
    a.add (CCCTS.getXSDResource ());
    a.add (CUBL23.XSD_UNQUALIFIED_DATA_TYPES);
    a.add (new ClassPathResource ("schemas/t4.2/CoreVocabularies-BasicComponents-1.1.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/t4.2/doing_Business_abroad_XSD_v0.6 draft.xsd", _getCL ()));
    return a;
  }
}
