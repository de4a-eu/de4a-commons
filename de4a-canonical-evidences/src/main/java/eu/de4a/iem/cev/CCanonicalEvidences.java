/*
 * Copyright (C) 2023, Partners of the EU funded DE4A project consortium
 *   (https://www.de4a.eu/consortium), under Grant Agreement No.870635
 * Author: Austrian Federal Computing Center (BRZ)
 *
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
package eu.de4a.iem.cev;

import javax.annotation.concurrent.Immutable;

/**
 * IEM constants
 *
 * @author Philip Helger
 * @since 0.2.1
 */
@Immutable
public final class CCanonicalEvidences
{
  // Iteration 2 identifier for the IEM "/SpecificationId" element
  public static final String SPECIFICATION_ID = "de4a-iem-v2";

  private CCanonicalEvidences ()
  {}
}
