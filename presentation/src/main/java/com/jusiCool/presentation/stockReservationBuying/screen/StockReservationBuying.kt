package com.jusiCool.presentation.stockReservationBuying.screen

import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.checkEntireStock.component.EntireStocksData
import com.jusiCool.presentation.main.component.MyAccountData
import com.jusiCool.presentation.main.screen.tempMyAccountData

const val stockReservationBuyingRoute = "stockReservationBuyingRoute"

fun NavController.navigationToStockReservationBuying() {
    this.navigate(stockReservationBuyingRoute)
}

fun NavGraphBuilder.stockReservationBuyingRoute(
    navigateToStockDetail: () -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    composable(stockReservationBuyingRoute) {
        StockReservationBuyingRoute(
            navigateToStockDetail = navigateToStockDetail,
            navigateToOrderHistory = navigateToOrderHistory
        )
    }
}

@Composable
internal fun StockReservationBuyingRoute(
    modifier: Modifier = Modifier,
    navigateToStockDetail: () -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    StockReservationBuyingScreen(
        modifier = modifier,
        myAccountData = tempMyAccountData,
        entireStocksData = EntireStocksData("마이크로소프트", 1231, 10000, 8160, 7.9f),
        navigateToStockDetail = navigateToStockDetail,
        navigateToOrderHistory = navigateToOrderHistory
    )
}

@Composable
internal fun StockReservationBuyingScreen(
    modifier: Modifier = Modifier,
    myAccountData: MyAccountData,
    entireStocksData: EntireStocksData,
    navigateToStockDetail: () -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    val (stockReservationTextState, setStockReservationTextState) = remember { mutableStateOf("") }
    val (stockTextState, setStockTextState) = remember { mutableStateOf("") }
    val (pagerState, setPagerState) = remember { mutableStateOf(1) }
    val formattedStockPrice = "%,d".format(entireStocksData.myStockPrice)
    val formattedPoint = "%,d".format(myAccountData.point)
    val formattedStock =
        if (stockTextState.isNotEmpty()) "%,d".format(stockTextState.toInt()) else "0"
    val formattedBuyingPoint =
        if (stockTextState.isNotEmpty()) "%,d".format(stockTextState.toInt() * stockReservationTextState.toInt()) else "0"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.WHITE),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        JDSArrowTopBar(
            startIcon = {
                LeftArrowIcon(modifier = Modifier.clickableSingle {
                    if (pagerState == 2) setPagerState(1)
                    else navigateToStockDetail()
                })
            },
            betweenText = "주식 구매"
        )

        when (pagerState) {
            1 -> {
                Spacer(modifier = Modifier.height(40.dp))

                JDSTextField(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    textState = stockReservationTextState,
                    placeHolder = "예약 금액을 달성했을 때 주식을 구매해요",
                    label = "예약 금액을 입력하세요",
                    helperText = "지금 주식 가격: $formattedStockPrice P",
                    onTextChange = setStockReservationTextState
                )

                Spacer(modifier = Modifier.weight(1f))

                JDSButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 32.dp),
                    state = if (stockReservationTextState.isEmpty()) ButtonState.Disable else ButtonState.Enable,
                    text = "다음",
                    onClick = { setPagerState(2) }
                )
            }

            2 -> {
                Spacer(modifier = Modifier.height(40.dp))

                JDSTextField(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    textState = stockTextState,
                    placeHolder = "최대 N주 구매 가능",
                    label = "몇 주 구매할까요?",
                    helperText = "보유 포인트 $formattedPoint P",
                    placerHolderShare = true,
                    onTextChange = setStockTextState
                )

                Spacer(modifier = Modifier.weight(1f))

                JDSButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 32.dp),
                    state = if (stockTextState.isEmpty()) ButtonState.Disable else ButtonState.Enable,
                    text = "구매 하기",
                    onClick = { setPagerState(3) }
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
                        text = "${entireStocksData.stockName} ${formattedStock}주\n ${formattedBuyingPoint}P 구매 예약 성공",
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


@Preview
@Composable
fun StockReservationBuyingScreenPreview() {
    StockReservationBuyingScreen(
        myAccountData = tempMyAccountData,
        entireStocksData = EntireStocksData("마이크로소프트", 1231, 10000, 8160, 7.9f),
        navigateToStockDetail = {},
        navigateToOrderHistory = {}
    )
}