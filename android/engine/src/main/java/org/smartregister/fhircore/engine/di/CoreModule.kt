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

package org.smartregister.fhircore.engine.di

import android.accounts.AccountManager
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.context.FhirVersionEnum
import com.google.android.fhir.FhirEngine
import com.google.android.fhir.knowledge.KnowledgeManager
import com.google.android.fhir.workflow.FhirOperator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton
import org.hl7.fhir.r4.context.SimpleWorkerContext
import org.hl7.fhir.r4.model.Parameters
import org.hl7.fhir.r4.utils.FHIRPathEngine
import org.smartregister.fhircore.engine.util.DispatcherProvider
import org.smartregister.fhircore.engine.util.helper.TransformSupportServices

@InstallIn(SingletonComponent::class)
@Module
class CoreModule {

  @Singleton
  @Provides
  fun provideWorkerContextProvider(): SimpleWorkerContext =
    SimpleWorkerContext().apply {
      setExpansionProfile(Parameters())
      isCanRunWithoutTerminology = true
    }

  @Singleton
  @Provides
  fun provideFHIRPathEngine(transformSupportServices: TransformSupportServices) =
    FHIRPathEngine(transformSupportServices.simpleWorkerContext)

  @Singleton
  @Provides
  fun provideApplicationManager(@ApplicationContext context: Context): AccountManager =
    AccountManager.get(context)

  @Singleton
  @Provides
  fun provideKnowledgeManager(@ApplicationContext context: Context): KnowledgeManager =
    KnowledgeManager.create(context)

  @Singleton @Provides fun provideFhirContext() = FhirContext.forCached(FhirVersionEnum.R4)

  @Singleton
  @Provides
  fun provideFhirOperator(fhirEngine: FhirEngine): FhirOperator =
    FhirOperator(fhirContext = FhirContext.forCached(FhirVersionEnum.R4), fhirEngine = fhirEngine)

  @Singleton
  @Provides
  fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
    val userPreferences = "preferences_datastore"
    return PreferenceDataStoreFactory.create(
      scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
      produceFile = { appContext.preferencesDataStoreFile(userPreferences) }
    )
  }

}
