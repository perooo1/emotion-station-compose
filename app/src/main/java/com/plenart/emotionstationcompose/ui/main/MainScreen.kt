package com.plenart.emotionstationcompose.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plenart.emotionstationcompose.navigation.HomeScreen
import com.plenart.emotionstationcompose.navigation.SignInScreen
import com.plenart.emotionstationcompose.navigation.SignUpScreen
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreen
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInViewModel
import com.plenart.emotionstationcompose.ui.authentication.signUp.SignUpScreen
import com.plenart.emotionstationcompose.ui.home.HomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold { padding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(padding)
        ) {
            NavHost(
                navController = navController,
                startDestination = SignUpScreen
            ) {
                composable<SignInScreen> {
                    val viewModel = koinViewModel<SignInViewModel>()
                    val state = viewModel.state.collectAsState()

                    LaunchedEffect(key1 = state.value.isSignInSuccessful) {
                        if (state.value.isSignInSuccessful) {
                            navController.navigate(HomeScreen)
                            viewModel.resetState()
                        }
                    }
                    SignInScreen(
                        uiState = state.value,
                        onSignInAction = viewModel::signIn,
                        onPasswordChange = viewModel::onPasswordChange,
                        onEmailChange = viewModel::onEmailChange,
                        onNavigateToSignUpScreen = { navController.navigate(SignUpScreen) },
                    )
                }
                composable<SignUpScreen> {
                    SignUpScreen(
                        onNavigateToSignIn = {
                            navController.navigate(SignInScreen)
                        },
                    )
                }
                composable<HomeScreen> {
                    val viewmodel = koinViewModel<SignInViewModel>()    //Temporary

                    HomeScreen(
                        onSignOut = {
                            viewmodel.signOut()
                            navController.popBackStack()
                        },
                    )
                }
            }
        }
    }
}


