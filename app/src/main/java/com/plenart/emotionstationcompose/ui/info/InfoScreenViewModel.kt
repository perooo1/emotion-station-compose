package com.plenart.emotionstationcompose.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.ui.info.mapper.InfoScreenMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InfoScreenViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val databaseRepository: RemoteDatabaseRepository,
    private val infoScreenMapper: InfoScreenMapper,
) : ViewModel() {
    private val currentUser = authenticationRepository.currentUser.value

    private val _uiState = MutableStateFlow(InfoScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _isSpecialist = currentUser is Specialist

    init {
        when (currentUser) {
            is Specialist -> {
                val currentUiState = _uiState.value
                _uiState.value = infoScreenMapper.toInfoScreenUiState(
                    currentUiState = currentUiState,
                    isCurrentUserSpecialist = _isSpecialist,
                    specialist = currentUser,
                )
            }

            is Parent -> {
                if (currentUser.assignedSpecialistId == null) {
                    _uiState.value = infoScreenMapper.toUnassignedSpecialistUiState()
                } else {
                    viewModelScope.launch {
                        databaseRepository.getSpecialistFlow(currentUser.assignedSpecialistId)
                            .collect { specialist ->
                                specialist?.let {
                                    _uiState.value = infoScreenMapper.toInfoScreenUiState(
                                        currentUiState = uiState.value,
                                        isCurrentUserSpecialist = _isSpecialist,
                                        specialist = it,
                                    )
                                } ?: run {
                                    _uiState.value =
                                        infoScreenMapper.toUnassignedSpecialistUiState()
                                }
                            }
                    }
                }
            }

            else -> {
                _uiState.value = infoScreenMapper.toUnassignedSpecialistUiState()
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authenticationRepository.signOut()
        }
    }
}
