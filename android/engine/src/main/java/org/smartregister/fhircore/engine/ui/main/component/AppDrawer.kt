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

package org.smartregister.fhircore.engine.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.smartregister.fhircore.engine.R
import org.smartregister.fhircore.engine.domain.model.SideMenuOption
import org.smartregister.fhircore.engine.ui.main.SideMenuEvent
import org.smartregister.fhircore.engine.ui.theme.AppTitleColor
import org.smartregister.fhircore.engine.ui.theme.SideMenuBottomItemDarkColor
import org.smartregister.fhircore.engine.ui.theme.SideMenuDarkColor
import org.smartregister.fhircore.engine.ui.theme.SubtitleTextColor
import org.smartregister.fhircore.engine.util.annotation.ExcludeFromJacocoGeneratedReport

const val SIDE_MENU_ICON = "sideMenuIcon"

@Composable
fun AppDrawer(
  modifier: Modifier = Modifier,
  appTitle: String,
  username: String,
  lastSynTime: String,
  sideMenuOptions: List<SideMenuOption>,
  onSideMenuClick: (SideMenuEvent) -> Unit
) {
  Column(
    verticalArrangement = Arrangement.SpaceBetween,
    modifier = modifier.fillMaxHeight().background(SideMenuDarkColor)
  ) {
    Column(modifier.background(SideMenuDarkColor).padding(16.dp)) {
      Text(
        text = appTitle,
        fontSize = 22.sp,
        color = AppTitleColor,
        modifier = modifier.padding(vertical = 16.dp)
      )
      LazyColumn {
        items(sideMenuOptions, { "${it.feature}|${it.healthModule?.name}" }) { sideMenuOption ->
          SideMenuItem(
            iconResource = sideMenuOption.iconResource,
            title = stringResource(sideMenuOption.titleResource),
            endText = sideMenuOption.count.toString(),
            showEndText = sideMenuOption.showCount,
            onSideMenuClick = {
              onSideMenuClick(SideMenuEvent.SwitchRegister(sideMenuOption.feature))
            }
          )
        }
      }
      SideMenuItem(
        iconResource = R.drawable.ic_sync,
        title = stringResource(R.string.transfer_data),
        showEndText = false,
        onSideMenuClick = { onSideMenuClick(SideMenuEvent.TransferData) }
      )
      SideMenuItem(
        iconResource = R.drawable.ic_outline_language_white,
        title = stringResource(R.string.language),
        showEndText = false,
        onSideMenuClick = { onSideMenuClick(SideMenuEvent.SwitchLanguage) }
      )
      SideMenuItem(
        iconResource = R.drawable.ic_logout_white,
        title = stringResource(R.string.logout_user, username),
        showEndText = false,
        onSideMenuClick = { onSideMenuClick(SideMenuEvent.Logout) }
      )
    }
    Box(
      modifier =
        modifier
          .background(SideMenuBottomItemDarkColor)
          .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
      SideMenuItem(
        iconResource = R.drawable.ic_sync,
        title = stringResource(R.string.sync),
        endText = stringResource(R.string.last_sync_timestamp, lastSynTime),
        showEndText = true,
        endTextColor = SubtitleTextColor,
        onSideMenuClick = { onSideMenuClick(SideMenuEvent.SyncData) }
      )
    }
  }
}

@Composable
fun SideMenuItem(
  modifier: Modifier = Modifier,
  iconResource: Int,
  title: String,
  endText: String = "",
  endTextColor: Color = Color.White,
  showEndText: Boolean,
  onSideMenuClick: () -> Unit
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = modifier.fillMaxWidth().clickable { onSideMenuClick() },
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(modifier = modifier.padding(vertical = 16.dp)) {
      Image(
        modifier = modifier.padding(end = 10.dp),
        painter = painterResource(id = iconResource),
        contentDescription = SIDE_MENU_ICON
      )
      SideMenuItemText(title = title, textColor = Color.White)
    }

    if (showEndText) {
      SideMenuItemText(title = endText, textColor = endTextColor)
    }
  }
}

@Composable
fun SideMenuItemText(title: String, textColor: Color) {
  Text(text = title, color = textColor, fontSize = 18.sp)
}

@Preview(showBackground = true)
@ExcludeFromJacocoGeneratedReport
@Composable
fun AppDrawerPreview() {
  AppDrawer(
    appTitle = "MOH VTS",
    username = "Demo",
    lastSynTime = "05:30 PM, Mar 3",
    sideMenuOptions =
      listOf(
        SideMenuOption(
          feature = "AllFamilies",
          iconResource = R.drawable.ic_user,
          titleResource = R.string.clients,
          count = 4,
          showCount = true,
        ),
        SideMenuOption(
          feature = "ChildClients",
          iconResource = R.drawable.ic_user,
          titleResource = R.string.clients,
          count = 16,
          showCount = true
        ),
        SideMenuOption(
          feature = "Reports",
          iconResource = R.drawable.ic_reports,
          titleResource = R.string.clients,
          showCount = false
        )
      ),
    onSideMenuClick = {}
  )
}
