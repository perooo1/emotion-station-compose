package com.plenart.emotionstationcompose.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.navigation.ESRoute
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreen
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInViewModel
import com.plenart.emotionstationcompose.ui.authentication.signUp.SignUpScreen
import com.plenart.emotionstationcompose.ui.authentication.signUp.SignUpViewModel
import com.plenart.emotionstationcompose.ui.children.ChildrenScreen
import com.plenart.emotionstationcompose.ui.home.HomeScreen
import com.plenart.emotionstationcompose.ui.info.InfoScreen
import com.plenart.emotionstationcompose.ui.info.InfoScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val showBottomNavigation by remember {
        derivedStateOf {
            when (navBackStackEntry?.destination?.route) {
                ESRoute.SignInScreen.route,
                ESRoute.SignUpScreen.route -> false

                else -> true
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomNavigation) {
                BottomNavigationBar(
                    destinations = listOf(
                        ESRoute.HomeScreen,
                        ESRoute.ChildrenScreen,
                        ESRoute.InfoScreen
                    ),
                    currentDestination = navBackStackEntry?.destination,
                    onNavigateToDestination = { dest ->
                        navController.navigate(dest.route) {
                            this.popUpTo(
                                route = dest.route,
                                popUpToBuilder = {
                                    inclusive = true
                                },
                            )
                        }
                    }
                )
            }
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(padding)
        ) {
            NavHost(
                navController = navController,
                startDestination = ESRoute.SignUpScreen.route,
            ) {
                composable(ESRoute.SignInScreen.route) {
                    val viewModel = koinViewModel<SignInViewModel>()
                    val state = viewModel.state.collectAsState()

                    LaunchedEffect(key1 = state.value.isSignInSuccessful) {
                        if (state.value.isSignInSuccessful) {
                            navController.navigate(ESRoute.HomeScreen.route)
                            viewModel.resetState()
                        }
                    }
                    SignInScreen(
                        uiState = state.value,
                        onSignInAction = viewModel::signIn,
                        onPasswordChange = viewModel::onPasswordChange,
                        onEmailChange = viewModel::onEmailChange,
                        onNavigateToSignUpScreen = { navController.navigate(ESRoute.SignUpScreen.route) },
                    )

                }
                composable(ESRoute.SignUpScreen.route)
                {
                    val authenticationRepository = koinInject<AuthenticationRepository>()
                    val viewModel = koinViewModel<SignUpViewModel>()
                    val state = viewModel.state.collectAsState()

                    LaunchedEffect(key1 = Unit) {
                        if (authenticationRepository.hasUser()) {
                            navController.navigate(ESRoute.HomeScreen.route)
                        }
                    }

                    LaunchedEffect(key1 = state.value.isSignUpSuccessful) {
                        if (state.value.isSignUpSuccessful) {
                            navController.navigate(ESRoute.HomeScreen.route)
                            viewModel.resetState()
                        }
                    }

                    SignUpScreen(
                        uiState = state.value,
                        onSignUpAction = viewModel::signUp,
                        onPasswordChange = viewModel::onPasswordChange,
                        onEmailChange = viewModel::onEmailChange,
                        onNameChange = viewModel::onNameChange,
                        onLastNameChange = viewModel::onLastNameChange,
                        onSignUpAsTherapistChange = viewModel::onSignUpAsTherapistChange,
                        onNavigateToSignIn = { navController.navigate(ESRoute.SignInScreen.route) },
                    )
                }
                composable(ESRoute.HomeScreen.route) {
                    HomeScreen()
                }
                composable(ESRoute.ChildrenScreen.route) {
                    ChildrenScreen()
                }
                composable(ESRoute.InfoScreen.route) {
                    val viewModel = koinViewModel<InfoScreenViewModel>()
                    val state = viewModel.uiState.collectAsState()
                    InfoScreen(
                        infoScreenUiState = state.value,
                        onSignOutAction = {
                            viewModel.signOut()
                            navController.navigate(ESRoute.SignInScreen.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                        onFABAction = {},
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    destinations: List<ESRoute>,
    onNavigateToDestination: (ESRoute) -> Unit,
    currentDestination: NavDestination?,
) {
    NavigationBar {
        destinations.forEach { dest ->
            val selected = currentDestination?.route == dest.route
            NavigationBarItem(
                selected = selected,
                label = {
                    Text(
                        stringResource(id = dest.labelId!!),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                icon = {
                    Icon(
                        imageVector =
                        if (selected) dest.iconSelected!! else dest.iconUnselected!!,
                        contentDescription = null
                    )
                },
                onClick = { onNavigateToDestination(dest) }
            )
        }

    }
}
