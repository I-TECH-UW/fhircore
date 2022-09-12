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

package org.smartregister.fhircore.engine.configuration.app

import kotlinx.serialization.Serializable
import org.hl7.fhir.r4.model.Coding
import org.smartregister.fhircore.engine.configuration.ConfigType
import org.smartregister.fhircore.engine.configuration.Configuration
import org.smartregister.fhircore.engine.configuration.ConfigurationRegistry
import org.smartregister.fhircore.engine.sync.SyncStrategy
import org.smartregister.fhircore.engine.util.SharedPreferencesHelper
import org.smartregister.model.practitioner.KeycloakUserDetails

@Serializable
data class ApplicationConfiguration(
  override var appId: String,
  override var configType: String = ConfigType.Application.name,
  override val classification: String,
  var theme: String = "",
  var languages: List<String> = listOf("en"),
  var syncInterval: Long = 30,
  var scheduleDefaultPlanWorker: Boolean = true,
  var applicationName: String = "",
  var appLogoIconResourceFile: String = "ic_default_logo",
  var patientTypeFilterTagViaMetaCodingSystem: String = "",
  var count: String = ConfigurationRegistry.DEFAULT_COUNT,
  val syncStrategy: List<String> = listOf(),
) : Configuration {

  fun getMandatoryTags(sharedPreferencesHelper: SharedPreferencesHelper): List<Coding> {
    val tags = mutableListOf<Coding>()
    syncStrategy.forEach { strategy ->
      when (strategy) {
        SyncStrategy.CARETEAM.value,
        SyncStrategy.ORGANIZATION.value,
        SyncStrategy.LOCATION.value -> {
          sharedPreferencesHelper.read<List<String>>(strategy)?.forEach { id ->
            tags.add(SyncStrategy.valueOf(strategy.uppercase()).tag.apply { code = id }.copy())
          }
        }
        SyncStrategy.PRACTITIONER.value -> {
          sharedPreferencesHelper.read<KeycloakUserDetails>(strategy)?.let { practitioner ->
            tags.add(
              SyncStrategy.valueOf(strategy.uppercase()).tag.apply { code = practitioner.id }
            )
          }
        }
      }
    }
    return tags
  }
}

/**
 * A function providing a DSL for configuring [ApplicationConfiguration] used in a FHIR application
 *
 * @param appId Set unique identifier for the app
 * @param classification Set the
 * @param languages Sets the languages for the app
 * @param syncInterval Sets the periodic sync interval in seconds. Default 30.
 * @param applicationName Sets the application display name
 * @param appLogoIconResourceFile Sets the application logo thumb icon, this must be png file inside
 * @param patientTypeFilterTagViaMetaCodingSystem sets code in Patient meta, and will use for
 * filtering patient
 * @param count Sets the application maximum records when downloading resource drawable folder
 */
fun applicationConfigurationOf(
  appId: String = "",
  classification: String = "",
  theme: String = "",
  languages: List<String> = listOf("en"),
  syncInterval: Long = 30,
  scheduleDefaultPlanWorker: Boolean = true,
  applicationName: String = "",
  appLogoIconResourceFile: String = "",
  patientTypeFilterTagViaMetaCodingSystem: String = "",
  count: String = ConfigurationRegistry.DEFAULT_COUNT,
  syncStrategy: List<String> = listOf(),
): ApplicationConfiguration =
  ApplicationConfiguration(
    appId = appId,
    classification = classification,
    theme = theme,
    languages = languages,
    syncInterval = syncInterval,
    scheduleDefaultPlanWorker = scheduleDefaultPlanWorker,
    applicationName = applicationName,
    appLogoIconResourceFile = appLogoIconResourceFile,
    patientTypeFilterTagViaMetaCodingSystem = patientTypeFilterTagViaMetaCodingSystem,
    count = count,
    syncStrategy = syncStrategy
  )
