package com.jusiCool.jusicool_android

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jusiCool.presentation.community.screen.communityRoute
import com.jusiCool.presentation.communityList.screen.communityMainRoute
import com.jusiCool.presentation.communityWriting.screen.communityWritingRoute
import com.jusiCool.presentation.join.screen.joinRoute
import com.jusiCool.presentation.login.screen.loginRoute
import com.jusiCool.presentation.main.screen.mainRoute
import com.jusiCool.presentation.splash.screen.splashRoute

@Composable
fun JusiCool_Android_NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "splashRoute",
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        startDestination = startDestination
    ) {
        splashRoute()

        loginRoute(
            navigateToFindPassword = { TODO() },
            navigateToLogin = { TODO() },
            navigateToJoin = { TODO() }
        )

        joinRoute(
            popUpBackStack = navController::popBackStack,
            navigateToMain = { /* TODO: */ }
        )

        communityMainRoute(
            popUpBackStack = navController::popBackStack,
            navigateToCommunity = { TODO() }
        )

        communityRoute(
            popUpBackStack = navController::popBackStack,
            navigateToDetailCommunity = { TODO() },
            navigateToCommunityWriting = { TODO() }
        )

        mainRoute(
            navigateToSearch = { /*TODO*/ },
            navigateToStockDetail = { /*TODO*/ },
            navigateToNews = { /*TODO*/ },
            navigateToOrderHistory = { /*TODO*/ },
        )

        communityWritingRoute(popUpBackStack = navController::popBackStack)
    }
}