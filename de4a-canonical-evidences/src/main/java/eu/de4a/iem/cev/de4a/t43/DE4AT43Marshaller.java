package eu.de4a.iem.cev.de4a.t43;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.NamespaceContext;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.jaxb.t43.birth.v1_7.BirthEvidenceType;
import eu.de4a.iem.jaxb.t43.domreg.v1_7.DomicileRegistrationEvidenceType;
import eu.de4a.iem.jaxb.t43.marriage.v1_7.MarriageEvidenceType;
import eu.de4a.iem.jaxb.t43.pension.v0_1.PensionMeansOfLivingEvidenceType;
import eu.de4a.iem.jaxb.t43.unemployment.v0_1.UnemploymentMeansOfLivingEvidenceType;
import eu.de4a.iem.jaxb.t43.workinglife.v0_1.WorkingLifeMeansOfLivingEvidenceType;

/**
 * Special marshaller for canonical evidences of the DE4A T4.3 v1.6a pilot. This
 * class can ONLY reads T4.3 stuff without the surrounding core document.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        JAXB type to be read
 */
public class DE4AT43Marshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  protected DE4AT43Marshaller (@Nonnull final Class <JAXBTYPE> aType,
                               @Nullable final List <? extends ClassPathResource> aXSDs,
                               @Nonnull final Function <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper,
                               @Nullable final NamespaceContext aNSContext)
  {
    super (aType, aXSDs, aJAXBElementWrapper);
    setNamespaceContext (aNSContext);
  }

  @Nonnull
  public static DE4AT43Marshaller <BirthEvidenceType> birthEvidence ()
  {
    return new DE4AT43Marshaller <> (BirthEvidenceType.class,
                                     CT43.getAllBirthEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.birth.v1_7.ObjectFactory ()::createBirthEvidence,
                                     DE4AT43NamespaceContext.getBirthEvidenceInstance ());
  }

  @Nonnull
  public static DE4AT43Marshaller <DomicileRegistrationEvidenceType> domicileRegistrationEvidence ()
  {
    return new DE4AT43Marshaller <> (DomicileRegistrationEvidenceType.class,
                                     CT43.getAllDomicileRegistrationEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.domreg.v1_7.ObjectFactory ()::createDomicileRegistrationEvidence,
                                     DE4AT43NamespaceContext.getDomicileRegistrationEvidenceInstance ());
  }

  @Nonnull
  public static DE4AT43Marshaller <MarriageEvidenceType> marriageEvidence ()
  {
    return new DE4AT43Marshaller <> (MarriageEvidenceType.class,
                                     CT43.getAllMarriageEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.marriage.v1_7.ObjectFactory ()::createMarriageEvidence,
                                     DE4AT43NamespaceContext.getMarriageEvidenceInstance ());
  }

  @Nonnull
  public static DE4AT43Marshaller <PensionMeansOfLivingEvidenceType> pensionMeansOfLivingEvidence ()
  {
    return new DE4AT43Marshaller <> (PensionMeansOfLivingEvidenceType.class,
                                     CT43.getAllPensionMOLEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.pension.v0_1.ObjectFactory ()::createPensionMeansOfLivingEvidence,
                                     DE4AT43NamespaceContext.getPensionMeansOfLivingEvidenceInstance ());
  }

  @Nonnull
  public static DE4AT43Marshaller <UnemploymentMeansOfLivingEvidenceType> unemploymentMeansOfLivingEvidence ()
  {
    return new DE4AT43Marshaller <> (UnemploymentMeansOfLivingEvidenceType.class,
                                     CT43.getAllUnemploymentMOLEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.unemployment.v0_1.ObjectFactory ()::createUnemploymentMeansOfLivingEvidence,
                                     DE4AT43NamespaceContext.getUnemploymentMeansOfLivingEvidenceInstance ());
  }

  @Nonnull
  public static DE4AT43Marshaller <WorkingLifeMeansOfLivingEvidenceType> workingLifeMeansOfLivingEvidence ()
  {
    return new DE4AT43Marshaller <> (WorkingLifeMeansOfLivingEvidenceType.class,
                                     CT43.getAllUnemploymentMOLEvidenceXSDs (),
                                     new eu.de4a.iem.jaxb.t43.workinglife.v0_1.ObjectFactory ()::createWorkingLifeMeansOfLivingEvidence,
                                     DE4AT43NamespaceContext.getWorkingLifeMeansOfLivingEvidenceInstance ());
  }
}
