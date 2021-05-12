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
package eu.de4a.iem.xml;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.helger.commons.exception.InitializationException;

@Immutable
public class XSDDataTypeHelper
{
  private static final DatatypeFactory DTF;

  static
  {
    try
    {
      DTF = DatatypeFactory.newInstance ();
    }
    catch (final DatatypeConfigurationException ex)
    {
      throw new InitializationException ("Failed to init XSD DataTypeFactory", ex);
    }
  }

  private XSDDataTypeHelper ()
  {}

  @Nonnull
  public static DatatypeFactory getFactory ()
  {
    return DTF;
  }
}
