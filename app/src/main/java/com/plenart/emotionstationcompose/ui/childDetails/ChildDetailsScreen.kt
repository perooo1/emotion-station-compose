package com.plenart.emotionstationcompose.ui.childDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.R
import com.plenart.emotionstationcompose.ui.childDetails.components.ActivitiesOverviewSecondaryTab
import com.plenart.emotionstationcompose.ui.childDetails.components.ChildDetailsTab
import com.plenart.emotionstationcompose.ui.childDetails.components.CompletedActivitiesSecondaryTab
import com.plenart.emotionstationcompose.ui.components.BackIcon
import org.koin.androidx.compose.koinViewModel

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
        floatingActionButton = {
            if(uiState.tabState.selectedPrimaryTabIndex == 0) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Default.Edit, contentDescription = "") },
                    onClick = { },
                    text = { Text(stringResource(R.string.info_screen_fab_text)) },
                )
            }
        }
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
            when (uiState.tabState.selectedPrimaryTabIndex) {
                0 -> ChildDetailsTab(child = uiState.child)
                else -> when (uiState.tabState.selectedSecondaryTabIndex) {
                    0 -> ActivitiesOverviewSecondaryTab()
                    else -> CompletedActivitiesSecondaryTab(
                        completedActivities = uiState.recordedActivities,
                        onCompletedActivityAction = {

                        }
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChildDetailsScreenPreview() {
    val viewModel = koinViewModel<ChildDetailsViewModel>()
    val state = viewModel.uiState.collectAsState().value

    ChildDetailsScreen(
        uiState = state,
        onNavigateBack = {},
        onPrimaryTabSelected = {},
        onSecondaryTabSelected = {},
    )
}
