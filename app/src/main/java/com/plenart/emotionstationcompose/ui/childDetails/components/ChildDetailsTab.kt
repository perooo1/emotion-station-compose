package com.plenart.emotionstationcompose.ui.childDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.theme.localSpacing

@Composable
fun ChildDetailsTab(
    child: Child,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(localSpacing.current.medium)
    ) {
        ChildInfoCard(child = child)
    }
}
