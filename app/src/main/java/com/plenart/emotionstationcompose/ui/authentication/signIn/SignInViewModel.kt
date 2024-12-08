package com.plenart.emotionstationcompose.ui.authentication.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.ui.authentication.signIn.mapper.SignInMapper
import com.plenart.emotionstationcompose.ui.components.AuthenticationLayoutUiState

class SignInViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val signInMapper: SignInMapper,
) : ViewModel() {
    var uiState by mutableStateOf(AuthenticationLayoutUiState())
        private set

    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun signIn() {
        val a = uiState.copy()

        val b = 3;
    }

}
