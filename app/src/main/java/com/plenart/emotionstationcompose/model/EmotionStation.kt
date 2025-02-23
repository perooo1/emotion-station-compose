package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class EmotionStation(
    @PropertyName("activityType") val activityType: ActivityType = ActivityType.UNKNOWN,
    @PropertyName("stationName") val stationName: String = "",
    @PropertyName("questions") val questions: List<Question> = emptyList(),
)
