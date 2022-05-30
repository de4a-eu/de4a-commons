package eu.de4a.iem.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;

/**
 * Test class for class {@link CDE4ACoreJAXB}.
 *
 * @author Philip Helger
 */
public final class CDE4ACoreJAXBTest
{
  @Test
  public void testBasic ()
  {
    for (final ClassPathResource aCP : CDE4ACoreJAXB.XSDS)
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : new ClassPathResource [] { CDE4ACoreJAXB.XSD_DE,
                                                                  CDE4ACoreJAXB.XSD_DO,
                                                                  CDE4ACoreJAXB.XSD_DR,
                                                                  CDE4ACoreJAXB.XSD_DT,
                                                                  CDE4ACoreJAXB.XSD_DEFAULT_RESPONSE })
      assertTrue (aCP.getPath (), aCP.exists ());
  }
}
