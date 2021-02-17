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

import javax.annotation.Nullable;

import com.helger.xml.namespace.IIterableNamespaceContext;
import com.helger.xml.namespace.MapBasedNamespaceContext;

import eu.de4a.edm.jaxb.cccev.CCCEVConceptType;

/**
 * XML marshaller for CCCEV concept
 *
 * @author Philip Helger
 */
public class ConceptMarshaller extends AbstractCCCEVMarshaller <CCCEVConceptType>
{
  public ConceptMarshaller ()
  {
    this (null);
  }

  public ConceptMarshaller (@Nullable final IIterableNamespaceContext aAdditionalNSPrefixes)
  {
    super (CCCEVConceptType.class, x -> new eu.de4a.edm.jaxb.cccev.ObjectFactory ().createConcept (x));

    if (aAdditionalNSPrefixes != null)
    {
      final MapBasedNamespaceContext aCtx = CCCEVNamespaceContext.getInstance ().getClone ();
      aCtx.addMappings (aAdditionalNSPrefixes);
      setNamespaceContext (aCtx);
    }
  }
}
