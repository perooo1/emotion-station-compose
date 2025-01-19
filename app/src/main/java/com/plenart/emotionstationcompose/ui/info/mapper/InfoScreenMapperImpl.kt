package com.plenart.emotionstationcompose.ui.info.mapper

import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.ui.info.InfoScreenUiState

class InfoScreenMapperImpl : InfoScreenMapper {
    override fun toInfoScreenUiState(
        currentUiState: InfoScreenUiState,
        isCurrentUserSpecialist: Boolean,
        specialist: Specialist,
    ): InfoScreenUiState = currentUiState.copy(
        additionalEducation = specialist.additionalEducation ?: "",
        businessHours = specialist.workHours ?: "",
        isCurrentUserSpecialist = isCurrentUserSpecialist,
        occupation = specialist.occupation ?: "",
        phoneNumber = specialist.professionalPhoneNum ?: "",
        specialistFullName = specialist.fullName ?: "",
        workplace = specialist.workAddress ?: "",
    )

    override fun toUnassignedSpecialistUiState(): InfoScreenUiState =
        InfoScreenUiState(occupation = "Unassigned specialist")
}
