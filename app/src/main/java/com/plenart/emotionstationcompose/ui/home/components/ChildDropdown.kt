package com.plenart.emotionstationcompose.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.plenart.emotionstationcompose.model.Child

data class ChildDropdownUiState(
    val children: List<Child> = emptyList(),
    val isExpanded: Boolean = false,
    val selectedChild: Child? = null,
)

@Composable
fun ChildDropdown(
    uiState: ChildDropdownUiState,
    onDropdownAction: () -> Unit,
    onOptionSelected: (Child) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = Color.LightGray)
                .border(3.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .clickable { onDropdownAction() }
        ) {
            Text(uiState.selectedChild?.name ?: "Select a Child")
            Icon(
                if (uiState.isExpanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
                contentDescription = "Toggle Dropdown"
            )
        }

        DropdownMenu(
            expanded = uiState.isExpanded,
            onDismissRequest = { onDropdownAction() }
        ) {
            uiState.children.forEach { child ->
                DropdownMenuItem(
                    text = { Text(child.name) },
                    onClick = {
                        onOptionSelected(child)
                    }
                )
            }
        }
    }
}
