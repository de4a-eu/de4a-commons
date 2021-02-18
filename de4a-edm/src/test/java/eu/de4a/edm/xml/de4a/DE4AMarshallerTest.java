package eu.de4a.edm.xml.de4a;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import eu.de4a.edm.jaxb.de_usi.RequestForwardEvidenceType;
import eu.de4a.edm.jaxb.de_usi.ResponseForwardEvidenceType;

/**
 * Test class for class {@link DE4AMarshaller}.
 *
 * @author Philip Helger
 */
public class DE4AMarshallerTest
{
  @Test
  public void testDE_USI ()
  {
    final RequestForwardEvidenceType req = DE4AMarshaller.deUsiRequestMarshaller ()
                                                         .read (new File ("src/test/resources/de4a/DE1-USI-request.xml"));
    assertNotNull (req);

    final ResponseForwardEvidenceType resp = DE4AMarshaller.deUsiResponseMarshaller ()
                                                           .read (new File ("src/test/resources/de4a/DE1-USI-response.xml"));
    assertNotNull (resp);
  }
}
