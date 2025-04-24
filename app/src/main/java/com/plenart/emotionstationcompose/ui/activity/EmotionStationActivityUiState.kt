package com.plenart.emotionstationcompose.ui.activity

import com.plenart.emotionstationcompose.model.ComprehensionLevel
import com.plenart.emotionstationcompose.model.ObservationCategory
import com.plenart.emotionstationcompose.model.Option

data class EmotionStationActivityUiState(
    val selectedChildId: String = "",
    val questions: List<QuestionUiState> = emptyList(),
    val activityCompleted: Boolean = false,
)

data class QuestionUiState(
    val answeredTimeMillis: Long? = null,
    val imageUrl: String? = null,
    val observationCategory: ObservationCategory = ObservationCategory.UNKNOWN,
    val options: List<Option> = emptyList(),
    val selectedComprehensionLevel: ComprehensionLevel = ComprehensionLevel.INITIAL_DEFAULT,
    val selectedOptionIndex: Int? = null,
    val startTimeMillis: Long? = null,
    val storyText: String = "",
    val text: String = "",
)
