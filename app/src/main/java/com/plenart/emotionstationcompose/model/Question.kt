package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    @PropertyName("text") val text: String = "",
    @PropertyName("storyText") val storyText: String? = null,
    @PropertyName("imageAssetPath") val imageAssetPath: String? = null,
    @PropertyName("activityType") val activityType: ActivityType = ActivityType.UNKNOWN,
    @PropertyName("opservationCategory") val observationCategory: ObservationCategory = ObservationCategory.UNKNOWN,
    @PropertyName("options") val options: List<Option> = emptyList(),
)
