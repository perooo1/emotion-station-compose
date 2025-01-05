package com.plenart.emotionstationcompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.plenart.emotionstationcompose.R

const val SIGN_IN_ROUTE = "sign_in"
const val SIGN_UP_ROUTE = "sign_up"
const val HOME_ROUTE = "home"
const val CHILDREN_ROUTE = "children"
const val INFO_ROUTE = "info"

sealed class EmotionStationDestination(open val route: String)

sealed class ESRoute(
    override val route: String,
    val iconUnselected: ImageVector? = null,
    val iconSelected: ImageVector? = null,
    val labelId: Int? = null,
) : EmotionStationDestination(route) {

    data object HomeScreen :
        ESRoute(
            HOME_ROUTE,
            iconSelected = Icons.Default.Home,
            iconUnselected = Icons.Outlined.Home,
            labelId = R.string.navigation_bar_home_screen_parent,
        )

    data object ChildrenScreen :
        ESRoute(
            CHILDREN_ROUTE,
            iconSelected = Icons.Default.Face,
            iconUnselected = Icons.Outlined.Face,
            labelId = R.string.navigation_bar_children_screen,
        )

    data object InfoScreen : ESRoute(
        INFO_ROUTE,
        iconSelected = Icons.Default.Info,
        iconUnselected = Icons.Outlined.Info,
        labelId = R.string.navigation_bar_info_screen,
    )

    data object SignInScreen : ESRoute(SIGN_IN_ROUTE)
    data object SignUpScreen : ESRoute(SIGN_UP_ROUTE)
}
