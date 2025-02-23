package com.plenart.emotionstationcompose.ui.childDetails.mapper

import com.plenart.emotionstationcompose.model.ActivityRecord
import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.childDetails.ChildDetailsUiState

interface ChildDetailsMapper {
    fun toChildDetailsUiState(
        currentState: ChildDetailsUiState,
        child: Child,
        recordedActivities: List<ActivityRecord>,
    ): ChildDetailsUiState

    fun onPrimaryTabSelected(
        currentState: ChildDetailsUiState,
        selectedIndex: Int
    ): ChildDetailsUiState

    fun onSecondaryTabSelected(
        currentState: ChildDetailsUiState,
        selectedIndex: Int
    ): ChildDetailsUiState
}
