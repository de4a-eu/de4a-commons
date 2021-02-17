/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.de4a.edm.xml.cagv;

import javax.annotation.Nullable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.jaxb.GenericJAXBMarshaller;
import com.helger.jaxb.JAXBContextCache;

import eu.de4a.edm.jaxb.cv.agent.AgentType;
import eu.de4a.edm.jaxb.cv.agent.ObjectFactory;

/**
 * Core Agent XML Marshaller
 *
 * @author Philip Helger
 */
public class AgentMarshaller extends GenericJAXBMarshaller <AgentType>
{
  public AgentMarshaller ()
  {
    super (AgentType.class, CCAGV.XSDS, x -> new ObjectFactory ().createAgent (x));
    setNamespaceContext (CAGVNamespaceContext.getInstance ());
  }

  @Override
  protected JAXBContext getJAXBContext (@Nullable final ClassLoader aClassLoader) throws JAXBException
  {
    final Class <?> [] aClasses = new Class <?> [] { com.helger.xsds.ccts.cct.schemamodule.ObjectFactory.class,
                                                     com.helger.xsds.xlink.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.owl.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.w3.skos.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.w3.locn.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.foaf.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.w3.org.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.rdf.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.dcterms.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.w3.regorg.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.cv.dt.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.cv.cbc.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.cv.cac.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.cv.agent.ObjectFactory.class };
    if (isUseContextCache ())
      return JAXBContextCache.getInstance ().getFromCache (new CommonsArrayList <> (aClasses));
    return JAXBContext.newInstance (aClasses);
  }
}
