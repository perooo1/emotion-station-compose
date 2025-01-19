package com.plenart.emotionstationcompose.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class EmotionForecast {
    @SerialName("happy")
    HAPPY,

    @SerialName("sad")
    SAD,

    @SerialName("angry")
    ANGRY,
}
