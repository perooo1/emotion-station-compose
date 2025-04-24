package com.plenart.emotionstationcompose.ui.activity.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.model.Option

@Composable
fun AnswerOption(
    option: Option,
    selected: Boolean,
    onSelectAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilterChip(
        label = { Text(option.text) },
        modifier = modifier.fillMaxWidth(),
        onClick = onSelectAction,
        selected = selected,
    )
}


@Preview(showBackground = true)
@Composable
private fun AnswerOptionPreview() {
    //AnswerOption()
}
