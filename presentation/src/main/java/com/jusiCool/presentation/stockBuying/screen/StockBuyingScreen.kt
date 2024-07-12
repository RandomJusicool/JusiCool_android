package com.jusiCool.presentation.stockBuying.screen

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
import com.jusiCool.presentation.checkEntireStock.component.EntireStocksData
import com.jusiCool.presentation.main.component.MyAccountData
import com.jusiCool.presentation.main.screen.tempMyAccountData
import com.jusiCool.presentation.stockBuying.viewModel.StockBuyViewModel
import com.jusiCool.presentation.utill.formatStockPrice

const val stockBuyingRoute = "stockBuyingRoute"

fun NavController.navigationToStockBuying(id: Long) {
    this.navigate("$stockBuyingRoute/$id")
}

fun NavGraphBuilder.stockBuyingRoute(
    navigateToStockDetail: (Long) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    composable("$stockBuyingRoute/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
        if (id != null) {
            StockBuyingRoute(
                id = id,
                navigateToStockDetail = navigateToStockDetail,
                navigateToOrderHistory = navigateToOrderHistory
            )
        }
    }
}

@Composable
fun StockBuyingRoute(
    modifier: Modifier = Modifier,
    viewModel: StockBuyViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    id: Long,
    navigateToStockDetail: (Long) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    StockBuyingScreen(
        modifier = modifier,
        postBuyStock = { num ->
            viewModel.postBuyStock(
                stockId = id,
                num = num
            )
        },
        id = id,
        myAccountData = tempMyAccountData,
        entireStocksData = EntireStocksData(id = 1L, "마이크로소프트", 1231, 10000, 8160, 7.9f),
        navigateToStockDetail = navigateToStockDetail,
        navigateToOrderHistory = navigateToOrderHistory,
        focusManager = focusManager,
        stockText = viewModel.stockText.longValue
    )
}

@Composable
internal fun StockBuyingScreen(
    modifier: Modifier = Modifier,
    postBuyStock: (num: Long) -> Unit,
    id: Long,
    myAccountData: MyAccountData,
    entireStocksData: EntireStocksData,
    navigateToStockDetail: (Long) -> Unit,
    navigateToOrderHistory: () -> Unit,
    focusManager: FocusManager,
    stockText: Long
) {
    val (stockTextState, setStockTextState) = remember { mutableLongStateOf(stockText) }
    val (isBuyingSuccessful, setIsBuyingSuccessful) = remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalFocusManager provides focusManager) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = JDSColor.WHITE)
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            JDSArrowTopBar(
                startIcon = {
                    LeftArrowIcon(modifier = Modifier.clickableSingle { navigateToStockDetail(id) })
                },
                betweenText = "주식 구매"
            )

            if (!isBuyingSuccessful) {
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
                        postBuyStock(stockTextState)
                        setIsBuyingSuccessful(true)
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
                        text = "${entireStocksData.stockName} ${stockTextState}주\n" + // 전 pr 병합후 formatLongStockPrice로 변경해 타입을 맞추어 주세요
                                " ${(stockTextState.toInt() * entireStocksData.myStockPrice).formatStockPrice()}P 구매 성공",
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
}

@Preview
@Composable
fun StockBuyingScreenPreview() {
    StockBuyingScreen(
        myAccountData = tempMyAccountData,
        entireStocksData = EntireStocksData(id = 1L, "마이크로소프트", 1231, 10000, 8160, 7.9f),
        navigateToStockDetail = {},
        navigateToOrderHistory = {},
        id = 1L,
        focusManager = LocalFocusManager.current,
        postBuyStock = { _ ->  },
        stockText = 0
    )
}