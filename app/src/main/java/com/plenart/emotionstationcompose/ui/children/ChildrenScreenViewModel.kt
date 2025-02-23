package com.plenart.emotionstationcompose.ui.children

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.ui.children.mapper.ChildrenScreenMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChildrenScreenViewModel(
    authenticationRepository: AuthenticationRepository,
    private val childrenScreenMapper: ChildrenScreenMapper,
    private val databaseRepository: RemoteDatabaseRepository,
) : ViewModel() {
    private val currentUser = authenticationRepository.currentUser.value

    private val _uiState = MutableStateFlow(ChildrenScreenUiState(emptyList()))
    val uiState = _uiState.asStateFlow()

    init {
        when (currentUser) {
            is Specialist -> {
                viewModelScope.launch {
                    databaseRepository.getChildrenFlow(
                        specialistId = "0Hut0VyRCNMyrLwW9maUoh1Rcm93",
                        parentId = null,
                    ).collect {
                        _uiState.value = childrenScreenMapper.toChildrenScreenUiState(it)
                    }
                }
            }

            is Parent -> {
                viewModelScope.launch {
                    databaseRepository.getChildrenFlow(
                        specialistId = null,
                        parentId = "gYeYK2SKXVdLAPrNKpXqttZcnQs2",
                    ).collect {
                        _uiState.value = childrenScreenMapper.toChildrenScreenUiState(it)
                    }
                }
            }
            else ->
                _uiState.value = ChildrenScreenUiState(emptyList())
        }
    }
}
