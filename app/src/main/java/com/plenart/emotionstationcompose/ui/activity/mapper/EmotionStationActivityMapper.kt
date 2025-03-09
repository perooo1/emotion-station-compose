package com.plenart.emotionstationcompose.ui.activity.mapper

import com.plenart.emotionstationcompose.ui.activity.EmotionStationActivityUiState

interface EmotionStationActivityMapper{
    fun toActivity(childId: String): EmotionStationActivityUiState
}
