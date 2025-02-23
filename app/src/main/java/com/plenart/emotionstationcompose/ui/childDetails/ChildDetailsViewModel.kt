package com.plenart.emotionstationcompose.ui.childDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.ui.childDetails.mapper.ChildDetailsMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChildDetailsViewModel(
    private val childId: String,
    private val mapper: ChildDetailsMapper,
    private val databaseRepository: RemoteDatabaseRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            databaseRepository.getChildFlow(childId).collect { child ->
                _uiState.value = mapper.toChildDetailsUiState(
                    _uiState.value,
                    child,
                    _uiState.value.recordedActivities,
                )
            }
        }
        viewModelScope.launch {
            databaseRepository.getRecordedActivities(childId).collect { activities ->
                _uiState.value =
                    mapper.toChildDetailsUiState(
                        _uiState.value,
                        _uiState.value.child,
                        activities,
                    )
            }
        }
    }

    private val _uiState = MutableStateFlow(ChildDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun onPrimaryTabSelected(index: Int) {
        _uiState.update { currentState ->
            mapper.onPrimaryTabSelected(currentState, index)
        }
    }

    fun onSecondaryTabSelected(index: Int) {
        _uiState.update { currentState ->
            mapper.onSecondaryTabSelected(currentState, index)
        }
    }
}
