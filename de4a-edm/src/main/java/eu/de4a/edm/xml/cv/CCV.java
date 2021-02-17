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
package eu.de4a.edm.xml.cv;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.ubl23.CUBL23;

/**
 * Constants for the Core vocabulary
 *
 * @author Philip Helger
 */
public final class CCV
{
  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CCV.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS = new CommonsArrayList <> (CUBL23.XSD_UNQUALIFIED_DATA_TYPES,
                                                                               new ClassPathResource ("schemas/provided/CoreVocabularies-BasicComponents-1.1.xsd",
                                                                                                      _getCL ()),
                                                                               new ClassPathResource ("schemas/provided/CoreVocabularies-AggregateComponents-1.1.xsd",
                                                                                                      _getCL ())).getAsUnmodifiable ();

  private CCV ()
  {}
}
