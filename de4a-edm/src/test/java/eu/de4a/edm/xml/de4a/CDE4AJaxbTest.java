package eu.de4a.edm.xml.de4a;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;

/**
 * Test class for class {@link CDE4AJaxb}.
 *
 * @author Philip Helger
 */
public final class CDE4AJaxbTest
{
  @Test
  public void testBasic ()
  {
    for (final ClassPathResource aCP : CDE4AJaxb.XSDS)
      assertTrue (aCP.getPath (), aCP.exists ());
    for (final ClassPathResource aCP : new ClassPathResource [] { CDE4AJaxb.XSD_DE_USI,
                                                                  CDE4AJaxb.XSD_DO_IM,
                                                                  CDE4AJaxb.XSD_DO_USI,
                                                                  CDE4AJaxb.XSD_DR_DT_IDK,
                                                                  CDE4AJaxb.XSD_DR_IM,
                                                                  CDE4AJaxb.XSD_DR_USI,
                                                                  CDE4AJaxb.XSD_DT_USI })
      assertTrue (aCP.getPath (), aCP.exists ());
  }
}
