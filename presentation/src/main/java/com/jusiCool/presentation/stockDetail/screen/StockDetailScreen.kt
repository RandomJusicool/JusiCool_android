package com.jusiCool.presentation.stockDetail.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val stockDetail = "stockDetail"

// 화면이동을 구현하는 NavController확장함수
fun NavController.navigationToStockDetail() {
    this.navigate(stockDetail)
}

// navHost에 화면을 등록할 수 있게 하는 확장 함수
fun NavGraphBuilder.stockDetailRoute(
    popUpBackStack: () -> Unit,
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    composable(stockDetail) {
        StockDetailRoute(
            popUpBackStack = popUpBackStack,
            navigateToStockBuying = navigateToStockBuying,
            navigateToStockSell = navigateToStockSell,
            navigateToCommunity = navigateToCommunity,
        )
    }
}

// Route

@Composable
fun StockDetailRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    StockDetailScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToStockBuying = navigateToStockBuying,
        navigateToStockSell = navigateToStockSell,
        navigateToCommunity = navigateToCommunity,
    )
}

// Screen

@Composable
fun StockDetailScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunity: () -> Unit,
) {

}