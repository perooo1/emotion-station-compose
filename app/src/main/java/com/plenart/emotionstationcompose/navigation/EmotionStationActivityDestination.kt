package com.plenart.emotionstationcompose.navigation


const val EMOTION_STATION_ACTIVITY_ROUTE = "emotion_station_activity"
const val CHILD_ID = "child_id"
const val EMOTION_STATION_ACTIVITY_ROUTE_WITH_PARAMS = "$EMOTION_STATION_ACTIVITY_ROUTE/{$CHILD_ID}"

data object EmotionStationActivityDestination : EmotionStationDestination(EMOTION_STATION_ACTIVITY_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(childId: String): String = "$EMOTION_STATION_ACTIVITY_ROUTE/$childId"
}
