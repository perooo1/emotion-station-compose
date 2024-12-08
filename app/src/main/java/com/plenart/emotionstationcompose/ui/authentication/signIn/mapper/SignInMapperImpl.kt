package com.plenart.emotionstationcompose.ui.authentication.signIn.mapper

import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreenUiState
import com.plenart.emotionstationcompose.ui.components.AuthenticationLayoutUiState

class SignInMapperImpl : SignInMapper {
    override fun toSignInScreenUiState(email: String, password: String): SignInScreenUiState =
        SignInScreenUiState(
            authenticationLayoutUiState =
            AuthenticationLayoutUiState().copy(
                email = email,
                password = password,
            )
        )
}
