package com.plenart.emotionstationcompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.R

@Composable
fun ESTextField(
    icon: @Composable (() -> Unit)?,
    label: String,
    onValueChange: (String) -> Unit,
    value: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        label = { Text(text = label) },
        leadingIcon = icon,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(dimensionResource(R.dimen.text_field_corner_radius)),
        value = value,
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth(0.8f),
    )
}

@Preview(showBackground = true)
@Composable
fun ESTextFieldPreview() {
    ESTextField(
        icon = { Icon(Icons.Default.Person, contentDescription = "") },
        label = stringResource(R.string.authentication_name_label),
        onValueChange = { },
        value = "uiState.name",
    )
}
