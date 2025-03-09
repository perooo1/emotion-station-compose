package com.plenart.emotionstationcompose.ui.home

import com.plenart.emotionstationcompose.ui.home.components.ChildDropdownUiState

data class HomeScreenUiState(
    val isCurrentUserSpecialist: Boolean = false,
    val childDropdownUiState: ChildDropdownUiState,
)
