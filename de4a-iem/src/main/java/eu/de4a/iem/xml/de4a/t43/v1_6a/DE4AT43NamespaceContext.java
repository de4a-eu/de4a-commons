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
package eu.de4a.iem.xml.de4a.t43.v1_6a;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.Singleton;
import com.helger.ubl23.UBL23NamespaceContext;
import com.helger.xml.namespace.MapBasedNamespaceContext;

/**
 * XML Namespace context for DE4A T4.3 v1.6
 *
 * @author Philip Helger
 */
@Singleton
public class DE4AT43NamespaceContext extends MapBasedNamespaceContext
{
  private static final class SingletonHolder
  {
    static final DE4AT43NamespaceContext INSTANCE = new DE4AT43NamespaceContext ();
  }

  protected DE4AT43NamespaceContext ()
  {
    addMappings (UBL23NamespaceContext.getInstance ());
    addMapping ("cva", "http://www.w3.org/ns/corevocabulary/AggregateComponents");
    addMapping ("cvb", "http://www.w3.org/ns/corevocabulary/BasicComponents");
    addMapping ("business", "http://www.w3.org/ns/corevocabulary/business");
    addMapping ("location", "http://www.w3.org/ns/corevocabulary/location");
    addMapping ("person", "http://www.w3.org/ns/corevocabulary/person");
  }

  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext getBirthEvidenceInstance ()
  {
    final MapBasedNamespaceContext ret = SingletonHolder.INSTANCE.getClone ();
    ret.addDefaultNamespaceURI (CT43.NS_URI_BIRTH_EVIDENCE);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext getDomicileRegistrationEvidenceInstance ()
  {
    final MapBasedNamespaceContext ret = SingletonHolder.INSTANCE.getClone ();
    ret.addDefaultNamespaceURI (CT43.NS_URI_DOMICILE_REGISTRATION_EVIDENCE);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static MapBasedNamespaceContext getMarriageEvidenceInstance ()
  {
    final MapBasedNamespaceContext ret = SingletonHolder.INSTANCE.getClone ();
    ret.addDefaultNamespaceURI (CT43.NS_URI_MARRIAGE_EVIDENCE);
    return ret;
  }
}
