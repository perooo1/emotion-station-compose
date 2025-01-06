package com.plenart.emotionstationcompose.ui.info.mapper

import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.model.User
import com.plenart.emotionstationcompose.ui.info.InfoScreenUiState
import com.plenart.emotionstationcompose.ui.info.SpecialistInfoDetailsUiState

interface InfoScreenMapper {
    fun toInfoScreenUiState(
        currentUiState: InfoScreenUiState,
        currentUser: User? = null,
        specialist: Specialist? = null,
        specialistInfoDetailsUiState: SpecialistInfoDetailsUiState,
    ): InfoScreenUiState
}
