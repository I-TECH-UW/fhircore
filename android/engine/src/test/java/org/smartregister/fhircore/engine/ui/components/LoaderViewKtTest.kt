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

package org.smartregister.fhircore.engine.ui.components

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.smartregister.fhircore.engine.R
import org.smartregister.fhircore.engine.robolectric.RobolectricTest
import org.smartregister.fhircore.engine.ui.components.register.LOADER_DIALOG_PROGRESS_BAR_TAG
import org.smartregister.fhircore.engine.ui.components.register.LOADER_DIALOG_PROGRESS_MSG_TAG
import org.smartregister.fhircore.engine.ui.components.register.LOADER_DIALOG_SYNC_PROGRESS_STATE_TEXT_TAG
import org.smartregister.fhircore.engine.ui.components.register.LoaderDialog

class LoaderViewKtTest : RobolectricTest() {

  @get:Rule val composeRule = createComposeRule()

  @Test
  fun testLoaderDialogView() {

    composeRule.setContent { LoaderDialog() }
    composeRule.onNodeWithTag(LOADER_DIALOG_PROGRESS_BAR_TAG).assertExists()
    composeRule.onNodeWithTag(LOADER_DIALOG_PROGRESS_BAR_TAG).assertIsDisplayed()

    composeRule.onNodeWithTag(LOADER_DIALOG_PROGRESS_MSG_TAG).assertExists()
    composeRule.onNodeWithTag(LOADER_DIALOG_PROGRESS_MSG_TAG).assertIsDisplayed()
    composeRule
      .onNodeWithTag(LOADER_DIALOG_PROGRESS_MSG_TAG)
      .assertTextEquals(
        ApplicationProvider.getApplicationContext<Application>().getString(R.string.syncing)
      )
  }

  @Test
  fun testLoaderDialogViewDefaultDoesNotShowSyncProgressStateText() {
    composeRule.setContent { LoaderDialog() }

    composeRule.onNodeWithTag(LOADER_DIALOG_SYNC_PROGRESS_STATE_TEXT_TAG).assertDoesNotExist()
  }

  @Test
  fun testLoaderDialogViewShowsProgressStateTextWhenSyncProgressStateFlowValueNotBlank() {
    val progressStateText = "28% downloaded"
    composeRule.setContent {
      LoaderDialog(syncProgressStateFlow = MutableStateFlow(progressStateText))
    }
    composeRule
      .onNodeWithTag(LOADER_DIALOG_SYNC_PROGRESS_STATE_TEXT_TAG)
      .assertIsDisplayed()
      .assertTextEquals(progressStateText)
  }
}
