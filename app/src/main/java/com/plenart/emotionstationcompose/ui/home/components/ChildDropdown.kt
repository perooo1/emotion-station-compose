package com.plenart.emotionstationcompose.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class ChildDropdownUiState(
    val isExpanded: Boolean = false,
    val selectedChild: String = "",
    val options: List<String> = emptyList()
)

@Composable
fun ChildDropdown(
    uiState: ChildDropdownUiState,
    onDropdownAction: () -> Unit,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
/*
        OutlinedTextField(
            value = uiState.selectedChild,
            onValueChange = {},
            readOnly = true,
            label = { Text("text") },
            trailingIcon = {
                Icon(
                    imageVector = if (uiState.isExpanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Toggle Dropdown"
                )
            },
            modifier = Modifier.clickable { onDropdownAction() }
        )
        */
        IconButton(onClick = onDropdownAction) {
            Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Toggle Dropdown")
        }

        DropdownMenu(
            expanded = uiState.isExpanded,
            onDismissRequest = { onDropdownAction() }
        ) {
            uiState.options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        onDropdownAction()
                    }
                )
            }
        }
    }
}
