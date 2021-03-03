package eu.de4a.iem.xml.de4a;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;

/**
 * Test class for class {@link CDE4AJAXB}.
 *
 * @author Philip Helger
 */
public final class CDE4AJAXBTest
{
  @Test
  public void testBasic ()
  {
    for (final ClassPathResource aCP : CDE4AJAXB.XSDS)
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : new ClassPathResource [] { CDE4AJAXB.XSD_DE_USI,
                                                                  CDE4AJAXB.XSD_DT_DO_IM,
                                                                  CDE4AJAXB.XSD_DO_USI,
                                                                  CDE4AJAXB.XSD_DR_DT_IDK,
                                                                  CDE4AJAXB.XSD_DR_DE_IM,
                                                                  CDE4AJAXB.XSD_DR_USI,
                                                                  CDE4AJAXB.XSD_DT_USI })
      assertTrue (aCP.getPath (), aCP.exists ());
  }
}
