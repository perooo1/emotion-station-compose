package com.plenart.emotionstationcompose.ui.info.mapper

import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.ui.info.InfoScreenUiState

interface InfoScreenMapper {
    fun toInfoScreenUiState(
        currentUiState: InfoScreenUiState,
        isCurrentUserSpecialist : Boolean,
        specialist: Specialist,
    ): InfoScreenUiState

    fun toUnassignedSpecialistUiState(): InfoScreenUiState
}
