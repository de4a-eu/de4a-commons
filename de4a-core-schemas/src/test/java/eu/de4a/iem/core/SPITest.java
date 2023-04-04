package eu.de4a.iem.core;

import org.junit.Test;

import com.helger.commons.mock.SPITestHelper;

/**
 * Test SPI definitions
 *
 * @author Philip Helger
 */
public final class SPITest
{
  @Test
  public void testBasic () throws Exception
  {
    SPITestHelper.testIfAllSPIImplementationsAreValid ();
  }
}
