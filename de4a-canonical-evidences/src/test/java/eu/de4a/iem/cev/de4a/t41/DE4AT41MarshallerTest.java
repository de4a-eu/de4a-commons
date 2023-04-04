package eu.de4a.iem.cev.de4a.t41;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.jaxb.GenericJAXBMarshaller;

/**
 * Test class for class {@link DE4AT41Marshaller}.
 *
 * @author Philip Helger
 */
public final class DE4AT41MarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AT41MarshallerTest.class);

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
      if (LOGGER.isInfoEnabled ())
        LOGGER.info ("Created version of '" + aFile.getName () + "'\n" + aMarshaller.getAsString (aRead));
    }
  }

  @Test
  public void testHigherEducationDiploma ()
  {
    final String sBasePath = "src/test/resources/de4a/t4.1/hed/";
    _testReadWrite (DE4AT41Marshaller.higherEducationDiploma (),
                    new File (sBasePath + "HigherEducationEvidenceTypev3.2.xml"));
    _testReadWrite (DE4AT41Marshaller.higherEducationDiploma (),
                    new File (sBasePath + "SA-UC1-11-02-2021-example-PT.xml"));
    _testReadWrite (DE4AT41Marshaller.higherEducationDiploma (), new File (sBasePath + "SA-UC1-20-04-2021-SI.xml"));
  }

  @Test
  public void testSecondaryEducationDiploma ()
  {
    final String sBasePath = "src/test/resources/de4a/t4.1/sed/";
    _testReadWrite (DE4AT41Marshaller.secondaryEducationDiploma (),
                    new File (sBasePath + "SA-UC1-SecondaryEducationEvidenceType-sample.xml"));
  }

  @Test
  public void testDisability ()
  {
    final String sBasePath = "src/test/resources/de4a/t4.1/de/";
    _testReadWrite (DE4AT41Marshaller.disability (), new File (sBasePath + "SA-UC2-DisabilityEvidenceSample.xml"));
  }

  @Test
  public void testLargeFamily ()
  {
    final String sBasePath = "src/test/resources/de4a/t4.1/lf/";
    _testReadWrite (DE4AT41Marshaller.largeFamily (), new File (sBasePath + "SA-UC2-LargeFamilyEvidenceSample.xml"));
  }
}
