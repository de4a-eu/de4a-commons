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
package eu.de4a.edm.schematron;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.regrep.RegRep4NamespaceContext;
import com.helger.schematron.svrl.ISVRLLocationBeautifierSPI;

import eu.de4a.edm.xml.cccev.CCCEVNamespaceContext;

/**
 * Beautifier for Schematron error messages.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class TOOPSVRLLocationBeautifier implements ISVRLLocationBeautifierSPI
{
  @Nullable
  public String getReplacementText (@Nonnull final String sNamespaceURI, @Nonnull final String sLocalName)
  {
    String sPrefix = CCCEVNamespaceContext.getInstance ().getCustomPrefix (sNamespaceURI);
    if (sPrefix == null)
      sPrefix = RegRep4NamespaceContext.getInstance ().getCustomPrefix (sNamespaceURI);

    if (sPrefix != null)
      return sPrefix + ':' + sLocalName;

    return null;
  }
}
