package com.jusiCool.presentation.orderHistory.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.example.design_system.theme.color.JDSColor
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jusiCool.domain.model.receipt.response.GetReceiptModel
import com.jusiCool.presentation.orderHistory.component.MyStocksOrderHistory
import com.jusiCool.presentation.orderHistory.component.MyStocksOrderReservation
import com.jusiCool.presentation.orderHistory.viewModel.OrderHistoryViewModel

const val orderHistoryRoute = "orderHistoryRoute"

fun NavController.navigateToOrderHistory() {
    this.navigate(orderHistoryRoute)
}

fun NavGraphBuilder.orderHistoryRoute(popUpBackStack: () -> Unit) {
    composable(orderHistoryRoute) {
        OrderHistoryRoute(popUpBackStack = popUpBackStack)
    }
}

@Composable
internal fun OrderHistoryRoute(
    modifier: Modifier = Modifier,
    viewModel: OrderHistoryViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    popUpBackStack: () -> Unit,
) {
    val swipeRefreshLoading by viewModel.swipeRefreshLoading.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = swipeRefreshLoading)

    OrderHistoryScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        buyData = viewModel.getBuyReceipt,
        sellData = viewModel.getBuyReceipt,
        swipeRefreshState = swipeRefreshState,
        loadStuff = viewModel::refreshReceipts,
    )
}

@Composable
internal fun OrderHistoryScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    scrollState: ScrollState = rememberScrollState(),
    buyData: List<GetReceiptModel>,
    sellData: List<GetReceiptModel>,
    swipeRefreshState: SwipeRefreshState,
    loadStuff: () -> Unit,
) {
    val (orderState, setOrderState) = remember { mutableStateOf(true) }

    val filteredBuyData = if (orderState) buyData else emptyList()
    val filteredSellData = if (!orderState) sellData else emptyList()

    JusiCoolAndroidTheme { _, _ ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { loadStuff() }
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = JDSColor.GRAY50)
                    .verticalScroll(scrollState)
            ) {
                JDSArrowTopBar(
                    startIcon = { LeftArrowIcon(modifier = Modifier.clickableSingle { popUpBackStack() }) },
                    betweenText = "주문내역"
                )
                Row(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .drawBehind {
                                drawRect(
                                    if (orderState) JDSColor.Black else JDSColor.GRAY400,
                                    Offset(0f, size.height - 1.dp.toPx()),
                                    Size(size.width, 1.dp.toPx())
                                )
                            }
                            .padding(
                                horizontal = 10.dp,
                                vertical = 8.dp
                            )
                            .clickableSingle { setOrderState(true) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "완료된 주문",
                            style = JDSTypography.subTitle,
                            color = if (orderState) JDSColor.Black else JDSColor.GRAY200,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                drawRect(
                                    if (orderState) JDSColor.GRAY400 else JDSColor.Black,
                                    Offset(0f, size.height - 1.dp.toPx()),
                                    Size(size.width, 1.dp.toPx())
                                )
                            }
                            .padding(
                                horizontal = 10.dp,
                                vertical = 8.dp
                            )
                            .clickableSingle { setOrderState(false) },
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "주문 예약",
                            style = JDSTypography.subTitle,
                            color = if (orderState) JDSColor.GRAY200 else JDSColor.Black,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .heightIn(max = 10000.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    if (orderState) {
                        items(filteredBuyData) { item ->
                            MyStocksOrderHistory(
                                data = item
                            )
                        }
                    } else {
                        items(filteredSellData) { item ->
                            MyStocksOrderReservation(
                                data = item
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OrderHistoryScreenPreview() {
    OrderHistoryScreen(
        popUpBackStack = { },
        buyData = listOf(),
        sellData = listOf(),
        swipeRefreshState = SwipeRefreshState(true),
        loadStuff = {}
    )
}