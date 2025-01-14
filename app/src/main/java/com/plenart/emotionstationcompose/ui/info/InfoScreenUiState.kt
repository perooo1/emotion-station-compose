package com.plenart.emotionstationcompose.ui.info

data class InfoScreenUiState(
    val additionalEducation: String = "",
    val businessHours: String = "",
    val isCurrentUserSpecialist: Boolean = false,
    val occupation: String = "",
    val phoneNumber: String = "",
    val specialistFullName: String = "",
    val workplace: String = "",
)
