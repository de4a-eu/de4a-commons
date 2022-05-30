package eu.de4a.iem.core;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.string.StringHelper;

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
   * A specific instance that encapsulates the provided
   * {@link IDE4ACanonicalEvidenceType} objects.
   *
   * @param aTypes
   *        The canonical evidence types to be combined
   * @return Never <code>null</code>.
   */
  @Nonnull
  static IDE4ACanonicalEvidenceType multiple (@Nonnull final IDE4ACanonicalEvidenceType... aTypes)
  {
    return new IDE4ACanonicalEvidenceType ()
    {
      @Nonnull
      @Nonempty
      public String getDisplayName ()
      {
        return "collectionOf(" +
               StringHelper.imploder ()
                           .separator (',')
                           .source (aTypes, IDE4ACanonicalEvidenceType::getDisplayName)
                           .build () +
               ")";
      }

      @Nonnull
      @ReturnsMutableCopy
      public ICommonsList <? extends ClassPathResource> getAllXSDs ()
      {
        // Create a set to avoid duplicates in it
        final ICommonsOrderedSet <ClassPathResource> ret = new CommonsLinkedHashSet <> ();
        for (final IDE4ACanonicalEvidenceType e : aTypes)
          ret.addAll (e.getAllXSDs ());
        return ret.getCopyAsList ();
      }
    };
  }

  /**
   * A specific instance that is empty.
   */
  @Nonnull
  IDE4ACanonicalEvidenceType NONE = new IDE4ACanonicalEvidenceType ()
  {
    @Nonnull
    @Nonempty
    public String getDisplayName ()
    {
      return "No DE4A Canoncical Evidence - for testing only";
    }

    @Nonnull
    @ReturnsMutableCopy
    public ICommonsList <? extends ClassPathResource> getAllXSDs ()
    {
      return new CommonsArrayList <> ();
    }
  };
}
