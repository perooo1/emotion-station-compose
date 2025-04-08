package com.plenart.emotionstationcompose.ui.activity.mapper

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
            options = question.options,
            text = question.text,
            storyText = question.storyText ?: ""
        )
    }
}
