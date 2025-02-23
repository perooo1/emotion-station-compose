package com.plenart.emotionstationcompose.navigation

const val CHILD_DETAILS_ROUTE = "child_details"
const val CHILD_ID_KEY = "child_id"
const val CHILD_DETAILS_ROUTE_WITH_PARAMS = "$CHILD_DETAILS_ROUTE/{$CHILD_ID_KEY}"

data object ChildDetailsDestination : EmotionStationDestination(CHILD_DETAILS_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(childId: String): String = "$CHILD_DETAILS_ROUTE/$childId"
}
