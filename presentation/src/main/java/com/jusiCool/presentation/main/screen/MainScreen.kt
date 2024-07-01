package com.jusiCool.presentation.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSMainTopBar
import com.example.design_system.icon_image.icon.GraphIcon
import com.example.design_system.icon_image.icon.SearchIcon
import com.example.design_system.icon_image.image.LogoImage
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.main.component.MyAccount
import com.jusiCool.presentation.main.component.MyAccountData
import com.jusiCool.presentation.main.component.MyStocks
import com.jusiCool.presentation.main.component.MyStocksData
import com.jusiCool.presentation.main.component.PopularNews
import com.jusiCool.presentation.main.component.PopularSummaryNewsData
import kotlinx.collections.immutable.persistentListOf

const val mainRoute = "mainRoute"

fun NavController.navigationToMain() {
    this.navigate(mainRoute)
}

fun NavGraphBuilder.mainRoute(
    navigateToSearch: () -> Unit,
    navigateToStockDetail: () -> Unit,
    navigateToNews: () -> Unit,
    navigateToOrderHistory: () -> Unit,
    navigateToCheckEntireStockList: () -> Unit,
    navigateToCommunity: () -> Unit,
    navigateToHoldShareRoute: () -> Unit,
) {
    composable(mainRoute) {
        MainRoute(
            navigateToSearch = navigateToSearch,
            navigateToStockDetail = navigateToStockDetail,
            navigateToNews = navigateToNews,
            navigateToOrderHistory = navigateToOrderHistory,
            navigateToCheckEntireStockList = navigateToCheckEntireStockList,
            navigateToCommunity = navigateToCommunity,
            navigateToHoldShareRoute = navigateToHoldShareRoute,
        )
    }
}

@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    navigateToSearch: () -> Unit,
    navigateToStockDetail: () -> Unit,
    navigateToNews: () -> Unit,
    navigateToOrderHistory: () -> Unit,
    navigateToCheckEntireStockList: () -> Unit,
    navigateToCommunity: () -> Unit,
    navigateToHoldShareRoute: () -> Unit,
) {
    MainScreen(
        modifier = modifier,
        navigateToSearch = navigateToSearch,
        navigateToStockDetail = navigateToStockDetail,
        navigateToNews = navigateToNews,
        navigateToOrderHistory = navigateToOrderHistory,
        navigateToCheckEntireStockList = navigateToCheckEntireStockList,
        navigateToCommunity = navigateToCommunity,
        navigateToHoldShareRoute = navigateToHoldShareRoute,
    )
}

val tempMyStockData = persistentListOf(
    MyStocksData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    MyStocksData("마이크로소프트", 1231, 11131, -8160, 7.9f),
    MyStocksData("마이크로소프트", 1231, 11131, 0, 7.9f),
    MyStocksData("마이크로소프트", 1, 11131, 8160, 7.9f),
    MyStocksData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    MyStocksData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    MyStocksData("마이크로소프트", 1231, 11131, 8160, 7.9f),
    MyStocksData("마이크로소프트", 1231, 11131, 8160, 7.9f),
)

val tempMyAccountData = MyAccountData(137871, -5778, 4.0f, 6)

val tempPopularSummaryNewsData = PopularSummaryNewsData(
    "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
    "\"고마워요 엔비디아\"...삼성전자, 간만의 '불기둥' 지속될까",
    "파이낸셜뉴스",
    1
)

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToSearch: () -> Unit,
    navigateToStockDetail: () -> Unit,
    navigateToNews: () -> Unit,
    navigateToOrderHistory: () -> Unit,
    navigateToCheckEntireStockList: () -> Unit,
    navigateToCommunity: () -> Unit,
    navigateToHoldShareRoute: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.GRAY50)
    ) {
        JDSMainTopBar(
            startIcon = { LogoImage() },
            betweenIcon = {
                SearchIcon(modifier = Modifier.clickableSingle { navigateToSearch() })
            },
            endIcon = {
                GraphIcon(
                    modifier = Modifier.clickableSingle { navigateToCheckEntireStockList() },
                    tint = JDSColor.GRAY400
                )
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            MyAccount(myAccountData = tempMyAccountData)

            MyStocks(
                myStocksData = tempMyStockData,
                myAccountData = tempMyAccountData,
                navigateToHoldShareRoute = navigateToHoldShareRoute,
                navigateToStockDetail = navigateToStockDetail,
                navigateToOrderHistory = navigateToOrderHistory,
            )

            PopularNews(
                popularSummaryNewsData = tempPopularSummaryNewsData,
                navigateToNews = navigateToNews,
            )

            Spacer(modifier = Modifier.height(13.dp))
        }
    }
}

@Preview
@Composable
fun MainScreenPriview() {
    MainScreen(
        navigateToSearch = { },
        navigateToStockDetail = { },
        navigateToNews = { },
        navigateToOrderHistory = { },
        navigateToCheckEntireStockList = { },
        navigateToCommunity = { },
        navigateToHoldShareRoute = { },
    )
}