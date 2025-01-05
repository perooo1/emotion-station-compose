package com.plenart.emotionstationcompose.ui.authentication.signIn.mapper

import com.plenart.emotionstationcompose.model.AuthenticationResult
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreenUiState

class SignInMapperImpl : SignInMapper {
    override fun toSignInScreenUiState(
        authenticationResult: AuthenticationResult?,
        currentUiState: SignInScreenUiState,
        email: String? ,
        password: String?,
    ): SignInScreenUiState = currentUiState.copy(
        authenticationLayoutUiState = currentUiState.authenticationLayoutUiState.copy(
            email = email ?: currentUiState.authenticationLayoutUiState.email,
            password = password ?: currentUiState.authenticationLayoutUiState.password,
        ),
        isSignInSuccessful = authenticationResult?.data != null  ,
        signInError = authenticationResult?.errorMessage ?: currentUiState.signInError,
    )
}
