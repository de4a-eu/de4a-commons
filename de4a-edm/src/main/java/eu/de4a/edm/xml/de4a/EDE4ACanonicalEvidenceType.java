package eu.de4a.edm.xml.de4a;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;

public enum EDE4ACanonicalEvidenceType implements IDE4ACanonicalEvidenceType
{
  T42_COMPANY_INFO;

  public String getName ()
  {
    return "TODO";
  }

  public ICommonsList <ClassPathResource> getAllXSDs ()
  {
    // TODO
    return new CommonsArrayList <> ();
  }
}
