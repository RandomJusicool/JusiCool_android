package com.jusiCool.jusicool_android

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jusiCool.presentation.checkEntireStock.screen.checkEntireStockListRoute
import com.jusiCool.presentation.checkEntireStock.screen.navigateToCheckEntireStockList
import com.jusiCool.presentation.community.screen.communityRoute
import com.jusiCool.presentation.community.screen.navigateToCommunity
import com.jusiCool.presentation.communityDetail.screen.communityDetailRoute
import com.jusiCool.presentation.communityDetail.screen.navigateToCommunityDetail
import com.jusiCool.presentation.communityList.screen.communityListRoute
import com.jusiCool.presentation.communityList.screen.navigateToCommunityList
import com.jusiCool.presentation.communityModify.screen.communityModifyRoute
import com.jusiCool.presentation.communityModify.screen.navigateToCommunityModify
import com.jusiCool.presentation.communityOtherDetail.screen.communityOtherDetailRoute
import com.jusiCool.presentation.communityWriting.screen.navigateToCommunityWriting
import com.jusiCool.presentation.holdShare.screen.holdShareRoute
import com.jusiCool.presentation.holdShare.screen.navigateToHoldShare
import com.jusiCool.presentation.join.screen.joinRoute
import com.jusiCool.presentation.join.screen.navigateToJoin
import com.jusiCool.presentation.login.screen.loginRoute
import com.jusiCool.presentation.login.screen.navigateToLogin
import com.jusiCool.presentation.main.screen.mainRoute
import com.jusiCool.presentation.main.screen.navigateToMain
import com.jusiCool.presentation.news.screen.navigateToNews
import com.jusiCool.presentation.news.screen.newsRoute
import com.jusiCool.presentation.orderHistory.screen.navigateToOrderHistory
import com.jusiCool.presentation.orderHistory.screen.orderHistoryRoute
import com.jusiCool.presentation.search.screen.navigateToSearch
import com.jusiCool.presentation.search.screen.searchRoute
import com.jusiCool.presentation.splash.screen.splashRoute
import com.jusiCool.presentation.stockDetail.screen.navigateToStockDetail
import com.jusiCool.presentation.stockDetail.screen.stockDetailRoute
import com.jusiCool.presentation.stockBuying.screen.stockBuyingRoute
import com.jusiCool.presentation.stockReservationBuying.screen.stockReservationBuyingRoute
import com.jusiCool.presentation.stockReservationSelling.screen.stockReservationSellingRoute
import com.jusiCool.presentation.stockSelling.screen.stockSellingRoute

@Composable
fun JusiCool_Android_NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = splashRoute,
    secondNavigation: String,
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
        splashRoute(
            navigateRoute = if (secondNavigation == mainRoute) navController::navigateToMain
            else navController::navigateToLogin
        )

        loginRoute(
            navigateToFindPassword = { TODO() },
            navigateToMain = navController::navigateToMain,
            navigateToJoin = navController::navigateToJoin,
        )

        joinRoute(popUpBackStack = navController::popBackStack)

        mainRoute(
            navigateToSearch = navController::navigateToSearch,
            navigateToStockDetail = navController::navigateToStockDetail,
            navigateToNews = navController::navigateToNews,
            navigateToOrderHistory = navController::navigateToOrderHistory,
            navigateToCheckEntireStockList = navController::navigateToCheckEntireStockList,
            navigateToCommunityList = navController::navigateToCommunityList,
            navigateToHoldShareRoute = navController::navigateToHoldShare,
        )

        searchRoute(
            popUpBackStack = navController::popBackStack,
            navigateToStockDetail = navController::navigateToStockDetail,
        )

        checkEntireStockListRoute(
            navigateToMain = navController::navigateToMain,
            navigateToSearch = navController::navigateToSearch,
            navigateToStockDetail = navController::navigateToStockDetail
        )

        orderHistoryRoute(popUpBackStack = navController::popBackStack)

        newsRoute(popUpBackStack = navController::popBackStack)

        stockDetailRoute(
            popUpBackStack = navController::popBackStack,
            navigateToStockBuying = { TODO() },
            navigateToStockSell = { TODO() },
            navigateToCommunityList = navController::navigateToCommunityList,
        )

        stockBuyingRoute(
            navigateToStockDetail = navController::navigateToStockDetail,
            navigateToOrderHistory = navController::navigateToOrderHistory,
        )

        stockReservationBuyingRoute(
            navigateToStockDetail = navController::navigateToStockDetail,
            navigateToOrderHistory = navController::navigateToOrderHistory,
        )

        stockSellingRoute(
            navigateToStockDetail = navController::navigateToStockDetail,
            navigateToOrderHistory = navController::navigateToOrderHistory,
        )

        stockReservationSellingRoute(
            navigateToStockDetail = navController::navigateToStockDetail,
            navigateToOrderHistory = navController::navigateToOrderHistory,
        )

        communityDetailRoute(
            popUpBackStack = navController::popBackStack,
            navigateToCommunityModify = navController::navigateToCommunityModify
        )

        communityOtherDetailRoute(
            popUpBackStack = navController::popBackStack,
            navigateToCommunityModify = navController::navigateToCommunityModify
        )

        communityRoute(
            popUpBackStack = navController::popBackStack,
            navigateToCommunityDetail = navController::navigateToCommunityDetail,
            navigateToCommunityWriting = navController::navigateToCommunityWriting,
        )

        navigateToCommunityWriting(
            popUpBackStack = navController::popBackStack,
            navigateToCommunity = navController::popBackStack
        )

        communityListRoute(
            popUpBackStack = navController::popBackStack,
            navigateToCommunity = navController::navigateToCommunity,
        )

        checkEntireStockListRoute(
            navigateToSearch = navController::navigateToSearch,
            navigateToMain = navController::navigateToMain,
            navigateToStockDetail = navController::navigateToStockDetail
        )

        holdShareRoute(
            navigateToStockDetail = navController::navigateToStockDetail,
            popUpBackStack = navController::popBackStack
        )

        communityModifyRoute(popUpBackStack = navController::popBackStack)

    }
}