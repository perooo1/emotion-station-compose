package com.plenart.emotionstationcompose.ui.home.mapper

import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.home.HomeScreenUiState

interface HomeScreenMapper {
    fun toHomeScreen(
        isCurrentUserSpecialist: Boolean,
        children: List<Child>
    ): HomeScreenUiState

    fun toggleChildDropdown(currentState: HomeScreenUiState): HomeScreenUiState
    fun selectChild(currentState: HomeScreenUiState, child: Child): HomeScreenUiState
}
