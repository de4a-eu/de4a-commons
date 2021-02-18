package eu.de4a.edm.xml.de4a;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

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
    final eu.de4a.edm.jaxb.de_usi.RequestForwardEvidenceType req = DE4AMarshaller.deUsiRequestMarshaller ()
                                                                                 .read (new File ("src/test/resources/de4a/DE1-USI-request.xml"));
    assertNotNull (req);

    final eu.de4a.edm.jaxb.de_usi.ResponseForwardEvidenceType resp = DE4AMarshaller.deUsiResponseMarshaller ()
                                                                                   .read (new File ("src/test/resources/de4a/DE1-USI-response.xml"));
    assertNotNull (resp);
  }

  @Test
  public void testDO_IM ()
  {
    final eu.de4a.edm.jaxb.do_im.RequestExtractEvidenceType req = DE4AMarshaller.doImRequestMarshaller ()
                                                                                .read (new File ("src/test/resources/de4a/DO1-IM-request.xml"));
    assertNotNull (req);

    final eu.de4a.edm.jaxb.do_im.ResponseExtractEvidenceType resp = DE4AMarshaller.doImResponseMarshaller ()
                                                                                  .read (new File ("src/test/resources/de4a/DO1-IM-response.xml"));
    assertNotNull (resp);
  }

  @Test
  public void testDO_USI ()
  {
    final eu.de4a.edm.jaxb.do_usi.RequestExtractEvidenceType req = DE4AMarshaller.doUsiRequestMarshaller ()
                                                                                 .read (new File ("src/test/resources/de4a/DO1-USI-request.xml"));
    assertNotNull (req);

    final eu.de4a.edm.jaxb.do_usi.ResponseExtractEvidenceType resp = DE4AMarshaller.doUsiResponseMarshaller ()
                                                                                   .read (new File ("src/test/resources/de4a/DO1-USI-response.xml"));
    assertNotNull (resp);
  }

  @Test
  public void testDR_IM ()
  {
    final eu.de4a.edm.jaxb.dr_im.RequestTransferEvidenceType req = DE4AMarshaller.drImRequestMarshaller ()
                                                                                 .read (new File ("src/test/resources/de4a/DR1-IM-request.xml"));
    assertNotNull (req);

    final eu.de4a.edm.jaxb.dr_im.ResponseTransferEvidenceType resp = DE4AMarshaller.drImResponseMarshaller ()
                                                                                   .read (new File ("src/test/resources/de4a/DR1-IM-response.xml"));
    assertNotNull (resp);
  }

}
