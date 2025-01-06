package com.plenart.emotionstationcompose.ui.info

import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.model.User

data class SpecialistInfoDetailsUiState(
    val additionalEducation: String,
    val businessHours: String,
    val occupation: String,
    val phoneNumber: String,
    val specialistFullName: String,
    val workplace: String,
)


data class InfoScreenUiState(
    val currentUser: User? = null,
    val specialist: Specialist? = null,
    val specialistInfoDetails: SpecialistInfoDetailsUiState,
)
