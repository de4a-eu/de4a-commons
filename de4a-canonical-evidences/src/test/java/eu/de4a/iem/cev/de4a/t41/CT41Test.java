package eu.de4a.iem.cev.de4a.t41;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;

/**
 * Test class for class {@link CT41}.
 *
 * @author Philip Helger
 */
public final class CT41Test
{
  @Test
  public void testBasic ()
  {
    for (final ClassPathResource aCP : CT41.getAllXSDsHigherEducationDiploma ())
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : CT41.getAllXSDsSecondaryEducationDiploma ())
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : CT41.getAllXSDsDisability ())
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : CT41.getAllXSDsLargeFamily ())
      assertTrue (aCP.getPath (), aCP.exists ());
  }
}
