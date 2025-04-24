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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.navigation.CHILD_ID
import com.plenart.emotionstationcompose.navigation.CHILD_ID_KEY
import com.plenart.emotionstationcompose.navigation.ChildDetailsDestination
import com.plenart.emotionstationcompose.navigation.ESRoute
import com.plenart.emotionstationcompose.navigation.EmotionStationActivityDestination
import com.plenart.emotionstationcompose.ui.activity.EmotionStationActivityScreen
import com.plenart.emotionstationcompose.ui.activity.EmotionStationActivityViewModel
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInScreen
import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInViewModel
import com.plenart.emotionstationcompose.ui.authentication.signUp.SignUpScreen
import com.plenart.emotionstationcompose.ui.authentication.signUp.SignUpViewModel
import com.plenart.emotionstationcompose.ui.childDetails.ChildDetailsScreen
import com.plenart.emotionstationcompose.ui.childDetails.ChildDetailsViewModel
import com.plenart.emotionstationcompose.ui.children.ChildrenScreen
import com.plenart.emotionstationcompose.ui.children.ChildrenScreenViewModel
import com.plenart.emotionstationcompose.ui.home.HomeScreen
import com.plenart.emotionstationcompose.ui.home.HomeScreenViewModel
import com.plenart.emotionstationcompose.ui.info.InfoScreen
import com.plenart.emotionstationcompose.ui.info.InfoScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val showBottomNavigation by remember {
        derivedStateOf {
            when (navBackStackEntry?.destination?.route) {
                ESRoute.SignInScreen.route,
                ESRoute.SignUpScreen.route,
                EmotionStationActivityDestination.route -> false

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
                startDestination = ESRoute.SignInScreen.route,
            ) {
                composable(ESRoute.SignInScreen.route) {
                    val authenticationRepository = koinInject<AuthenticationRepository>()
                    val viewModel = koinViewModel<SignInViewModel>()
                    val state = viewModel.state.collectAsState()

                    LaunchedEffect(key1 = Unit) {
                        if (authenticationRepository.hasUser()) {
                            navController.navigate(ESRoute.HomeScreen.route)
                        }
                    }

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
                    val viewModel = koinViewModel<SignUpViewModel>()
                    val state = viewModel.state.collectAsState()

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
                    val viewModel = koinViewModel<HomeScreenViewModel>()
                    val state = viewModel.uiState.collectAsState()

                    HomeScreen(
                        uiState = state.value,
                        onDropdownAction = {
                            viewModel.onDropdownAction(state.value)
                        },
                        onChildSelectedAction = viewModel::onChildSelectedAction,
                        onEmotionStationAction = {
                            navController.navigate(
                                EmotionStationActivityDestination.createNavigationRoute(
                                    it
                                )
                            )
                        }
                    )
                }
                composable(ESRoute.ChildrenScreen.route) {
                    val viewModel = koinViewModel<ChildrenScreenViewModel>()
                    val state = viewModel.uiState.collectAsState()
                    ChildrenScreen(
                        uiState = state.value,
                        onChildAction = {
                            navController.navigate(ChildDetailsDestination.createNavigationRoute(it))
                        },
                    )
                }
                composable(
                    route = ChildDetailsDestination.route,
                    arguments = listOf(
                        navArgument(CHILD_ID_KEY) {
                            type = NavType.StringType
                        },
                    )
                ) {
                    val childId = it.arguments?.getString(CHILD_ID_KEY)
                    val viewModel = koinViewModel<ChildDetailsViewModel>(
                        parameters = {
                            parametersOf(childId)
                        }
                    )
                    val uiState = viewModel.uiState.collectAsState().value

                    ChildDetailsScreen(
                        onNavigateBack = navController::popBackStack,
                        uiState = uiState,
                        onPrimaryTabSelected = viewModel::onPrimaryTabSelected,
                        onSecondaryTabSelected = viewModel::onSecondaryTabSelected,
                    )
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
                composable(
                    route = EmotionStationActivityDestination.route,
                    arguments = listOf(navArgument(CHILD_ID) { type = NavType.StringType })
                ) {
                    val childId = it.arguments?.getString(CHILD_ID)

                    val viewModel = koinViewModel<EmotionStationActivityViewModel>(
                        parameters = { parametersOf(childId) }
                    )
                    val state = viewModel.uiState.collectAsState()

                    EmotionStationActivityScreen(
                        state = state.value,
                        onQuestionVisible = viewModel::onQuestionVisible,
                        onOptionSelected = viewModel::onOptionSelected,
                        recordActivity = viewModel::recordActivity,
                        onActivityCompletedDialogDismiss = {
                            navController.popBackStack()
                        }

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
