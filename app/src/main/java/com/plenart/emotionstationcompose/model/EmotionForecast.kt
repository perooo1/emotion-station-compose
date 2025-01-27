package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
enum class EmotionForecast {
    @PropertyName("happy")
    HAPPY,

    @PropertyName("sad")
    SAD,

    @PropertyName("angry")
    ANGRY,
}
