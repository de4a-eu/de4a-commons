package eu.de4a.edm.xml.de4a;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import eu.de4a.edm.jaxb.de_usi.RequestForwardEvidence;

/**
 * Test class for class {@link DE_USI_RequestMarshaller}.
 *
 * @author Philip Helger
 */
public class DE_USI_RequestMarshallerTest
{
  @Test
  public void testBasic ()
  {
    final DE_USI_RequestMarshaller m = new DE_USI_RequestMarshaller ();
    final RequestForwardEvidence r = m.read (new File ("src/test/resources/de4a/DE1-USI-request.xml"));
    assertNotNull (r);
    System.out.println (r.toString ());
  }
}
