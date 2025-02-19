package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class Option(
    @PropertyName("text") val text: String = "",
    @PropertyName("comprehensionLevel") val comprehensionLevel: ComprehensionLevel = ComprehensionLevel.INITIAL_DEFAULT,
)
