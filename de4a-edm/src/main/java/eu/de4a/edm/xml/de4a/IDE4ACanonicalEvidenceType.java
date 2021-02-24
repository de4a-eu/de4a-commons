package eu.de4a.edm.xml.de4a;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;

public interface IDE4ACanonicalEvidenceType
{
  String getName ();

  ICommonsList <ClassPathResource> getAllXSDs ();

  IDE4ACanonicalEvidenceType ALL_PREDEFINED = new IDE4ACanonicalEvidenceType ()
  {
    public String getName ()
    {
      return "all";
    }

    public ICommonsList <ClassPathResource> getAllXSDs ()
    {
      // TODO
      return new CommonsArrayList <> ();
    }

  };
}
