package com.jusiCool.presentation.stockSelling.screen

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
import com.jusiCool.presentation.main.component.MyStocksData
import com.jusiCool.presentation.utill.formatStockPrice

const val stockSellingRoute = "stockSellingRoute"

fun NavController.navigationToStockSelling() {
    this.navigate(stockSellingRoute)
}

fun NavGraphBuilder.stockSellingRoute(
    navigateToStockDetail: (Long) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    composable(stockSellingRoute) {
        StockSellingRoute(
            navigateToStockDetail = navigateToStockDetail,
            navigateToOrderHistory = navigateToOrderHistory
        )
    }
}

@Composable
internal fun StockSellingRoute(
    modifier: Modifier = Modifier,
    navigateToStockDetail: (Long) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    StockSellingScreen(
        modifier = modifier,
        myStocksData = MyStocksData("마이크로소프트", 1231, 11131, 0, 0.0f),
        entireStocksData = EntireStocksData("마이크로소프트", 1231, 10000, 8160, 7.9f),
        navigateToStockDetail = navigateToStockDetail,
        navigateToOrderHistory = navigateToOrderHistory
    )
}

@Composable
internal fun StockSellingScreen(
    modifier: Modifier = Modifier,
    myStocksData: MyStocksData,
    entireStocksData: EntireStocksData,
    navigateToStockDetail: (Long) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    val (stockTextState, setStockTextState) = remember { mutableStateOf("") }
    val (isBuyingSuccessful, setIsBuyingSuccessful) = remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.WHITE),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        JDSArrowTopBar(
            startIcon = { LeftArrowIcon(modifier = Modifier.clickableSingle { navigateToStockDetail() }) },
            betweenText = "주식 판매"
        )

        if (isBuyingSuccessful) {
            Spacer(modifier = Modifier.height(40.dp))

            JDSTextField(
                modifier = Modifier.padding(horizontal = 24.dp),
                textState = stockTextState,
                placeHolder = "최대 N주 판매 가능",
                label = "몇 주 판매할까요?",
                helperText = "보유 주 ${myStocksData.share}주",
                placerHolderShare = true,
                onTextChange = setStockTextState
            )

            Spacer(modifier = Modifier.weight(1f))

            JDSButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 32.dp),
                state = if (stockTextState.isEmpty()) ButtonState.Disable else ButtonState.Enable,
                text = "판매 하기",
                onClick = { setIsBuyingSuccessful(false) }
            )
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(120.dp))

                CostImage(modifier = Modifier.size(177.dp))

                Text(
                    text = "${entireStocksData.stockName} ${stockTextState.formatStockPrice()}주\n" +
                            "${(stockTextState.toInt() * entireStocksData.myStockPrice).formatStockPrice()}P 판매 성공",
                    style = JDSTypography.subTitle,
                    color = JDSColor.Black
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

@Preview
@Composable
fun StockSellingScreenPreview() {
    StockSellingScreen(
        myStocksData = MyStocksData("마이크로소프트", 1231, 11131, 0, 0.0f),
        entireStocksData = EntireStocksData("마이크로소프트", 1231, 10000, 8160, 7.9f),
        navigateToStockDetail = {},
        navigateToOrderHistory = {}
    )
}