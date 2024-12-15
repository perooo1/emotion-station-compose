package com.plenart.emotionstationcompose.ui.authentication.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.ui.authentication.signIn.mapper.SignInMapper
import com.plenart.emotionstationcompose.ui.components.AuthenticationLayoutUiState
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val signInMapper: SignInMapper,
) : ViewModel() {

    var uiState by mutableStateOf(SignInScreenUiState(AuthenticationLayoutUiState()))
        private set

    fun onEmailChange(email: String) {
        uiState = signInMapper.toSignInScreenUiState(
            authenticationResult = null,
            currentUiState = uiState,
            email = email,
        )
    }

    fun onPasswordChange(password: String) {
        uiState = signInMapper.toSignInScreenUiState(
            authenticationResult = null,
            currentUiState = uiState,
            password = password,
        )
    }

    fun signIn() {
        viewModelScope.launch {
            val result = authenticationRepository.signIn(
                uiState.authenticationLayoutUiState.email,
                uiState.authenticationLayoutUiState.password
            )
            uiState = signInMapper.toSignInScreenUiState(
                currentUiState = uiState,
                authenticationResult = result,
            )
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authenticationRepository.signOut()
        }
    }

    fun resetState() {
        uiState = SignInScreenUiState(AuthenticationLayoutUiState())
    }
}
