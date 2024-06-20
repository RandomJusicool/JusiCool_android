package com.jusiCool.jusicool_android

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jusiCool.presentation.communityList.screen.communityRoute
import com.jusiCool.presentation.join.screen.joinRoute
import com.jusiCool.presentation.login.screen.loginRoute
import com.jusiCool.presentation.splash.screen.splashRoute
import com.jusiCool.presentation.stockDetail.screen.stockDetailRoute

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
        startDestination = startDestination,
    ) {
        splashRoute()

        loginRoute(
            navigateToFindPassword = { TODO() },
            navigateToLogin = { TODO() },
            navigateToJoin = { TODO() },
        )

        stockDetailRoute(
            popUpBackStack = navController::popBackStack,
            navigateToStockBuying = { TODO() },
            navigateToStockSell = { TODO() },
            navigateToCommunity = { TODO() },
        )

        joinRoute(
            popUpBackStack = navController::popBackStack,
            navigateToMain = { /* TODO: */ },
        )

        communityRoute(
            popUpBackStack = navController::popBackStack,
            navigateToDetailCommunity = { TODO() },
        )
    }
}