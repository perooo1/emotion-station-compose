package com.plenart.emotionstationcompose.ui.home

import com.plenart.emotionstationcompose.ui.home.components.ChildDropdownUiState

data class HomeScreenUiState(
    val childDropdownUiState: ChildDropdownUiState,
    val isCurrentUserSpecialist: Boolean = false,
)
