package com.plenart.emotionstationcompose.ui.childDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.plenart.emotionstationcompose.ui.components.BackIcon

//Todo add preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildDetailsScreen(
    uiState: ChildDetailsUiState,
    onPrimaryTabSelected: (Int) -> Unit,
    onSecondaryTabSelected: (Int) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = { BackIcon(onBackClicked = onNavigateBack) },
                title = {
                    Text("Details")
                },
            )
        },
    ) { paddingValues ->

        Column(modifier = modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = uiState.tabState.selectedPrimaryTabIndex) {

                uiState.tabState.primaryTabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = uiState.tabState.selectedPrimaryTabIndex == index,
                        onClick = { onPrimaryTabSelected(index) },
                        icon = { Icon(tab.icon ?: Icons.Default.Home, contentDescription = null) },
                        text = { Text(stringResource(tab.labelRes)) }
                    )
                }
            }

            if (uiState.tabState.selectedPrimaryTabIndex == 1) {
                TabRow(selectedTabIndex = uiState.tabState.selectedSecondaryTabIndex) {
                    uiState.tabState.secondaryTabs.forEachIndexed { index, tab ->
                        Tab(
                            selected = uiState.tabState.selectedSecondaryTabIndex == index,
                            onClick = { onSecondaryTabSelected(index) },
                            text = { Text(stringResource(tab.labelRes)) },
                            )
                    }
                }
            }

            Text(
                text = "Selected: primary index ${uiState.tabState.selectedPrimaryTabIndex}, secondary index ${uiState.tabState.selectedSecondaryTabIndex}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
