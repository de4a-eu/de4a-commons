package eu.de4a.iem.cev.de4a.t43;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jaxb.GenericJAXBMarshaller;

/**
 * Test class for class {@link DE4AT43Marshaller}.
 *
 * @author Philip Helger
 */
public final class DE4AT43MarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AT43MarshallerTest.class);
  private static final String BASE_PATH = "src/test/resources/de4a/t4.3/v1.7/";

  private static <T> void _testReadWrite (@Nonnull final GenericJAXBMarshaller <T> aMarshaller,
                                          @Nonnull final File aFile)
  {
    assertTrue ("Test file does not exists " + aFile.getAbsolutePath (), aFile.exists ());

    final T aRead = aMarshaller.read (aFile);
    assertNotNull ("Failed to read " + aFile.getAbsolutePath (), aRead);

    final byte [] aBytes = aMarshaller.getAsBytes (aRead);
    assertNotNull ("Failed to re-write " + aFile.getAbsolutePath (), aBytes);

    if (false)
    {
      aMarshaller.setFormattedOutput (true);
      LOGGER.info (aMarshaller.getAsString (aRead));
    }
  }

  @Test
  public void testBirthEvidence ()
  {
    for (final String s : new String [] { "birth-evidence-1.7-generated-example.xml",
                                          "birth-evidence-1.7-generated-example-without-optional-attributes.xml" })
      _testReadWrite (DE4AT43Marshaller.birthEvidence (), new File (BASE_PATH + s));
  }

  @Test
  public void testDomicileRegistrationEvidence ()
  {
    for (final String s : new String [] {})
      _testReadWrite (DE4AT43Marshaller.domicileRegistrationEvidence (), new File (BASE_PATH + s));
  }

  @Test
  public void testMarriageEvidence ()
  {
    for (final String s : new String [] { "marriage-evidence-1.7-generated-example.xml",
                                          "marriage-evidence-1.7-generated-example-without-optional-attributes.xml" })
      _testReadWrite (DE4AT43Marshaller.marriageEvidence (), new File (BASE_PATH + s));
  }

  @Test
  public void testPensionMeansOfLivingEvidence ()
  {
    for (final String s : new String [] {})
      _testReadWrite (DE4AT43Marshaller.pensionMeansOfLivingEvidence (), new File (BASE_PATH + s));
  }

  @Test
  public void testUnemploymentMeansOfLivingEvidence ()
  {
    for (final String s : new String [] {})
      _testReadWrite (DE4AT43Marshaller.unemploymentMeansOfLivingEvidence (), new File (BASE_PATH + s));
  }

  @Test
  public void testWorkingLifeMeansOfLivingEvidence ()
  {
    for (final String s : new String [] {})
      _testReadWrite (DE4AT43Marshaller.workingLifeMeansOfLivingEvidence (), new File (BASE_PATH + s));
  }
}
