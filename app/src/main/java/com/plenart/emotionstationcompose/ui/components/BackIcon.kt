package com.plenart.emotionstationcompose.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.plenart.emotionstationcompose.R

@Composable
fun BackIcon(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(onClick = { onBackClicked() }, modifier = modifier) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(
                id = R.string.back_icon
            ),
        )
    }
}
