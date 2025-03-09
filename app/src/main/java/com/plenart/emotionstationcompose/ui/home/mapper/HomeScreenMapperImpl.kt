package com.plenart.emotionstationcompose.ui.home.mapper

import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.home.HomeScreenUiState
import com.plenart.emotionstationcompose.ui.home.components.ChildDropdownUiState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeScreen(
        isCurrentUserSpecialist: Boolean,
        children: List<Child>
    ): HomeScreenUiState {
        return HomeScreenUiState(
            isCurrentUserSpecialist = isCurrentUserSpecialist,
            childDropdownUiState = ChildDropdownUiState(options = children.map { child -> child.fullName })
        )
    }

    override fun toggleChildDropdown(currentState: HomeScreenUiState): HomeScreenUiState {
        return currentState.copy(childDropdownUiState = currentState.childDropdownUiState.copy(isExpanded = !currentState.childDropdownUiState.isExpanded))
    }

}
