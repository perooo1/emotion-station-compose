package com.plenart.emotionstationcompose.ui.activity.mapper

import com.plenart.emotionstationcompose.model.Question
import com.plenart.emotionstationcompose.ui.activity.EmotionStationActivityUiState
import com.plenart.emotionstationcompose.ui.activity.QuestionUiState

interface EmotionStationActivityMapper {
    fun toActivityUiState(childId: String, questions: List<QuestionUiState>): EmotionStationActivityUiState
    fun toQuestionUiState(question: Question, imageUrl: String?): QuestionUiState
}
