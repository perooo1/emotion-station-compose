package com.plenart.emotionstationcompose.ui.children

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.ui.children.components.ChildSimpleCard
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildrenScreen(
    uiState: ChildrenScreenUiState,
    onChildAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Children screen") },
            )
        },
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = modifier,
        ) {

            items(
                items = uiState.children,
                key = {
                    it.itemId
                }
            ) {
                ChildSimpleCard(
                    onCardAction = onChildAction,
                    uiState = it,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChildrenScreenPreview() {
    val viewModel = koinViewModel<ChildrenScreenViewModel>()
    val state = viewModel.uiState.collectAsState().value

    ChildrenScreen(
        uiState = state,
        onChildAction = {},
    )
}
