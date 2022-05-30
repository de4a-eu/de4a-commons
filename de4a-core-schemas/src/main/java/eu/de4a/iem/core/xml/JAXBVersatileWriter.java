package eu.de4a.iem.core.xml;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.jaxb.IJAXBWriter;

/**
 * The default implementation of {@link IJAXBVersatileWriter} using a constant
 * value and an instance of {@link IJAXBWriter}.
 *
 * @author Philip Helger
 * @param <T>
 *        Type to be written.
 */
public final class JAXBVersatileWriter <T> implements IJAXBVersatileWriter <T>
{
  private final T m_aObject;
  private final IJAXBWriter <T> m_aWriter;

  public JAXBVersatileWriter (@Nonnull final T aObject, @Nonnull final IJAXBWriter <T> aWriter)
  {
    ValueEnforcer.notNull (aObject, "Object");
    ValueEnforcer.notNull (aWriter, "Writer");
    m_aObject = aObject;
    m_aWriter = aWriter;
  }

  @Nonnull
  public T getObjectToWrite ()
  {
    return m_aObject;
  }

  @Nonnull
  public IJAXBWriter <T> getWriter ()
  {
    return m_aWriter;
  }
}
