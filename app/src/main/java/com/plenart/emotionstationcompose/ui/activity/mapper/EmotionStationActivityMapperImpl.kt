package com.plenart.emotionstationcompose.ui.activity.mapper

import com.plenart.emotionstationcompose.ui.activity.EmotionStationActivityUiState

class EmotionStationActivityMapperImpl : EmotionStationActivityMapper{
    override fun toActivity(childId: String): EmotionStationActivityUiState {
        return EmotionStationActivityUiState(
            selectedChildId = childId,
        )
    }
}
