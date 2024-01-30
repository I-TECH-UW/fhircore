/*
 * Copyright 2021-2024 Ona Systems, Inc
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

package org.smartregister.fhircore.engine.configuration.app

import org.hl7.fhir.r4.model.Coding
import org.hl7.fhir.r4.model.Encounter
import org.hl7.fhir.r4.model.Enumerations
import org.hl7.fhir.r4.model.Resource
import org.hl7.fhir.r4.model.ResourceType
import org.hl7.fhir.r4.model.SearchParameter
import org.smartregister.fhircore.engine.sync.ResourceTag
import org.smartregister.fhircore.engine.util.SharedPreferenceKey
import org.smartregister.fhircore.engine.util.SharedPreferencesHelper
import org.smartregister.fhircore.engine.util.extension.extractLogicalIdUuid

/** An interface that provides the application configurations. */
interface ConfigService {

  /** Provide [AuthConfiguration] for the application. */
  fun provideAuthConfiguration(): AuthConfiguration

  /** Define a list of [ResourceTag] for the application. */
  fun defineResourceTags(): List<ResourceTag>

  /**
   * Provide a list of [Coding] that represents [ResourceTag]. [Coding] can be directly appended to
   * a FHIR resource.
   */
  fun provideResourceTags(sharedPreferencesHelper: SharedPreferencesHelper, currentResource: Resource): List<Coding> {
    val tags = mutableListOf<Coding>()
    defineResourceTags().forEach { strategy ->
      when(strategy.type) {
        ResourceType.Practitioner.name -> {
          val id = sharedPreferencesHelper.read(SharedPreferenceKey.PRACTITIONER_ID.name, null)
          // TODO: refactor below to remove duplication w/Patient.name + Location.name case
          if (id.isNullOrBlank()) {
            strategy.tag.let { tag -> tags.add(tag.copy().apply { code = "Not defined" }) }
          } else {
            strategy.tag.let { tag ->
              tags.add(tag.copy().apply { code = id.extractLogicalIdUuid() })
            }
          }
        }
        // TODO: write a PatientLocation helper for the below string concatenation
        ResourceType.Patient.name + ResourceType.Location.name -> {
          val id = getPatientLocationIdFromCurrentResource(currentResource)
          // TODO: refactor below to remove duplication w/Practitioner.name case
          if (id.isNullOrBlank()) {
            strategy.tag.let { tag -> tags.add(tag.copy().apply { code = "Not defined" }) }
          } else {
            strategy.tag.let { tag ->
              tags.add(tag.copy().apply { code = id.extractLogicalIdUuid() })
            }
          }
        }
        else -> {
          val ids = sharedPreferencesHelper.read<List<String>>(strategy.type)
          if (ids.isNullOrEmpty()) {
            strategy.tag.let { tag -> tags.add(tag.copy().apply { code = "Not defined" }) }
          } else {
            ids.forEach { id ->
              strategy.tag.let { tag ->
                tags.add(tag.copy().apply { code = id.extractLogicalIdUuid() })
              }
            }
          }
        }
      }
    }

    return tags
  }

  fun getPatientLocationIdFromCurrentResource(currentResource: Resource): String? {
    // Find the patient resource connected to the currentResource and return the id.
    // It would be nice to get all the values for the current resource, filter those that are
    // references to patient, and then get the id of the location linked to that patient
    // but if a resource has or will have multiple references to a patient that is ambiguous
    val patientResource = when (currentResource.resourceType) {
      ResourceType.Patient -> currentResource
      ResourceType.Encounter -> (currentResource as Encounter).subject
      // TODO: add more cases for other resources that can be linked to a patient
      else -> {
        // TODO: log no matching resource
        null
      }
    }
    if (patientResource != null) {
      // TODO: find the location connected (how?) to that patient and return the location id
      throw NotImplementedError("Not implemented")
    }
    return null
  }

  fun provideConfigurationSyncPageSize(): String

  /**
   * Provide a list of custom search parameters.
   *
   * @return list of predefined custom group search parameters.
   */
  fun provideCustomSearchParameters(): List<SearchParameter> {
    val activeGroupSearchParameter =
      SearchParameter().apply {
        url = "http://smartregister.org/SearchParameter/group-active"
        addBase("Group")
        name = ACTIVE_SEARCH_PARAM
        code = ACTIVE_SEARCH_PARAM
        type = Enumerations.SearchParamType.TOKEN
        expression = "Group.active"
        description = "Search the active field"
      }

    return listOf(activeGroupSearchParameter)
  }

  companion object {
    const val ACTIVE_SEARCH_PARAM = "active"
  }
}
