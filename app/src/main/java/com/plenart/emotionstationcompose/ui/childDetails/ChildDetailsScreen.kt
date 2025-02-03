package com.plenart.emotionstationcompose.ui.childDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.plenart.emotionstationcompose.ui.components.BackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildDetailsScreen(
    uiState: ChildDetailsUiState,
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
        Column(
            modifier = modifier.padding(paddingValues)
        ) {
            Text(uiState.child.fullName)
            Text(uiState.child.id)
            Text(uiState.child.diagnosis)
        }
    }
}
