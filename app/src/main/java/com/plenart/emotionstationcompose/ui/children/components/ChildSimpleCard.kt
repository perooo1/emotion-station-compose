package com.plenart.emotionstationcompose.ui.children.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

data class ChildSimpleCardUiState(
    val age: Int,
    val diagnosis: String,
    val fullName: String,
    val itemId: String,
)

@Composable
fun ChildSimpleCard(
    onCardAction: (String) -> Unit,
    uiState: ChildSimpleCardUiState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable {
            onCardAction(uiState.itemId)
        },
    ) {
        ListItem(
            headlineContent = { Text(uiState.fullName) },
            supportingContent = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = uiState.age.toString())
                    Text(text = uiState.diagnosis)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChildSimpleCardPreview(modifier: Modifier = Modifier) {
    val uiState = ChildSimpleCardUiState(
        age = 10,
        diagnosis = "Diagnosis",
        fullName = "Full Name",
        itemId = "Item Id",
    )

    ChildSimpleCard(onCardAction = {}, uiState = uiState)
}
