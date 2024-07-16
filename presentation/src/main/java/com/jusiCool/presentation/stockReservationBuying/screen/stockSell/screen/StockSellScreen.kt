package com.jusiCool.presentation.stockReservationBuying.screen.stockSell.screen

import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.domain.model.user.response.GetMyStockModel
import com.jusiCool.presentation.main.component.EntireStocksData
import com.jusiCool.presentation.main.viewModel.MainViewModel
import com.jusiCool.presentation.stockReservationBuying.screen.stockSell.viewModel.StockSellViewModel
import com.jusiCool.presentation.utill.formatStockPrice

const val stockSellingRoute = "stockSellingRoute"

fun NavController.navigationToStockSelling(id: String) {
    this.navigate("$stockSellingRoute/$id")
}

fun NavGraphBuilder.stockSellingRoute(
    navigateToStockDetail: (String) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    composable("$stockSellingRoute/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id") ?: ""
        StockSellingRoute(
            id = id,
            navigateToStockDetail = navigateToStockDetail,
            navigateToOrderHistory = navigateToOrderHistory
        )
    }
}

@Composable
internal fun StockSellingRoute(
    modifier: Modifier = Modifier,
    viewModel: StockSellViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    id: String,
    navigateToStockDetail: (String) -> Unit,
    navigateToOrderHistory: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    StockSellingScreen(
        modifier = modifier,
        id = id,
        deleteStock = { num ->
            viewModel.deleteStock(
                stockId = id,
                num = num
            )
        },
        stockText = viewModel.stockText.longValue,
        stockData = mainViewModel.myStock,
        entireStocksData = EntireStocksData(id = "1L", "마이크로소프트", 1231, 10000, 8160, 7.9f),
        navigateToStockDetail = navigateToStockDetail,
        navigateToOrderHistory = navigateToOrderHistory
    )
}

@Composable
internal fun StockSellingScreen(
    modifier: Modifier = Modifier,
    id: String,
    deleteStock: (num: Long) -> Unit,
    stockText: Long,
    stockData: List<GetMyStockModel>,
    entireStocksData: EntireStocksData,
    navigateToStockDetail: (String) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    val (stockTextState, setStockTextState) = remember { mutableLongStateOf(stockText) }
    val (isBuyingSuccessful, setIsBuyingSuccessful) = remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.WHITE),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        JDSArrowTopBar(
            startIcon = { LeftArrowIcon(modifier = Modifier.clickableSingle { navigateToStockDetail(id) }) },
            betweenText = "주식 판매"
        )

        if (isBuyingSuccessful) {
            Spacer(modifier = Modifier.height(40.dp))

            JDSTextField(
                modifier = Modifier.padding(horizontal = 24.dp),
                textState = stockTextState.toString(),
                placeHolder = "최대 N주 판매 가능",
                label = "몇 주 판매할까요?",
                helperText = "보유 주 주",//나중에 함
                placerHolderShare = true,
                onTextChange = { setStockTextState(it.toLong()) }
            )

            Spacer(modifier = Modifier.weight(1f))

            JDSButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 32.dp),
                state = if (stockTextState == 0L) ButtonState.Disable else ButtonState.Enable,
                text = "판매 하기",
                onClick = {
                    setIsBuyingSuccessful(false)
                    deleteStock(stockTextState)
                }
            )
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(120.dp))

                CostImage(modifier = Modifier.size(177.dp))

                Text(
                    text = "${entireStocksData.stockName} ${
                        stockTextState.toInt().formatStockPrice()
                    }주\n" +
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
}