package com.plenart.emotionstationcompose.ui.info.mapper

import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.model.User
import com.plenart.emotionstationcompose.ui.info.InfoScreenUiState
import com.plenart.emotionstationcompose.ui.info.SpecialistInfoDetailsUiState

class InfoScreenMapperImpl : InfoScreenMapper {
    override fun toInfoScreenUiState(
        currentUiState: InfoScreenUiState,
        currentUser: User?,
        specialist: Specialist?,
        specialistInfoDetailsUiState: SpecialistInfoDetailsUiState
    ): InfoScreenUiState = currentUiState.copy(
        currentUser = currentUser,
        specialist = specialist,
        specialistInfoDetails = specialistInfoDetailsUiState,
    )
}
