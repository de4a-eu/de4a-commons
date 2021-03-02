package eu.de4a.iem.xml.de4a.t42;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.xml.de4a.t42.DE4AT42Marshaller;

/**
 * Test class for class {@link DE4AT42Marshaller}.
 *
 * @author Philip Helger
 */
public final class DE4AT42MarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AT42MarshallerTest.class);
  private static final String BASE_PATH = "src/test/resources/de4a/";

  private static <T> void _testReadWrite (@Nonnull final GenericJAXBMarshaller <T> aMarshaller, @Nonnull final File aFile)
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
  public void testLegalEntity ()
  {
    _testReadWrite (DE4AT42Marshaller.legalEntity (), new File (BASE_PATH + "t4.2/sample CompanyInfo NL KVK.xml"));
    _testReadWrite (DE4AT42Marshaller.legalEntity (), new File (BASE_PATH + "t4.2/sample CompanyInfo RO ONRC-2.xml"));
    _testReadWrite (DE4AT42Marshaller.legalEntity (), new File (BASE_PATH + "t4.2/sample company info SE -2.xml"));
  }
}
