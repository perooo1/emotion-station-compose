package com.plenart.emotionstationcompose.ui.authentication.signUp

import com.plenart.emotionstationcompose.ui.components.AuthenticationLayoutUiState

data class SignUpScreenUiState(
    val authenticationLayoutUiState: AuthenticationLayoutUiState,
    val isSignUpSuccessful: Boolean = false,
    val signUpError: String? = null,
)
