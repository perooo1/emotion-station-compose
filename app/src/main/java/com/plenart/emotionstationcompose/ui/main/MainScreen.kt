package com.plenart.emotionstationcompose.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plenart.emotionstationcompose.navigation.SignInScreen
import com.plenart.emotionstationcompose.navigation.SignUpScreen
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreen
import com.plenart.emotionstationcompose.ui.authentication.signUp.SignUpScreen
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("erer") })

        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(padding)
        ) {
            NavHost(
                navController = navController,
                startDestination = SignUpScreen
            ) {
                composable<SignInScreen> {
                    SignInScreen(
                        viewModel = koinViewModel(),
                        onNavigateToSignUpScreen = { navController.navigate(SignUpScreen) },
                    )
                }
                composable<SignUpScreen> {
                    SignUpScreen(onNavigateToSignIn = {
                        navController.navigate(SignInScreen)
                    })
                }
            }
        }

    }
}


