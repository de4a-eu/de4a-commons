package eu.de4a.edm.xml.de4a;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for class {@link DE4AMarshaller}.
 *
 * @author Philip Helger
 */
public final class DE4AMarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AMarshallerTest.class);
  private static final String BASE_PATH = "src/test/resources/de4a/";

  private static <T> void _testReadWrite (@Nonnull final DE4AMarshaller <T> aMarshaller, @Nonnull final File aFile)
  {
    assertTrue ("Test file does not exists " + aFile.getAbsolutePath (), aFile.exists ());

    final T aRead = aMarshaller.read (aFile);
    assertNotNull ("Failed to read " + aFile.getAbsolutePath (), aRead);

    final byte [] aBytes = aMarshaller.getAsBytes (aRead);
    assertNotNull ("Failed to re-write " + aFile.getAbsolutePath (), aBytes);

    if (true)
    {
      aMarshaller.setFormattedOutput (true);
      LOGGER.info (aMarshaller.getAsString (aRead));
    }
  }

  @Test
  public void testDE_USI ()
  {
    _testReadWrite (DE4AMarshaller.deUsiRequestMarshaller (), new File (BASE_PATH + "core/DE1-USI-request.xml"));
    _testReadWrite (DE4AMarshaller.deUsiResponseMarshaller (), new File (BASE_PATH + "core/DE1-USI-response.xml"));
  }

  @Test
  public void testDO_IM ()
  {
    _testReadWrite (DE4AMarshaller.doImRequestMarshaller (), new File (BASE_PATH + "core/DO1-IM-request.xml"));
    _testReadWrite (DE4AMarshaller.doImResponseMarshaller (), new File (BASE_PATH + "core/DO1-IM-response.xml"));
  }

  @Test
  public void testDO_USI ()
  {
    _testReadWrite (DE4AMarshaller.doUsiRequestMarshaller (), new File (BASE_PATH + "core/DO1-USI-request.xml"));
    _testReadWrite (DE4AMarshaller.doUsiResponseMarshaller (), new File (BASE_PATH + "core/DO1-USI-response.xml"));
  }

  @Test
  public void testDR_IM ()
  {
    _testReadWrite (DE4AMarshaller.drImRequestMarshaller (), new File (BASE_PATH + "core/DR1-IM-request.xml"));
    _testReadWrite (DE4AMarshaller.drImResponseMarshaller (), new File (BASE_PATH + "core/DR1-IM-response.xml"));
  }

  @Test
  public void testDR_USI ()
  {
    _testReadWrite (DE4AMarshaller.drUsiRequestMarshaller (), new File (BASE_PATH + "core/DR1-USI-request.xml"));
    _testReadWrite (DE4AMarshaller.drUsiResponseMarshaller (), new File (BASE_PATH + "core/DR1-USI-response.xml"));
  }

  @Test
  public void testDT_USI ()
  {
    _testReadWrite (DE4AMarshaller.dtUsiRequestMarshaller (), new File (BASE_PATH + "core/DT1-USI-request.xml"));
    _testReadWrite (DE4AMarshaller.dtUsiResponseMarshaller (), new File (BASE_PATH + "core/DT1-USI-response.xml"));
  }

  @Test
  public void testIDK_LookupEvidenceServiceData ()
  {
    _testReadWrite (DE4AMarshaller.idkRequestLookupEvidenceServiceDataMarshaller (),
                    new File (BASE_PATH + "core/DR-DT1-IDK-request-evidence.xml"));
    _testReadWrite (DE4AMarshaller.idkResponseLookupEvidenceServiceDataMarshaller (),
                    new File (BASE_PATH + "core/DR-DT1-IDK-response-evidence.xml"));
  }

  @Test
  public void testIDK_LookupRoutingInformation ()
  {
    _testReadWrite (DE4AMarshaller.idkRequestLookupRoutingInformationMarshaller (),
                    new File (BASE_PATH + "core/DR-DT1-IDK-request-routing.xml"));
    _testReadWrite (DE4AMarshaller.idkResponseLookupRoutingInformationMarshaller (),
                    new File (BASE_PATH + "core/DR-DT1-IDK-response-routing.xml"));
  }
}
