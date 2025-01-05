package com.plenart.emotionstationcompose.ui.authentication.signIn.mapper

import com.plenart.emotionstationcompose.model.AuthenticationResult
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreenUiState

interface SignInMapper {
    fun toSignInScreenUiState(
        authenticationResult: AuthenticationResult? = null,
        currentUiState: SignInScreenUiState,
        email: String? = null,
        password: String? = null,
    ): SignInScreenUiState
}
