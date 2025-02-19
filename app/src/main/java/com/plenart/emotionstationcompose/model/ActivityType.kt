package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
enum class ActivityType {
    @PropertyName("stationOfHappiness")
    STATION_OF_HAPPINESS,

    @PropertyName("stationOfSadness")
    STATION_OF_SADNESS,

    @PropertyName("stationOfAnger")
    STATION_OF_ANGER,

    @PropertyName("stationOfFear")
    STATION_OF_FEAR,

    UNKNOWN,
}
