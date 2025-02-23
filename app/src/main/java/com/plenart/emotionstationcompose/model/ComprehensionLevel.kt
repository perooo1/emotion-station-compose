package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
enum class ComprehensionLevel {
    @PropertyName("high")
    HIGH,

    @PropertyName("partial")
    PARTIAL,

    @PropertyName("low")
    LOW,

    @PropertyName("initialDefault")
    INITIAL_DEFAULT
}
