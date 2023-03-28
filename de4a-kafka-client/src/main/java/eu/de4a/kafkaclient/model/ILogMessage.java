/*
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
package eu.de4a.kafkaclient.model;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public interface ILogMessage
{
  @Nonnull
  @Nonempty
  default String getLogCode ()
  {
    return getModule ().getID () + getLevel ().getCode () + getCode ();
  }

  @Nonnull
  @Nonempty
  String getKey ();

  @Nonnull
  EExternalModule getModule ();

  @Nonnull
  ELogMessageLevel getLevel ();

  @Nonnull
  @Nonempty
  String getCode ();
}
