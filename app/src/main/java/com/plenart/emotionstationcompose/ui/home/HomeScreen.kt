package com.plenart.emotionstationcompose.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.home.components.ChildDropdown
import com.plenart.emotionstationcompose.ui.home.components.EmotionStationCard
import com.plenart.emotionstationcompose.ui.theme.localSpacing
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    onDropdownAction: () -> Unit,
    onChildSelectedAction: (Child) -> Unit,
    onEmotionStationAction: (childId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Home screen") },
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(localSpacing.current.small),
            modifier = modifier.padding(paddingValues)
        ) {
            ChildDropdown(uiState.childDropdownUiState, onDropdownAction, onChildSelectedAction)
            EmotionStationCard(
                label = "Hapi",
                containerColor = Color.Cyan,
                onAction = {
                    onEmotionStationAction(uiState.childDropdownUiState.selectedChild?.id ?: "emptyy")
                },
            )
            EmotionStationCard(
                label = "Sad",
                containerColor = Color.Magenta,
                onAction = {},
            )
            EmotionStationCard(
                label = "temp",
                containerColor = Color.Yellow,
                onAction = {},
            )
            EmotionStationCard(
                label = "erhh",
                containerColor = Color.Green,
                onAction = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val state = viewModel.uiState.collectAsState().value

    HomeScreen(
        uiState = state,
        onDropdownAction = {},
        onChildSelectedAction = {},
        onEmotionStationAction = {}
    )
}
