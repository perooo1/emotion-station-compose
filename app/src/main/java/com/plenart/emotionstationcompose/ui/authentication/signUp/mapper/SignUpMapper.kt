package com.plenart.emotionstationcompose.ui.authentication.signUp.mapper

import com.plenart.emotionstationcompose.model.AuthenticationResult
import com.plenart.emotionstationcompose.ui.authentication.signUp.SignUpScreenUiState

interface SignUpMapper {
    fun toSignUpScreenUiState(
        authenticationResult: AuthenticationResult? = null,
        currentUiState: SignUpScreenUiState,
        email: String? = null,
        password: String? = null,
        name: String? = null,
        lastName: String? = null,
        signUpAsTherapist: Boolean = false,
    ): SignUpScreenUiState
}
