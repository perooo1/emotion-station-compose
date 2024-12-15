package com.plenart.emotionstationcompose.ui.authentication.signIn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.ui.components.AuthenticationLayout
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    uiState: SignInScreenUiState,
    onNavigateToSignUpScreen: () -> Unit,
    onSignInAction: () -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AuthenticationLayout(
            uiState = uiState.authenticationLayoutUiState,
            onNavigateToSignUp = onNavigateToSignUpScreen,
            onSignInAction = onSignInAction,
            onPasswordChange = onPasswordChange,
            isSignUp = false,
            onEmailChange = onEmailChange,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    val viewModel = koinViewModel<SignInViewModel>()
    val state = viewModel.state.collectAsState().value
    SignInScreen(
        uiState = state,
        onNavigateToSignUpScreen = {},
        onSignInAction = viewModel::signIn,
        onPasswordChange = viewModel::onPasswordChange,
        onEmailChange = viewModel::onEmailChange,
    )
}
