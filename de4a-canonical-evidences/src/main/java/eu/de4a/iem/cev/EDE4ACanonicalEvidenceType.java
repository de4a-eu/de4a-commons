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
package eu.de4a.iem.cev;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;

import eu.de4a.iem.core.IDE4ACanonicalEvidenceType;

/**
 * Contains all the predefined DE4A Canonical Evidences
 *
 * @author Philip Helger
 */
public enum EDE4ACanonicalEvidenceType implements IDE4ACanonicalEvidenceType
{
  T41_HIGHER_EDUCATION_EVIDENCE_2021_04_13 ("t41-hed-2021-04-13",
                                            "T4.1 Higher Education Evidence v2021-04-13",
                                            eu.de4a.iem.cev.de4a.t41.CT41.getAllXSDsHigherEducationDiploma ()),
  T41_SECONDARY_EDUCATION_EVIDENCE_2022_05_12 ("t41-sed-2022-05-12",
                                               "T4.1 Secondary Education Evidence v2022-05-12",
                                               eu.de4a.iem.cev.de4a.t41.CT41.getAllXSDsSecondaryEducationDiploma ()),
  T41_DISABILITY_EVIDENCE_2022_05_12 ("t41-disability-2022-05-12",
                                      "T4.1 Disability Evidence v2022-05-12",
                                      eu.de4a.iem.cev.de4a.t41.CT41.getAllXSDsDisability ()),
  T41_LARGE_FAMILY_EVIDENCE_2022_05_12 ("t41-largefam-2022-05-12",
                                        "T4.1 Large Family v2022-05-12",
                                        eu.de4a.iem.cev.de4a.t41.CT41.getAllXSDsLargeFamily ()),

  T42_LEGAL_ENTITY_V06 ("t42-legalentity-v06", "T4.2 Legal Entity v0.6", eu.de4a.iem.cev.de4a.t42.CT42.getAllXSDs ()),

  T43_BIRTH_EVIDENCE_V17 ("t43-birth-v17",
                          "T4.3 Birth Evidence v1.7",
                          eu.de4a.iem.cev.de4a.t43.CT43.getAllBirthEvidenceXSDs ()),
  T43_DOMREG_EVIDENCE_V17 ("t43-domreg-v17",
                           "T4.3 Domicile Registration Evidence v1.7",
                           eu.de4a.iem.cev.de4a.t43.CT43.getAllDomicileRegistrationEvidenceXSDs ()),
  T43_MARRIAGE_EVIDENCE_V17 ("t43-marriage-v17",
                             "T4.3 Marriage Evidence v1.7",
                             eu.de4a.iem.cev.de4a.t43.CT43.getAllMarriageEvidenceXSDs ()),
  T43_PENSION_MOL_EVIDENCE_V01 ("t43-pensionmol-v01",
                                "T4.3 Pension Means of Living Evidence v0.1",
                                eu.de4a.iem.cev.de4a.t43.CT43.getAllPensionMOLEvidenceXSDs ()),
  T43_UNEMPLOYMENT_MOL_EVIDENCE_V01 ("t43-unemploymentmol-v01",
                                     "T4.3 Unemployment Means of Living Evidence v0.1",
                                     eu.de4a.iem.cev.de4a.t43.CT43.getAllUnemploymentMOLEvidenceXSDs ()),
  T43_WORKING_LIFE_MOL_EVIDENCE_V01 ("t43-workinglifemol-v01",
                                     "T4.3 Working Life Means of Living Evidence v0.1",
                                     eu.de4a.iem.cev.de4a.t43.CT43.getAllWorkingLifeMOLEvidenceXSDs ());

  private final String m_sID;
  private final String m_sDisplayName;
  private final ICommonsList <ClassPathResource> m_aXSDs;

  EDE4ACanonicalEvidenceType (@Nonnull @Nonempty final String sID,
                              @Nonnull @Nonempty final String sDisplayName,
                              @Nonnull final ICommonsList <ClassPathResource> aXSDs)
  {
    m_sID = sID;
    m_sDisplayName = sDisplayName;
    m_aXSDs = aXSDs;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <? extends ClassPathResource> getAllXSDs ()
  {
    return m_aXSDs;
  }
}
