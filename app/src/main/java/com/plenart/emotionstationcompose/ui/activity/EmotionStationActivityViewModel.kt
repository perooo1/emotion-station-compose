package com.plenart.emotionstationcompose.ui.activity

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EmotionStationActivityViewModel(selectedChildId: String) : ViewModel() {

    private val _uiState = MutableStateFlow(EmotionStationActivityUiState(selectedChildId))
    val uiState = _uiState.asStateFlow()


}
