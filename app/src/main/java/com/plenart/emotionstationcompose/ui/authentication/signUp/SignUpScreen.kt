package com.plenart.emotionstationcompose.ui.authentication.signUp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.ui.components.AuthenticationLayout
import com.plenart.emotionstationcompose.ui.components.AuthenticationLayoutUiState

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, onNavigateToSignIn: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AuthenticationLayout(
            uiState = AuthenticationLayoutUiState(),
            onNavigateToSignIn = onNavigateToSignIn,
            onSignUpAction = {},
            onSignInAction = {},
            onNavigateToSignUp = {},
            onPasswordChange = {},
            onLastNameChange = {},
            isSignUp = true,
            onNameChange = {},
            onEmailChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(modifier: Modifier = Modifier) {
    SignUpScreen(onNavigateToSignIn = {})
}
