package eu.de4a.iem.cev.de4a.t43;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.ubl23.CUBL23;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xades141.CXAdES141;
import com.helger.xsds.xml.CXML_XSD;

/**
 * Constants for handling DE4A T4.3 pilot stuff
 *
 * @author Philip Helger
 */
@Immutable
public final class CT43
{
  public static final String NS_URI_BIRTH_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::BirthEvidence:v1.7";
  public static final String NS_URI_DOMICILE_REGISTRATION_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::DomicileRegistrationEvidence:v1.7";
  public static final String NS_URI_MARRIAGE_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::MarriageEvidence:v1.7";
  public static final String NS_URI_PENSION_MOL_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::PensionMeansOfLivingEvidence:v0.1";
  public static final String NS_URI_UNEMPLOYMENT_MOL_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::UnemploymentMeansOfLivingEvidence:v0.1";
  public static final String NS_URI_WORKING_LIFE_MOL_EVIDENCE = "urn:eu-de4a:xsd:CanonicalEvidenceType::WorkingLifeMeansOfLivingEvidence:v0.1";

  private CT43 ()
  {}

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CT43.class.getClassLoader ();
  }

  @Nonnull
  @ReturnsMutableCopy
  private static ICommonsList <ClassPathResource> _getBaseXSDs ()
  {
    final ICommonsList <ClassPathResource> a = new CommonsArrayList <> ();
    a.add (CXML_XSD.getXSDResource ());
    // XMLDsig + Xades
    a.addAll (CXAdES141.getAllXSDResources ());
    a.add (CCCTS.getXSDResource ());
    a.add (CUBL23.XSD_UNQUALIFIED_DATA_TYPES);
    a.add (CUBL23.XSD_QUALIFIED_DATA_TYPES);
    a.add (CUBL23.XSD_COMMON_BASIC_COMPONENTS);
    if (false)
      a.add (CUBL23.XSD_EXTENSION_CONTENT_DATA_TYPE);
    a.add (CUBL23.XSD_COMMON_EXTENSION_COMPONENTS);
    a.add (new ClassPathResource ("schemas/t4.3/CoreVocabularyBasicComponents-v1.00.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/t4.3/CoreBusiness-v1.00.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/t4.3/CoreLocation-v1.00.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/t4.3/CorePerson-v1.00.xsd", _getCL ()));
    a.add (new ClassPathResource ("schemas/t4.3/CoreVocabularyAggregateComponents-v1.00.xsd", _getCL ()));
    return a;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllBirthEvidenceXSDs ()
  {
    final ICommonsList <ClassPathResource> a = _getBaseXSDs ();
    a.add (new ClassPathResource ("schemas/t4.3/v1.7/birthEvidence-1.7.xsd", _getCL ()));
    return a;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllDomicileRegistrationEvidenceXSDs ()
  {
    final ICommonsList <ClassPathResource> a = _getBaseXSDs ();
    a.add (new ClassPathResource ("schemas/t4.3/v1.7/domicileRegistrationEvidence-1.7.xsd", _getCL ()));
    return a;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllMarriageEvidenceXSDs ()
  {
    final ICommonsList <ClassPathResource> a = _getBaseXSDs ();
    a.add (new ClassPathResource ("schemas/t4.3/v1.7/marriageEvidence-1.7.xsd", _getCL ()));
    return a;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllPensionMOLEvidenceXSDs ()
  {
    final ICommonsList <ClassPathResource> a = _getBaseXSDs ();
    a.add (new ClassPathResource ("schemas/t4.3/v1.7/pensionMeansOfLivingEvidence-0.1.xsd", _getCL ()));
    return a;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllUnemploymentMOLEvidenceXSDs ()
  {
    final ICommonsList <ClassPathResource> a = _getBaseXSDs ();
    a.add (new ClassPathResource ("schemas/t4.3/v1.7/unemploymentMeansOfLivingEvidence-0.1.xsd", _getCL ()));
    return a;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <ClassPathResource> getAllWorkingLifeMOLEvidenceXSDs ()
  {
    final ICommonsList <ClassPathResource> a = _getBaseXSDs ();
    a.add (new ClassPathResource ("schemas/t4.3/v1.7/workingLifeMeansOfLivingEvidence-0.1.xsd", _getCL ()));
    return a;
  }
}
