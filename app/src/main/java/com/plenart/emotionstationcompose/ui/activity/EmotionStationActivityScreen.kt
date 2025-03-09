package com.plenart.emotionstationcompose.ui.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmotionStationActivityScreen(
    selectedChildId: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier.fillMaxSize()) {
        Text(text = "Selected child id is $selectedChildId")
    }
}


@Preview(showBackground = true)
@Composable
fun EmotionStationActivityScreenPreview(modifier: Modifier = Modifier) {
    EmotionStationActivityScreen(selectedChildId = "lalala")
}
