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
package eu.de4a.edm.xml.de4a;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.edm.jaxb.de_usi.RequestForwardEvidence;

/**
 * DE4A DE USI Request Marshaller
 *
 * @author Philip Helger
 */
public class DE_USI_RequestMarshaller extends GenericJAXBMarshaller <RequestForwardEvidence>
{
  private static ICommonsList <ClassPathResource> _getXSDs ()
  {
    final ICommonsList <ClassPathResource> ret = new CommonsArrayList <> ();
    ret.addAll (CDE4AJaxb.XSDS);
    ret.add (CDE4AJaxb.XSD_DE_USI);
    return ret;
  }

  public DE_USI_RequestMarshaller ()
  {
    super (RequestForwardEvidence.class,
           _getXSDs (),
           x -> new JAXBElement <> (new QName ("http://www.de4a.eu/2020/data/evaluator/pattern/usi", "RequestForwardEvidence"),
                                    RequestForwardEvidence.class,
                                    x));
  }
}
