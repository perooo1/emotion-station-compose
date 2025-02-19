package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
enum class ObservationCategory {
    @PropertyName("emotionReckognition")
    EMOTION_RECOGNITION,

    @PropertyName("emotionUnderstandingVisual")
    EMOTION_UNDERSTANDING_VISUAL,

    @PropertyName("emotionUnderstandingTextual")
    EMOTION_UNDERSTANDING_TEXTUAL,

    UNKNOWN,
}
