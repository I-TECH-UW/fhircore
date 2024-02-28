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

package org.smartregister.fhircore.engine.task

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ca.uhn.fhir.rest.param.ParamPrefixEnum
import com.google.android.fhir.search.search
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.math.BigDecimal
import java.util.Date
import kotlinx.coroutines.withContext
import org.hl7.fhir.r4.model.Enumerations
import org.hl7.fhir.r4.model.Group
import org.hl7.fhir.r4.model.MessageDefinition
import org.hl7.fhir.r4.model.Observation
import org.hl7.fhir.r4.model.ResourceType
import org.smartregister.fhircore.engine.data.local.DefaultRepository
import org.smartregister.fhircore.engine.domain.model.NotificationData
import org.smartregister.fhircore.engine.domain.notification.FhirNotificationManager
import org.smartregister.fhircore.engine.util.DispatcherProvider
import org.smartregister.fhircore.engine.util.extension.extractId

@HiltWorker
class FhirStockOutNotifierWorker
@AssistedInject
constructor(
  @Assisted val context: Context,
  @Assisted workerParams: WorkerParameters,
  val defaultRepository: DefaultRepository,
  val dispatcherProvider: DispatcherProvider,
  val notificationManager: FhirNotificationManager,
  val gson: Gson,
) : CoroutineWorker(context, workerParams) {
  override suspend fun doWork(): Result {
    return withContext(dispatcherProvider.io()) {
      val stockOutInventoryDetails = getStockOutInventoryDetails()

      if (stockOutInventoryDetails.isNotEmpty()) {
        val descriptionBuilder =
          StringBuilder()
            .appendLine("Almost running out below inventories")
            .appendLine()
            .append(stockOutInventoryDetails.joinToString("\n"))

        val notificationData =
          NotificationData(
            title = "Stock Out Remainder",
            description = descriptionBuilder.toString(),
            type = "Stock",
          )

        notificationManager.showNotification(notificationData)

        MessageDefinition()
          .apply {
            status = Enumerations.PublicationStatus.ACTIVE
            category = MessageDefinition.MessageSignificanceCategory.NOTIFICATION
            name = gson.toJson(notificationData)
            title = notificationData.title
            description = notificationData.description
            purpose = notificationData.type
            date = Date()
          }
          .run { defaultRepository.addOrUpdate(resource = this) }
      }

      Result.success()
    }
  }

  private suspend fun getStockOutInventoryDetails(): List<String> {
    val inventorySet = hashSetOf<String>()
    return defaultRepository.fhirEngine
      .search<Observation> {
        filter(
          Observation.STATUS,
          { value = of(Observation.ObservationStatus.PRELIMINARY.toCode()) },
        )

        filter(
          Observation.COMPONENT_VALUE_QUANTITY,
          {
            prefix = ParamPrefixEnum.LESSTHAN
            value = BigDecimal.valueOf(5)
          },
        )
      }
      .map { it.resource }
      .mapNotNull { obs ->
        if (inventorySet.add(obs.subject.extractId())) {
          val inventory =
            defaultRepository.fhirEngine.get(ResourceType.Group, obs.subject.extractId()) as Group

          StringBuilder()
            .append(inventory.name)
            .append(" -> ")
            .appendLine(obs.componentFirstRep.valueQuantity.value)
            .toString()
        } else {
          null
        }
      }
  }

  companion object {
    const val WORK_ID = "FhirStockOutNotifierWorker"
  }
}
