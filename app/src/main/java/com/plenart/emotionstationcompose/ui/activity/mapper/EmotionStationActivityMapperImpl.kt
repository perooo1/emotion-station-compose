package com.plenart.emotionstationcompose.ui.activity.mapper

import com.plenart.emotionstationcompose.model.ComprehensionLevel
import com.plenart.emotionstationcompose.model.Question
import com.plenart.emotionstationcompose.ui.activity.EmotionStationActivityUiState
import com.plenart.emotionstationcompose.ui.activity.QuestionUiState

class EmotionStationActivityMapperImpl : EmotionStationActivityMapper {
    override fun toActivityUiState(
        childId: String,
        questions: List<QuestionUiState>,
    ): EmotionStationActivityUiState {
        return EmotionStationActivityUiState(
            selectedChildId = childId,
            questions = questions,
        )
    }

    override fun toQuestionUiState(question: Question, imageUrl: String?): QuestionUiState {
        return QuestionUiState(
            imageUrl = imageUrl,
            observationCategory = question.observationCategory,
            options = question.options,
            storyText = question.storyText ?: "",
            text = question.text,
        )
    }

    override fun updateSelectedOption(
        question: QuestionUiState,
        selectedOptionIndex: Int,
        selectedOptionComprehensionLevel: ComprehensionLevel,
        answeredTimeMillis: Long,
    ): QuestionUiState {
        return QuestionUiState(
            answeredTimeMillis = answeredTimeMillis,
            imageUrl = question.imageUrl,
            options = question.options,
            selectedComprehensionLevel = selectedOptionComprehensionLevel,
            selectedOptionIndex = selectedOptionIndex,
            startTimeMillis = question.startTimeMillis,
            storyText = question.storyText,
            text = question.text,
        )
    }

    override fun updateQuestionStartTime(
        question: QuestionUiState,
        startTimeMillis: Long,
    ): QuestionUiState {
        return QuestionUiState(
            imageUrl = question.imageUrl,
            options = question.options,
            text = question.text,
            storyText = question.storyText,
            selectedOptionIndex = question.selectedOptionIndex,
            answeredTimeMillis = question.answeredTimeMillis,
            startTimeMillis = startTimeMillis
        )
    }

    override fun updateActivityCompleted(currentUiState: EmotionStationActivityUiState): EmotionStationActivityUiState {
        return currentUiState.copy(activityCompleted = true)
    }
}
