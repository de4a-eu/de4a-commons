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

import java.util.Map.Entry;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Singleton;
import com.helger.xml.namespace.MapBasedNamespaceContext;

import eu.de4a.edm.xml.cagv.CAGVNamespaceContext;
import eu.de4a.edm.xml.cv.CCVNamespaceContext;

/**
 * XML Namespace context for DE4A
 *
 * @author Philip Helger
 */
@Singleton
public class DE4ANamespaceContext extends MapBasedNamespaceContext
{
  private static final class SingletonHolder
  {
    static final DE4ANamespaceContext s_aInstance = new DE4ANamespaceContext ();
  }

  protected DE4ANamespaceContext ()
  {
    addMappings (CAGVNamespaceContext.getInstance ());
    for (final Entry <String, String> aEntry : CCVNamespaceContext.getInstance ().getPrefixToNamespaceURIMap ().entrySet ())
      if (!isPrefixMapped (aEntry.getKey ()))
        addMapping (aEntry.getKey (), aEntry.getValue ());
  }

  @Nonnull
  public static DE4ANamespaceContext getInstance ()
  {
    return SingletonHolder.s_aInstance;
  }
}
