package com.jusiCool.jusicool_android

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jusiCool.presentation.checkEntireStock.screen.checkEntireStockListRoute
import com.jusiCool.presentation.checkEntireStock.screen.navigationToCheckEntireStockList
import com.jusiCool.presentation.community.screen.communityRoute
import com.jusiCool.presentation.community.screen.navigateToCommunity
import com.jusiCool.presentation.communityDetail.screen.communityDetailRoute
import com.jusiCool.presentation.communityList.screen.communityListRoute
import com.jusiCool.presentation.communityList.screen.navigateToCommunityList
import com.jusiCool.presentation.communityModifier.screen.communityModifierRoute
import com.jusiCool.presentation.communityWriting.screen.communityWritingRoute
import com.jusiCool.presentation.holdShare.screen.holdShareRoute
import com.jusiCool.presentation.holdShare.screen.navigationToHoldShare
import com.jusiCool.presentation.join.screen.joinRoute
import com.jusiCool.presentation.join.screen.navigationToJoin
import com.jusiCool.presentation.login.screen.loginRoute
import com.jusiCool.presentation.main.screen.mainRoute
import com.jusiCool.presentation.main.screen.navigationToMain
import com.jusiCool.presentation.news.screen.newsRoute
import com.jusiCool.presentation.orderHistory.screen.orderHistoryRoute
import com.jusiCool.presentation.search.screen.searchRoute
import com.jusiCool.presentation.splash.screen.splashRoute
import com.jusiCool.presentation.stockDetail.screen.navigationToStockDetail
import com.jusiCool.presentation.stockDetail.screen.stockDetailRoute

@Composable
fun JusiCool_Android_NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = splashRoute,
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
        splashRoute(navigateToMain = navController::navigationToCheckEntireStockList)

        loginRoute(
            navigateToFindPassword = { TODO() },
            navigateToMain = navController::navigationToMain,
            navigateToJoin = navController::navigationToJoin,
        )

        joinRoute(popUpBackStack = navController::popBackStack)

        mainRoute(
            navigateToSearch = navController::searchRoute,
            navigateToStockDetail = navController::navigationToStockDetail,
            navigateToNews = navController::newsRoute,
            navigateToOrderHistory = navController::orderHistoryRoute,
            navigateToCheckEntireStockList = navController::navigationToCheckEntireStockList,
            navigateToCommunityList = navController::navigateToCommunityList,
            navigateToHoldShareRoute = navController::navigationToHoldShare,
        )

        searchRoute(
            popUpBackStack = navController::popBackStack,
            navigateToStockDetail = navController::navigationToStockDetail,
        )

        checkEntireStockListRoute(
            navigateToMain = navController::navigationToMain,
            navigateToSearch = navController::searchRoute,
            navigateToStockDetail = navController::navigationToStockDetail
        )

        orderHistoryRoute(popUpBackStack = navController::popBackStack)

        newsRoute(popUpBackStack = navController::popBackStack)

        stockDetailRoute(
            popUpBackStack = navController::popBackStack,
            navigateToStockBuying = { TODO() },
            navigateToStockSell = { TODO() },
            navigateToCommunityList = navController::navigateToCommunityList,
        )

        communityRoute(
            popUpBackStack = navController::popBackStack,
            navigateToCommunityDetail = navController::communityDetailRoute,
            navigateToCommunityWriting = navController::communityWritingRoute,
        )

        communityWritingRoute(popUpBackStack = navController::popBackStack)

        communityListRoute(
            popUpBackStack = navController::popBackStack,
            navigateToCommunity = navController::navigateToCommunity,
        )

        checkEntireStockListRoute(
            navigateToSearch = navController::searchRoute,
            navigateToMain = navController::navigationToMain,
            navigateToStockDetail = navController::navigationToStockDetail
        )

        holdShareRoute(
            navigateToStockDetail = navController::navigationToStockDetail,
            popUpBackStack = navController::popBackStack
        )
        communityModifierRoute(popUpBackStack = navController::popBackStack)
    }
}