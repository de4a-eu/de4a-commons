package eu.de4a.edm.xml.de4a;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.io.resource.ClassPathResource;

/**
 * Contains the technical details of a single canonical evidence.
 *
 * @author Philip Helger
 */
public interface IDE4ACanonicalEvidenceType
{
  /**
   * @return The display name of this particular evidence. May neither be
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  String getDisplayName ();

  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <? extends ClassPathResource> getAllXSDs ();

  /**
   * A specific instance that encapsulates all predefined objects from the
   * {@link EDE4ACanonicalEvidenceType} enum.
   */
  @Nonnull
  IDE4ACanonicalEvidenceType ALL_PREDEFINED = new IDE4ACanonicalEvidenceType ()
  {
    @Nonnull
    @Nonempty
    public String getDisplayName ()
    {
      return "All predefined DE4A Canoncical Evidences";
    }

    @Nonnull
    @ReturnsMutableCopy
    public ICommonsList <? extends ClassPathResource> getAllXSDs ()
    {
      // Create a set to avoid duplicates in it
      final ICommonsOrderedSet <ClassPathResource> ret = new CommonsLinkedHashSet <> ();
      for (final EDE4ACanonicalEvidenceType e : EDE4ACanonicalEvidenceType.values ())
        ret.addAll (e.getAllXSDs ());
      return ret.getCopyAsList ();
    }
  };
}
