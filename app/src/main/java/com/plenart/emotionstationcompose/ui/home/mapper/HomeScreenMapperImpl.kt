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
            childDropdownUiState = ChildDropdownUiState(
                children = children,
                selectedChild = children.firstOrNull(),
            )
        )
    }

    override fun toggleChildDropdown(currentState: HomeScreenUiState): HomeScreenUiState {
        return currentState.copy(
            childDropdownUiState = currentState.childDropdownUiState.copy(
                isExpanded = !currentState.childDropdownUiState.isExpanded
            )
        )
    }

    override fun selectChild(currentState: HomeScreenUiState, child: Child): HomeScreenUiState {
        return currentState.copy(
            childDropdownUiState = currentState.childDropdownUiState.copy(
                selectedChild = child,
                isExpanded = false,
            )
        )
    }
}
