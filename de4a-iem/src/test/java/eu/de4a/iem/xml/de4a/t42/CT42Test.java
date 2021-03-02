package eu.de4a.iem.xml.de4a.t42;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;

import eu.de4a.iem.xml.de4a.t42.CT42;

/**
 * Test class for class {@link CT42}.
 *
 * @author Philip Helger
 */
public final class CT42Test
{
  @Test
  public void testBasic ()
  {
    for (final ClassPathResource aCP : CT42.getAllXSDs ())
      assertTrue (aCP.getPath (), aCP.exists ());
  }
}
