package eu.de4a.iem.cev.de4a.t43;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;

/**
 * Test class for class {@link CT43}.
 *
 * @author Philip Helger
 */
public final class CT43Test
{
  @Test
  public void testBasic ()
  {
    for (final ClassPathResource aCP : CT43.getAllBirthEvidenceXSDs ())
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : CT43.getAllDomicileRegistrationEvidenceXSDs ())
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : CT43.getAllMarriageEvidenceXSDs ())
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : CT43.getAllPensionMOLEvidenceXSDs ())
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : CT43.getAllUnemploymentMOLEvidenceXSDs ())
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : CT43.getAllWorkingLifeMOLEvidenceXSDs ())
      assertTrue (aCP.getPath (), aCP.exists ());
  }
}
