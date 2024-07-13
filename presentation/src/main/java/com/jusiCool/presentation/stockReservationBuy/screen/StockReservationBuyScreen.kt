package com.jusiCool.presentation.stockReservationBuy.screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.button.ButtonState
import com.example.design_system.component.button.JDSButton
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.textfield.JDSTextField
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.icon_image.icon.RightArrowIcon
import com.example.design_system.icon_image.image.CostImage
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.checkEntireStock.component.EntireStocksData
import com.jusiCool.presentation.main.component.MyAccountData
import com.jusiCool.presentation.main.screen.tempMyAccountData
import com.jusiCool.presentation.stockReservationBuy.viewmodel.StockReservationBuyViewModel
import com.jusiCool.presentation.utill.formatLongStockPrice
import com.jusiCool.presentation.utill.formatStockPrice

const val stockReservationBuyRoute = "stockReservationBuyRoute"

fun NavController.navigationToStockReservationBuy(id: Long) {
    this.navigate("${stockReservationBuyRoute}/${id}")
}

fun NavGraphBuilder.stockReservationBuyRoute(
    navigateToStockDetail: (Long) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    composable("$stockReservationBuyRoute}/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
        if(id != null) {
            StockReservationBuyRoute(
                id = id,
                navigateToStockDetail = navigateToStockDetail,
                navigateToOrderHistory = navigateToOrderHistory
            )
        }
    }
}

@Composable
internal fun StockReservationBuyRoute(
    modifier: Modifier = Modifier,
    viewModel: StockReservationBuyViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    id: Long,
    navigateToStockDetail: (Long) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    StockReservationBuyScreen(
        modifier = modifier,
        id = id,
        myAccountData = tempMyAccountData,
        entireStocksData = EntireStocksData(0, "", 10000, 8160, 6, 4.9f),
        navigateToStockDetail = navigateToStockDetail,
        navigateToOrderHistory = navigateToOrderHistory,
        focusManager = focusManager,
        postStock = viewModel.postStock.longValue,
        postReserveStock = viewModel.postReserveStock.longValue,
        postBuyStock = {num, goal_price ->
            viewModel.postBuyStock(
                stockId = id,
                num = num,
                goal_price = goal_price
            )
            viewModel.postStock.longValue = 0
            viewModel.postReserveStock.longValue = 0
        }
    )
}

@Composable
internal fun StockReservationBuyScreen(
    modifier: Modifier = Modifier,
    id: Long,
    myAccountData: MyAccountData,
    entireStocksData: EntireStocksData,
    postBuyStock: (num: Long, goal_price: Long) -> Unit,
    navigateToStockDetail: (Long) -> Unit,
    navigateToOrderHistory: () -> Unit,
    focusManager: FocusManager,
    postStock: Long,
    postReserveStock: Long
) {
    val (stockReservationTextState, setStockReservationTextState) = remember { mutableLongStateOf(postStock) }
    val (stockTextState, setStockTextState) = remember { mutableLongStateOf(postReserveStock) }
    val (pager, setPager) = remember { mutableStateOf(1) }

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        JusiCoolAndroidTheme { colors, _ ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colors.WHITE)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            focusManager.clearFocus()
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                JDSArrowTopBar(
                    startIcon = {
                        LeftArrowIcon(modifier = Modifier.clickableSingle {
                            if (pager == 2) setPager(1)
                            else navigateToStockDetail(id)
                        })
                    },
                    betweenText = "주식 구매"
                )

                when (pager) {
                    1 -> {
                        Spacer(modifier = Modifier.height(40.dp))

                        JDSTextField(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            textState = stockReservationTextState.toString(),
                            placeHolder = "예약 금액을 달성했을 때 주식을 구매해요",
                            label = "예약 금액을 입력하세요",
                            helperText = "지금 주식 가격: ${entireStocksData.myStockPrice.formatStockPrice()} P",
                            onTextChange = { setStockReservationTextState(it.toLongOrNull() ?: 0) }
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        JDSButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 32.dp),
                            state = if (stockReservationTextState == 0L) ButtonState.Disable else ButtonState.Enable,
                            text = "다음",
                            onClick = { setPager(2) }
                        )
                    }

                    2 -> {
                        Spacer(modifier = Modifier.height(40.dp))

                        JDSTextField(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            textState = stockTextState.toString(),
                            placeHolder = "최대 N주 구매 가능",
                            label = "몇 주 구매할까요?",
                            helperText = "보유 포인트 ${myAccountData.point.formatStockPrice()} P",
                            placerHolderShare = true,
                            onTextChange = { setStockTextState(it.toLongOrNull() ?: 0) }
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        JDSButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 32.dp),
                            state = if (stockTextState == 0L) ButtonState.Disable else ButtonState.Enable,
                            text = "구매 하기",
                            onClick = {
                                postBuyStock(
                                    stockTextState,
                                    stockReservationTextState
                                )
                                setPager(3)
                            }
                        )
                    }

                    3 -> {
                        Column(
                            modifier = modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(120.dp))

                            CostImage(modifier = Modifier.size(177.dp))

                            Text(
                                text = "${entireStocksData.stockName} ${stockTextState.formatLongStockPrice()}주\n" +
                                        "${(stockTextState.toInt() * stockReservationTextState.toInt()).formatStockPrice()}P 구매 예약 성공",
                                style = JDSTypography.subTitle,
                                color = JDSColor.Black,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.clickableSingle { navigateToOrderHistory() },
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "구매 내역 보러가기",
                                    style = JDSTypography.label,
                                    color = JDSColor.GRAY600
                                )

                                RightArrowIcon()
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun StockReservationBuyingScreenPreview() {
    StockReservationBuyScreen(
        id = 0,
        myAccountData = tempMyAccountData,
        entireStocksData = EntireStocksData(0, "", 10000, 8160, 23, 9.3f),
        navigateToStockDetail = {},
        navigateToOrderHistory = {},
        focusManager = LocalFocusManager.current,
        postStock = 0,
        postReserveStock = 0,
        postBuyStock = {_, _ ->}
    )
}