package com.plenart.emotionstationcompose.ui.childDetails.mapper

import com.plenart.emotionstationcompose.model.ActivityRecord
import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.childDetails.ChildDetailsTabState
import com.plenart.emotionstationcompose.ui.childDetails.ChildDetailsUiState

class ChildDetailsMapperImpl : ChildDetailsMapper {
    override fun toChildDetailsUiState(
        currentState: ChildDetailsUiState,
        child: Child,
        recordedActivities: List<ActivityRecord>
    ): ChildDetailsUiState {
        return currentState.copy(
            child = child,
            recordedActivities = recordedActivities,
            tabState = currentState.tabState.copy(
                selectedPrimaryTabIndex = currentState.tabState.selectedPrimaryTabIndex,
                selectedSecondaryTabIndex = currentState.tabState.selectedSecondaryTabIndex,
            ),
        )
    }

    /*
    override fun toChildDetailsUiState(child: Child): ChildDetailsUiState {
        return ChildDetailsUiState(child = child, tabState = ChildDetailsTabState())
    }
*/
    override fun onPrimaryTabSelected(
        currentState: ChildDetailsUiState,
        selectedIndex: Int
    ): ChildDetailsUiState {
        return currentState.copy(
            tabState = currentState.tabState.copy(
                selectedPrimaryTabIndex = selectedIndex,
                selectedSecondaryTabIndex = 0,
            ),
        )
    }

    override fun onSecondaryTabSelected(
        currentState: ChildDetailsUiState,
        selectedIndex: Int
    ): ChildDetailsUiState {
        return currentState.copy(
            tabState = currentState.tabState.copy(
                selectedSecondaryTabIndex = selectedIndex,
            ),
        )
    }
}
