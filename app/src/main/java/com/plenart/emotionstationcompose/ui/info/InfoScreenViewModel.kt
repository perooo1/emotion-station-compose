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

    private val _specialistFlow = MutableStateFlow<Specialist?>(null)

    private val _uiState = MutableStateFlow(InfoScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        //Todo refacorat logiku da bude samo jedan update ui state-a, a ne ovako u svakom ifu
        if (currentUser is Specialist) {
            _specialistFlow.value = currentUser
            _uiState.value = infoScreenMapper.toInfoScreenUiState(
                currentUiState = _uiState.value,
                specialist = currentUser
            )
        } else {
            val korisnik = currentUser as Parent
            if (korisnik.assignedSpecialistId == null) {
                _uiState.value = infoScreenMapper.toUnassignedSpecialistUiState()
            } else {
                viewModelScope.launch {
                    databaseRepository.getSpecialistFlow(korisnik.assignedSpecialistId).collect {
                        _specialistFlow.value = it
                        _uiState.value = infoScreenMapper.toInfoScreenUiState(
                            currentUiState = _uiState.value,
                            specialist = it!!
                        )
                    }
                }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authenticationRepository.signOut()
        }
    }
}
