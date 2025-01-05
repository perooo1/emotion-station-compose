package com.plenart.emotionstationcompose.ui.children

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChildrenScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,

            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Text(text = "This will be children list")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ChildrenScreenPreview() {
    ChildrenScreen()
}
