package com.plenart.emotionstationcompose.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.plenart.emotionstationcompose.ui.theme.localSpacing

@Composable
fun EmotionStationCard(
    containerColor: Color,
    label: String,
    onAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: Dp = localSpacing.current.medium,
) {
    Card(
        colors = CardDefaults.cardColors().copy(containerColor = containerColor),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = localSpacing.current.medium)
            .clickable { onAction() }
    ) {
        Text(label, modifier = Modifier.padding(contentPadding))
    }
}
