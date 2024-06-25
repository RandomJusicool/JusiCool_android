package com.jusiCool.jusicool_android

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jusiCool.presentation.community.screen.communityRoute
import com.jusiCool.presentation.communityList.screen.communityListRoute
import com.jusiCool.presentation.communityWriting.screen.communityWritingRoute
import com.jusiCool.presentation.join.screen.joinRoute
import com.jusiCool.presentation.login.screen.loginRoute
import com.jusiCool.presentation.main.screen.mainRoute
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
        communityListRoute(
            popUpBackStack = navController::popBackStack,
            navigateToCommunity = { TODO() }
        )

        communityWritingRoute(popUpBackStack = navController::popBackStack)

        communityRoute(
            popUpBackStack = navController::popBackStack,
            navigateToDetailCommunity = { TODO() },
            navigateToCommunityWriting = { TODO() }
        )

        joinRoute(
            popUpBackStack = navController::popBackStack,
            navigateToMain = { /* TODO: */ },
        )

        loginRoute(
            navigateToFindPassword = { TODO() },
            navigateToLogin = { TODO() },
            navigateToJoin = { TODO() },
        )

        mainRoute(
            navigateToSearch = { /*TODO*/ },
            navigateToStockDetail = { /*TODO*/ },
            navigateToNews = { /*TODO*/ },
            navigateToOrderHistory = { /*TODO*/ },
        )

        splashRoute()

        stockDetailRoute(
            popUpBackStack = navController::popBackStack,
            navigateToStockBuying = { TODO() },
            navigateToStockSell = { TODO() },
            navigateToCommunity = { TODO() },
        )
    }
}