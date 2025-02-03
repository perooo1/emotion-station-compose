package com.plenart.emotionstationcompose.ui.childDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.ui.childDetails.mapper.ChildDetailsMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChildDetailsViewModel(
    private val childId: String,
    private val mapper: ChildDetailsMapper,
    private val databaseRepository: RemoteDatabaseRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChildDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            databaseRepository.getChildFlow(childId).collect { child ->
                _uiState.value = mapper.toChildDetailsUiState(child)
            }
        }
    }

}
