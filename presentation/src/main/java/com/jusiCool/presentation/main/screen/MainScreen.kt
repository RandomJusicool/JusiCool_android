package com.jusiCool.presentation.main.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSMainTopBar
import com.example.design_system.icon_image.icon.GraphIcon
import com.example.design_system.icon_image.icon.SearchIcon
import com.example.design_system.icon_image.image.LogoImage
import com.example.design_system.theme.color.JDSColor
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jusiCool.domain.model.user.response.GetMyPointModel
import com.jusiCool.domain.model.user.response.GetMyStockModel
import com.jusiCool.presentation.main.component.CommunityButton
import com.jusiCool.presentation.main.component.MyAccount
import com.jusiCool.presentation.main.component.MyAccountData
import com.jusiCool.presentation.main.component.MyStocks
import com.jusiCool.presentation.main.component.PopularNews
import com.jusiCool.presentation.main.component.PopularSummaryNewsData
import com.jusiCool.presentation.main.viewModel.MainViewModel
import com.jusiCool.presentation.utill.Event
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.delay

const val mainRoute = "mainRoute"

fun NavController.navigateToMain() {
    this.navigate(mainRoute)
}

fun NavGraphBuilder.mainRoute(
    navigateToStockDetail: (String) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToNews: () -> Unit,
    navigateToOrderHistory: () -> Unit,
    navigateToCheckEntireStockList: () -> Unit,
    navigateToCommunityList: () -> Unit,
    navigateToHoldShareRoute: () -> Unit,
) {
    composable(mainRoute) {
        MainRoute(
            navigateToStockDetail = navigateToStockDetail,
            navigateToSearch = navigateToSearch,
            navigateToNews = navigateToNews,
            navigateToOrderHistory = navigateToOrderHistory,
            navigateToCheckEntireStockList = navigateToCheckEntireStockList,
            navigateToCommunity = navigateToCommunityList,
            navigateToHoldShareRoute = navigateToHoldShareRoute,
        )
    }
}

@Composable
internal fun MainRoute(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    navigateToStockDetail: (String) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToNews: () -> Unit,
    navigateToOrderHistory: () -> Unit,
    navigateToCheckEntireStockList: () -> Unit,
    navigateToCommunity: () -> Unit,
    navigateToHoldShareRoute: () -> Unit,
) {
    val (isSwipeRefreshLoading, setIsSwipeRefreshLoading) = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isSwipeRefreshLoading)
    val getMyPointResponse by viewModel.getMyPointResponse.collectAsStateWithLifecycle()
    val getMyStockResponse by viewModel.getMyStockResponse.collectAsStateWithLifecycle()

    MainScreen(
        modifier = modifier,
        swipeRefreshState = swipeRefreshState,
        popularSummaryNewsData = PopularSummaryNewsData(
            "https://newsimg.sedaily.com/2023/04/19/29OD2TUOJ3_1.jpg",
            "\"고마워요 엔비디아\"...삼성전자, 간만의 '불기둥' 지속될까",
            "파이낸셜뉴스",
            1
        ),
        pointData = when (getMyPointResponse) {
            is Event.Success -> getMyPointResponse.data!!
            else -> GetMyPointModel(
                points = 0,
                upDownPercent = 0.0,
                upDownPoints = 0,
            )
        },
        stockData = when (getMyStockResponse) {
            is Event.Success -> getMyStockResponse.data!!
            else -> persistentListOf()
        },
        onRefresh = {
            viewModel.apply {
                getMyPoint()
                getMyStock()
            }
            setIsSwipeRefreshLoading(true)
        },
        navigateToSearch = navigateToSearch,
        navigateToStockDetail = navigateToStockDetail,
        navigateToNews = navigateToNews,
        navigateToOrderHistory = navigateToOrderHistory,
        navigateToCheckEntireStockList = navigateToCheckEntireStockList,
        navigateToCommunity = navigateToCommunity,
        navigateToHoldShareRoute = navigateToHoldShareRoute,
    )

    LaunchedEffect(isSwipeRefreshLoading) {
        if (isSwipeRefreshLoading) {
            delay(1000L)
            setIsSwipeRefreshLoading(false)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.apply {
            getMyPoint()
            getMyStock()
        }
        setIsSwipeRefreshLoading(true)
    }
}

val tempMyAccountData = MyAccountData(137871, -5778, 4.0f, 6)


@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    swipeRefreshState: SwipeRefreshState,
    pointData: GetMyPointModel,
    popularSummaryNewsData: PopularSummaryNewsData,
    stockData: ImmutableList<GetMyStockModel>,
    onRefresh: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToStockDetail: (String) -> Unit,
    navigateToNews: () -> Unit,
    navigateToOrderHistory: () -> Unit,
    navigateToCheckEntireStockList: () -> Unit,
    navigateToCommunity: () -> Unit,
    navigateToHoldShareRoute: () -> Unit,
) {
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = JDSColor.GRAY50)
        ) {
            Column {
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
                    MyAccount(myPoint = pointData)

                    MyStocks(
                        myStocksData = stockData,
                        navigateToHoldShareRoute = navigateToHoldShareRoute,
                        navigateToStockDetail = navigateToStockDetail,
                        navigateToOrderHistory = navigateToOrderHistory,
                    )

                    PopularNews(
                        popularSummaryNewsData = popularSummaryNewsData,
                        navigateToNews = navigateToNews,
                    )

                    Spacer(modifier = Modifier.height(13.dp))
                }
            }
            CommunityButton(
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = 24.dp,
                        bottom = 24.dp
                    ),
                navigateToCommunity = navigateToCommunity
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        swipeRefreshState = rememberSwipeRefreshState(isRefreshing = true),
        navigateToStockDetail = {},
        navigateToNews = {},
        navigateToCommunity = {},
        navigateToSearch = {},
        navigateToCheckEntireStockList = {},
        navigateToOrderHistory = {},
        navigateToHoldShareRoute = {},
        onRefresh = {},
        pointData = GetMyPointModel(0, 0.0, 0),
        popularSummaryNewsData = PopularSummaryNewsData("", "", "", 0),
        stockData = persistentListOf()
    )
}