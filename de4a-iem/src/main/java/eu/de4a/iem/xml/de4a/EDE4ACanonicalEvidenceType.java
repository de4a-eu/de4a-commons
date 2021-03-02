package eu.de4a.iem.xml.de4a;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;

import eu.de4a.iem.xml.de4a.t42.CT42;

/**
 * Contains all the predefined DE4A Canonical Evidences
 *
 * @author Philip Helger
 */
public enum EDE4ACanonicalEvidenceType implements IDE4ACanonicalEvidenceType
{
  T42_COMPANY_INFO ("T4.2 Company Info", CT42.getAllXSDs ());

  private final String m_sDisplayName;
  private final ICommonsList <ClassPathResource> m_aXSDs;

  EDE4ACanonicalEvidenceType (@Nonnull @Nonempty final String sDisplayName, @Nonnull final ICommonsList <ClassPathResource> aXSDs)
  {
    m_sDisplayName = sDisplayName;
    m_aXSDs = aXSDs;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <? extends ClassPathResource> getAllXSDs ()
  {
    return m_aXSDs;
  }
}
