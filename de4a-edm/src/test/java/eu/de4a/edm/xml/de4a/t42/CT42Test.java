package eu.de4a.edm.xml.de4a.t42;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;

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
    for (final ClassPathResource aCP : CT42.XSDS)
      assertTrue (aCP.getPath (), aCP.exists ());
  }
}
