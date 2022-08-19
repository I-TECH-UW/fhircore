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

package org.smartregister.fhircore.quest.ui.patient.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.hl7.fhir.r4.model.ResourceType
import org.smartregister.fhircore.engine.appfeature.AppFeature
import org.smartregister.fhircore.engine.appfeature.model.HealthModule
import org.smartregister.fhircore.engine.data.local.register.PatientRegisterRepository
import org.smartregister.fhircore.engine.ui.questionnaire.QuestionnaireActivity
import org.smartregister.fhircore.engine.ui.questionnaire.QuestionnaireType
import org.smartregister.fhircore.engine.util.extension.ACTIVE_ANC_REGEX
import org.smartregister.fhircore.engine.util.extension.asReference
import org.smartregister.fhircore.engine.util.extension.launchQuestionnaire
import org.smartregister.fhircore.engine.util.extension.launchQuestionnaireForResult
import org.smartregister.fhircore.engine.util.extension.monthsPassed
import org.smartregister.fhircore.engine.util.extension.yearsPassed
import org.smartregister.fhircore.quest.R
import org.smartregister.fhircore.quest.navigation.MainNavigationScreen
import org.smartregister.fhircore.quest.navigation.NavigationArg
import org.smartregister.fhircore.quest.navigation.OverflowMenuFactory
import org.smartregister.fhircore.quest.navigation.OverflowMenuHost
import org.smartregister.fhircore.quest.ui.family.remove.member.RemoveFamilyMemberQuestionnaireActivity
import org.smartregister.fhircore.quest.ui.shared.models.ProfileViewData
import org.smartregister.fhircore.quest.util.mappers.ProfileViewDataMapper

@HiltViewModel
class PatientProfileViewModel
@Inject
constructor(
  val overflowMenuFactory: OverflowMenuFactory,
  val patientRegisterRepository: PatientRegisterRepository,
  val profileViewDataMapper: ProfileViewDataMapper
) : ViewModel() {

  val patientProfileUiState: MutableState<PatientProfileUiState> =
    mutableStateOf(getProfileUiState())

  val patientProfileViewData: MutableState<ProfileViewData.PatientProfileViewData> =
    mutableStateOf(ProfileViewData.PatientProfileViewData())

  fun fetchPatientProfileData(
    appFeatureName: String?,
    healthModule: HealthModule,
    patientId: String
  ) {
    if (patientId.isNotEmpty()) {
      viewModelScope.launch {
        patientRegisterRepository.loadPatientProfileData(appFeatureName, healthModule, patientId)
          ?.let {
            patientProfileViewData.value =
              profileViewDataMapper.transformInputToOutputModel(it) as
                ProfileViewData.PatientProfileViewData

            // TODO only display some overflow menu items when certain conditions are met
            // dynamically from config
            patientProfileUiState.value = getProfileUiState(patientProfileViewData.value)
          }
      }
    }
  }

  // TODO handle dynamic profile menu with configurations; avoid string comparison
  fun getProfileUiState(profileData: ProfileViewData.PatientProfileViewData? = null) =
    PatientProfileUiState(
      overflowMenuFactory.retrieveOverflowMenuItems(
        OverflowMenuHost.PATIENT_PROFILE,
        listOfNotNull(
<<<<<<< HEAD
          Pair(R.id.record_sick_child, profileData?.dob?.let { it.yearsPassed() >= 5 } ?: false),
          Pair(
            R.id.record_as_anc,
            profileData?.let {
              // hide menu item for people not female | not reproductive age | enrolled into anc
              it.sex.startsWith("F", true).not() ||
                (it.dob?.yearsPassed() in 15..45).not() ||
                it.tasks.any { it.action.matches(Regex(ACTIVE_ANC_REGEX)) }
            }
              ?: false
          ),
          Pair(
            R.id.pregnancy_outcome,
            profileData?.tasks?.none { it.action.matches(Regex(ACTIVE_ANC_REGEX)) } ?: false
          ),
          Pair(
            R.id.add_to_family_planning,
            profileData?.let {
              it.sex.startsWith("F", true).not() &&
               it.tasks.none { it.action.matches(Regex(ACTIVE_ANC_REGEX)) } &&
               (it.dob?.yearsPassed() in 15..45).not()
            }
              ?: false
          )
=======
          Pair(R.id.record_sick_child, profileData?.dob?.let { it.yearsPassed() >= 5 } ?: false)
>>>>>>> parent of 395d7b378 (1345 | ANC care plan (#1359))
        )
      )
    )

  fun onEvent(event: PatientProfileEvent) =
    when (event) {
      is PatientProfileEvent.LoadQuestionnaire ->
        event.context.launchQuestionnaire<QuestionnaireActivity>(event.questionnaireId)
      is PatientProfileEvent.SeeAll -> {
        /* TODO(View all records in this category e.g. all medical history, tasks etc) */
      }
      is PatientProfileEvent.OverflowMenuClick -> {
        // TODO use navigation items from config and handle these actions dynamically
        // https://github.com/opensrp/fhircore/issues/1371
        when (event.menuId) {
          R.id.individual_details ->
            event.context.launchQuestionnaire<QuestionnaireActivity>(
              questionnaireId = FAMILY_MEMBER_REGISTER_FORM,
              clientIdentifier = event.patientId,
              questionnaireType = QuestionnaireType.EDIT
            )
          R.id.view_family -> {
            event.familyId?.let { familyId ->
              val urlParams =
                NavigationArg.bindArgumentsOf(
                  Pair(NavigationArg.FEATURE, AppFeature.HouseholdManagement.name),
                  Pair(NavigationArg.HEALTH_MODULE, HealthModule.FAMILY.name),
                  Pair(NavigationArg.PATIENT_ID, familyId)
                )
              event.navController.navigate(
                route = MainNavigationScreen.FamilyProfile.route + urlParams
              )
            }
          }
          R.id.remove_family_member ->
            event.context.launchQuestionnaire<RemoveFamilyMemberQuestionnaireActivity>(
              questionnaireId = REMOVE_FAMILY_FORM,
              clientIdentifier = event.patientId,
              intentBundle = bundleOf(Pair(NavigationArg.FAMILY_ID, event.familyId))
            )
          R.id.record_as_anc ->
            event.context.launchQuestionnaire<QuestionnaireActivity>(
              questionnaireId = ANC_ENROLLMENT_FORM,
              clientIdentifier = event.patientId,
              questionnaireType = QuestionnaireType.DEFAULT
            )
<<<<<<< HEAD
          R.id.pregnancy_outcome ->
            event.context.launchQuestionnaire<QuestionnaireActivity>(
              questionnaireId = PREGNANCY_OUTCOME_FORM,
              clientIdentifier = event.patientId,
              questionnaireType = QuestionnaireType.DEFAULT
            )
          R.id.record_sick_child ->
            event.context.launchQuestionnaire<QuestionnaireActivity>(
              questionnaireId =
                if (event.patient.dob!!.monthsPassed() < 2) SICK_CHILD_UNDER_2M_FORM
                else SICK_CHILD_ABOVE_2M_FORM,
              clientIdentifier = event.patientId,
              questionnaireType = QuestionnaireType.DEFAULT
            )
          R.id.add_to_family_planning ->
=======
          R.id.record_sick_child ->
>>>>>>> parent of 395d7b378 (1345 | ANC care plan (#1359))
            event.context.launchQuestionnaire<QuestionnaireActivity>(
              questionnaireId = FAMILY_PLANNING,
              clientIdentifier = event.patientId,
              questionnaireType = QuestionnaireType.DEFAULT
            )
          else -> {}
        }
      }
      is PatientProfileEvent.OpenTaskForm ->
        event.context.launchQuestionnaireForResult<QuestionnaireActivity>(
          questionnaireId = event.taskFormId,
          clientIdentifier = event.patientId,
          backReference = event.taskId?.asReference(ResourceType.Task)?.reference
        )
    }

  companion object {
    const val REMOVE_FAMILY_FORM = "remove-family"
    const val FAMILY_MEMBER_REGISTER_FORM = "family-member-registration"
    const val ANC_ENROLLMENT_FORM = "anc-patient-registration"
    const val SICK_CHILD_UNDER_2M_FORM = "sick-child-under-2m"
    const val SICK_CHILD_ABOVE_2M_FORM = "sick-child-above-2m"
    const val FAMILY_PLANNING = "family-planning"
  }
}
