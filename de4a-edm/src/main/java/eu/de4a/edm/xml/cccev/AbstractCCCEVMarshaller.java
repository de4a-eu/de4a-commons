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
package eu.de4a.edm.xml.cccev;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.functional.IFunction;
import com.helger.jaxb.GenericJAXBMarshaller;
import com.helger.jaxb.JAXBContextCache;

/**
 * Abstract CCCEV XML marshaller
 *
 * @author Philip Helger
 * @param <T>
 *        Type to be marshalled
 */
public abstract class AbstractCCCEVMarshaller <T> extends GenericJAXBMarshaller <T>
{
  public AbstractCCCEVMarshaller (@Nonnull final Class <T> aType,
                                  @Nonnull final IFunction <? super T, ? extends JAXBElement <T>> aJAXBElementWrapper)
  {
    super (aType, CCCEV.XSDS, aJAXBElementWrapper);
    setNamespaceContext (CCCEVNamespaceContext.getInstance ());
  }

  @Override
  protected JAXBContext getJAXBContext (@Nullable final ClassLoader aClassLoader) throws JAXBException
  {
    final Class <?> [] aClasses = new Class <?> [] { eu.de4a.edm.jaxb.cccev.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.cv.agent.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.cv.cac.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.cv.cbc.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.dcatap.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.foaf.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.owl.ObjectFactory.class,
                                                     eu.de4a.edm.jaxb.w3.adms.ObjectFactory.class };
    if (isUseContextCache ())
      return JAXBContextCache.getInstance ().getFromCache (new CommonsArrayList <> (aClasses));
    return JAXBContext.newInstance (aClasses);
  }
}
