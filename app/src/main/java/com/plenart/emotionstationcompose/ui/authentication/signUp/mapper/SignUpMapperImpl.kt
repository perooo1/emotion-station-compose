package com.plenart.emotionstationcompose.ui.authentication.signUp.mapper

import com.plenart.emotionstationcompose.model.AuthenticationResult
import com.plenart.emotionstationcompose.ui.authentication.signUp.SignUpScreenUiState

class SignUpMapperImpl : SignUpMapper {
    override fun toSignUpScreenUiState(
        authenticationResult: AuthenticationResult?,
        currentUiState: SignUpScreenUiState,
        email: String?,
        password: String?,
        name: String?,
        lastName: String?,
        signUpAsTherapist: Boolean
    ): SignUpScreenUiState = currentUiState.copy(
        authenticationLayoutUiState = currentUiState.authenticationLayoutUiState.copy(
            email = email ?: currentUiState.authenticationLayoutUiState.email,
            password = password ?: currentUiState.authenticationLayoutUiState.password,
            name = name ?: currentUiState.authenticationLayoutUiState.name,
            lastName = lastName ?: currentUiState.authenticationLayoutUiState.lastName,
            signUpAsSpecialist = signUpAsTherapist,
        ),
        isSignUpSuccessful = authenticationResult?.data != null,
        signUpError = authenticationResult?.errorMessage ?: currentUiState.signUpError,
    )
}
