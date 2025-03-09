package com.plenart.emotionstationcompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.ui.home.components.ChildDropdownUiState
import com.plenart.emotionstationcompose.ui.home.mapper.HomeScreenMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    authenticationRepository: AuthenticationRepository,
    private val homeScreenMapper: HomeScreenMapper,
    private val databaseRepository: RemoteDatabaseRepository,
) : ViewModel() {

    private val _currentUser = authenticationRepository.currentUser.value

    private val _uiState =
        MutableStateFlow(HomeScreenUiState(childDropdownUiState = ChildDropdownUiState()))
    val uiState = _uiState.asStateFlow()

    init {
        when (_currentUser) {
            is Parent -> {
                viewModelScope.launch {
                    databaseRepository.getChildrenFlow(
                        specialistId = null,
                        parentId = _currentUser.id,
                    ).collect { children ->
                        _uiState.update {
                            homeScreenMapper.toHomeScreen(
                                isCurrentUserSpecialist = _uiState.value.isCurrentUserSpecialist,
                                children = children,
                            )
                        }
                    }
                }
            }

            else ->
                _uiState.update {
                    homeScreenMapper.toHomeScreen(
                        isCurrentUserSpecialist = _uiState.value.isCurrentUserSpecialist,
                        children = emptyList(),
                    )
                }
        }
    }

    fun onDropdownAction(homeScreenUiState: HomeScreenUiState) {
        _uiState.update {
            homeScreenMapper.toggleChildDropdown(homeScreenUiState);
        }
    }

    fun onChildSelectedAction(child: Child) {
       _uiState.update {
           homeScreenMapper.selectChild(_uiState.value, child)
       }
    }
}
