/*
 * Copyright 2021-2023 Ona Systems, Inc
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

package org.smartregister.fhircore.engine.configuration.migration

import kotlinx.serialization.Serializable
import org.hl7.fhir.r4.model.ResourceType
import org.smartregister.fhircore.engine.domain.model.DataQuery
import org.smartregister.fhircore.engine.domain.model.RuleConfig

@Serializable
data class MigrationConfig(
  val resourceType: ResourceType,
  val updateValues: List<UpdateValueConfig>,
  val dataQueries: List<DataQuery>?,
  val version: Int,
  val purgeAffectedResources: Boolean = false,
  val resourceFilterExpression: ResourceFilterExpression? = null,
) : java.io.Serializable

@Serializable
data class ResourceFilterExpression(
  val conditionalFhirPathExpressions: List<String>,
  val matchAll: Boolean = true,
) : java.io.Serializable

@Serializable
data class UpdateValueConfig(
  val jsonPathExpression: String,
  val valueRule: RuleConfig,
) : java.io.Serializable