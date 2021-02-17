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

import eu.de4a.edm.jaxb.cccev.CCCEVRequirementType;

/**
 * XML marshaller for CCCEV requirements
 *
 * @author Philip Helger
 */
public class RequirementMarshaller extends AbstractCCCEVMarshaller <CCCEVRequirementType>
{
  public RequirementMarshaller ()
  {
    super (CCCEVRequirementType.class, x -> new eu.de4a.edm.jaxb.cccev.ObjectFactory ().createRequirement (x));
  }
}
