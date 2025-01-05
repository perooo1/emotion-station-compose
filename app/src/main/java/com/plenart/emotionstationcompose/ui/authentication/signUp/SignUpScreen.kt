package com.plenart.emotionstationcompose.ui.authentication.signUp

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
fun SignUpScreen(
    uiState: SignUpScreenUiState,
    onSignUpAction: () -> Unit,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpAsTherapistChange: (Boolean) -> Unit,
    onNavigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AuthenticationLayout(
            uiState = uiState.authenticationLayoutUiState,
            onNavigateToSignIn = onNavigateToSignIn,
            onSignUpAction = onSignUpAction,
            onPasswordChange = onPasswordChange,
            onEmailChange = onEmailChange,
            isSignUp = true,
            onLastNameChange = onLastNameChange,
            onNameChange = onNameChange,
            onSignUpAsTherapistChange = onSignUpAsTherapistChange,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<SignUpViewModel>()
    val state = viewModel.state.collectAsState().value

    SignUpScreen(
        uiState = state,
        onNavigateToSignIn = {},
        onSignUpAction = viewModel::signUp,
        onPasswordChange = viewModel::onPasswordChange,
        onEmailChange = viewModel::onEmailChange,
        onNameChange = viewModel::onNameChange,
        onLastNameChange = viewModel::onLastNameChange,
        onSignUpAsTherapistChange = viewModel::onSignUpAsTherapistChange,
    )
}
