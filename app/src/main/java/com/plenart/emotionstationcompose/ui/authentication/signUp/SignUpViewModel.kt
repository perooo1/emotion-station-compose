package com.plenart.emotionstationcompose.ui.authentication.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreenUiState
import com.plenart.emotionstationcompose.ui.authentication.signUp.mapper.SignUpMapper
import com.plenart.emotionstationcompose.ui.components.AuthenticationLayoutUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val signUpMapper: SignUpMapper,
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpScreenUiState(AuthenticationLayoutUiState()))
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.value = signUpMapper.toSignUpScreenUiState(
            authenticationResult = null,
            currentUiState = _state.value,
            email = email,
        )
    }

    fun onNameChange(name: String) {
        _state.value = signUpMapper.toSignUpScreenUiState(
            authenticationResult = null,
            currentUiState = _state.value,
            name = name,
        )
    }

    fun onLastNameChange(lastName: String) {
        _state.value = signUpMapper.toSignUpScreenUiState(
            authenticationResult = null,
            currentUiState = _state.value,
            lastName = lastName,
        )
    }

    fun onSignUpAsTherapistChange(signUpAsTherapist: Boolean) {
        _state.value = signUpMapper.toSignUpScreenUiState(
            authenticationResult = null,
            currentUiState = _state.value,
            signUpAsTherapist = signUpAsTherapist,
        )
    }

    fun onPasswordChange(password: String) {
        _state.value = signUpMapper.toSignUpScreenUiState(
            authenticationResult = null,
            currentUiState = _state.value,
            password = password,
        )
    }

    fun signUp() {
        viewModelScope.launch {
            val result = authenticationRepository.signUp(
                email = _state.value.authenticationLayoutUiState.email,
                password = _state.value.authenticationLayoutUiState.password,
                name = _state.value.authenticationLayoutUiState.name,
                lastName = _state.value.authenticationLayoutUiState.lastName,
                signUpAsSpecialist = _state.value.authenticationLayoutUiState.signUpAsSpecialist,
            )

            _state.value = signUpMapper.toSignUpScreenUiState(
                currentUiState = _state.value,
                authenticationResult = result,
            )
        }
    }

    fun resetState() {
        _state.value = SignUpScreenUiState(AuthenticationLayoutUiState())
    }
}
