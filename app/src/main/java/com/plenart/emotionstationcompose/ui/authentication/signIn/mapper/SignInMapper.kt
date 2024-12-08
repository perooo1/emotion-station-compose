package com.plenart.emotionstationcompose.ui.authentication.signIn.mapper

import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreenUiState

interface SignInMapper{
    fun toSignInScreenUiState(email: String = "", password: String = "") : SignInScreenUiState
}
