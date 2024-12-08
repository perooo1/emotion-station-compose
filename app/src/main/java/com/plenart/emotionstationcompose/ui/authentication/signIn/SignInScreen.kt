package com.plenart.emotionstationcompose.ui.authentication.signIn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.ui.components.AuthenticationLayout

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    onNavigateToSignUpScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AuthenticationLayout(
            uiState = viewModel.uiState,
            onNavigateToSignUp = onNavigateToSignUpScreen,
            onSignUpAction = {},
            onSignInAction = { viewModel.signIn() },
            onNavigateToSignIn = {},
            onPasswordChange = {
                viewModel.onPasswordChange(it)
            },
            onLastNameChange = {},
            isSignUp = false,
            onNameChange = {},
            onEmailChange = {
                viewModel.onEmailChange(it)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
/*
    SignInScreen(
        onNavigateToSignUpScreen = {},
        onSignInAction = {},
        email = "",
        onPasswordChange = {},
        password = "",
        onEmailChange = {},
    )
    */
    //SignInScreen(onNavigateToSignUpScreen = {})
}
