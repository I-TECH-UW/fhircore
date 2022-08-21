/*
 * Copyright 2021 Ona Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.smartregister.fhircore.engine.configuration

import kotlinx.serialization.Serializable
import org.smartregister.fhircore.engine.domain.model.QuestionnaireType
import org.smartregister.fhircore.engine.ui.questionnaire.QuestionnaireActivity

@Serializable
data class QuestionnaireConfig(
  val id: String,
  val title: String? = null,
  val saveButtonText: String? = null,
  val setPractitionerDetails: Boolean = false,
  val setOrganizationDetails: Boolean = false,
  val planDefinitions: List<String>? = null,
  val type: QuestionnaireType = QuestionnaireType.DEFAULT,
  val customActivity: String? = null
)

enum class CustomQuestionnaireActivity {
  REMOVE_FAMILY,
  REMOVE_FAMILY_MEMBER;

  fun getCustomClass(): Class<out QuestionnaireActivity> {
    return when (this) {
      REMOVE_FAMILY ->
        Class.forName(REMOVE_FAMILY_QUESTIONNAIRE_ACTIVITY) as Class<out QuestionnaireActivity>
      REMOVE_FAMILY_MEMBER ->
        Class.forName(REMOVE_FAMILY_MEMBER_QUESTIONNAIRE_ACTIVITY) as
          Class<out QuestionnaireActivity>
    }
  }

  companion object {
    const val REMOVE_FAMILY_QUESTIONNAIRE_ACTIVITY =
      "org.smartregister.fhircore.quest.ui.family.remove.family.RemoveFamilyQuestionnaireActivity"
    const val REMOVE_FAMILY_MEMBER_QUESTIONNAIRE_ACTIVITY =
      "org.smartregister.fhircore.quest.ui.family.remove.member.RemoveFamilyMemberQuestionnaireActivity"
  }
}
