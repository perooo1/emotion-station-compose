package com.plenart.emotionstationcompose.ui.activity.mapper

import com.plenart.emotionstationcompose.model.ComprehensionLevel
import com.plenart.emotionstationcompose.model.Question
import com.plenart.emotionstationcompose.ui.activity.EmotionStationActivityUiState
import com.plenart.emotionstationcompose.ui.activity.QuestionUiState

interface EmotionStationActivityMapper {
    fun toActivityUiState(
        childId: String,
        questions: List<QuestionUiState>,
    ): EmotionStationActivityUiState

    fun toQuestionUiState(question: Question, imageUrl: String?): QuestionUiState

    fun updateSelectedOption(
        question: QuestionUiState,
        selectedOptionIndex: Int,
        selectedOptionComprehensionLevel: ComprehensionLevel,
        answeredTimeMillis: Long,
    ): QuestionUiState

    fun updateQuestionStartTime(question: QuestionUiState, startTimeMillis: Long): QuestionUiState

    fun updateActivityCompleted(currentUiState: EmotionStationActivityUiState): EmotionStationActivityUiState
}
