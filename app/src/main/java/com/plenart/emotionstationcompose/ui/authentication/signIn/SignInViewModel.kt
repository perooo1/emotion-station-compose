package com.plenart.emotionstationcompose.ui.authentication.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.ui.authentication.signIn.mapper.SignInMapper
import com.plenart.emotionstationcompose.ui.components.AuthenticationLayoutUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val signInMapper: SignInMapper,
) : ViewModel() {

    private val _state = MutableStateFlow(SignInScreenUiState(AuthenticationLayoutUiState()))
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.value = signInMapper.toSignInScreenUiState(
            authenticationResult = null,
            currentUiState = _state.value,
            email = email,
        )
    }

    fun onPasswordChange(password: String) {
        _state.value = signInMapper.toSignInScreenUiState(
            authenticationResult = null,
            currentUiState = _state.value,
            password = password,
        )
    }

    fun signIn() {
        viewModelScope.launch {
            val result = authenticationRepository.signIn(
                _state.value.authenticationLayoutUiState.email,
                _state.value.authenticationLayoutUiState.password
            )
            _state.value = signInMapper.toSignInScreenUiState(
                currentUiState = _state.value,
                authenticationResult = result,
            )
        }
    }

    //Temporary
    fun signOut() {
        viewModelScope.launch {
            authenticationRepository.signOut()
        }
    }

    fun resetState() {
        _state.value = SignInScreenUiState(AuthenticationLayoutUiState())
    }
}
