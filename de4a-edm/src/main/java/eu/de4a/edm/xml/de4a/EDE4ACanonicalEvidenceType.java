package eu.de4a.edm.xml.de4a;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;

/**
 * Contains all the predefined DE4A Canonical Evidences
 *
 * @author Philip Helger
 */
public enum EDE4ACanonicalEvidenceType implements IDE4ACanonicalEvidenceType
{
  T42_COMPANY_INFO ("T4.2 Company Info");

  private final String m_sDisplayName;

  EDE4ACanonicalEvidenceType (@Nonnull @Nonempty final String sDisplayName)
  {
    m_sDisplayName = sDisplayName;
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
    // TODO
    return new CommonsArrayList <> ();
  }
}
