package com.plenart.emotionstationcompose.ui.activity

import com.plenart.emotionstationcompose.model.Option

data class EmotionStationActivityUiState(
    val selectedChildId: String = "",
    val questions: List<QuestionUiState> = emptyList(),
)

data class QuestionUiState(
    val text : String = "",
    val storyText: String = "",
    val imageUrl: String? = null,
    val options: List<Option> = emptyList()
)
