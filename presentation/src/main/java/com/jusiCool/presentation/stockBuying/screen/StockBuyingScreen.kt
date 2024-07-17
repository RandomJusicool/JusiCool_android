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
import androidx.compose.runtime.LaunchedEffect
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
import com.jusiCool.domain.model.user.response.GetMyPointModel
import com.jusiCool.domain.model.user.response.GetMyStockModel
import com.jusiCool.presentation.main.component.EntireStocksData
import com.jusiCool.presentation.main.component.MyAccountData
import com.jusiCool.presentation.main.screen.tempMyAccountData
import com.jusiCool.presentation.main.viewModel.MainViewModel
import com.jusiCool.presentation.stockBuying.viewModel.StockBuyViewModel
import com.jusiCool.presentation.utill.Event
import com.jusiCool.presentation.utill.formatCommunityDate
import com.jusiCool.presentation.utill.formatStockPrice

const val stockBuyingRoute = "stockBuyingRoute"

fun NavController.navigationToStockBuying(id: String) {
    this.navigate("$stockBuyingRoute/$id")
}

fun NavGraphBuilder.stockBuyingRoute(
    navigateToStockDetail: (String) -> Unit,
    navigateToOrderHistory: () -> Unit,
) {
    composable("$stockBuyingRoute/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id") ?: ""
        StockBuyingRoute(
            id = id,
            navigateToStockDetail = navigateToStockDetail,
            navigateToOrderHistory = navigateToOrderHistory,
        )
    }
}

@Composable
fun StockBuyingRoute(
    modifier: Modifier = Modifier,
    id: String,
    viewModel: StockBuyViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    navigateToStockDetail: (String) -> Unit,
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
        navigateToStockDetail = navigateToStockDetail,
        navigateToOrderHistory = navigateToOrderHistory,
        stockData = viewModel.myStock,
        focusManager = focusManager,
        stockText = viewModel.stockText.longValue,
        getMyPoint = { viewModel.getMyPoint() },
        getMyStock = { viewModel.getMyStock() },
        pointData = viewModel.myPoint.value
    )

    LaunchedEffect(Unit) {
        viewModel.getMyPoint()
    }

    LaunchedEffect(Unit) {
        getMyPoint(
            viewModel = viewModel,
            onSuccess = {
                viewModel.myPoint.value = it
            },
            onFailure = { }
        )
    }

    LaunchedEffect(Unit) {
        getMyStock(
            viewModel = viewModel,
            onSuccess = {
                viewModel.myStock.removeRange(0, viewModel.myStock.size)
                viewModel.myStock.addAll(it)
            },
            onFailure = {
                viewModel.myStock.removeRange(0, viewModel.myStock.size)
            }
        )
    }
}

private suspend fun getMyStock(
    viewModel: StockBuyViewModel,
    onSuccess: (data: List<GetMyStockModel>) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getMyStockResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {
                onFailure()
            }
        }
    }
}

private suspend fun getMyPoint(
    viewModel: StockBuyViewModel,
    onSuccess: (data: GetMyPointModel) -> Unit,
    onFailure: () -> Unit
) {
    viewModel.getMyPointResponse.collect { response ->
        when (response) {
            is Event.Success -> {
                onSuccess(response.data!!)
            }

            else -> {
                onFailure()
            }
        }
    }
}

@Composable
internal fun StockBuyingScreen(
    modifier: Modifier = Modifier,
    id: String,
    stockData: List<GetMyStockModel>,
    pointData: GetMyPointModel,
    postBuyStock: (num: Long) -> Unit,
    getMyStock: () -> Unit,
    getMyPoint: () -> Unit,
    navigateToStockDetail: (String) -> Unit,
    navigateToOrderHistory: () -> Unit,
    focusManager: FocusManager,
    stockText: Long
) {
    val (stockTextState, setStockTextState) = remember { mutableStateOf(stockText.toString()) }
    val (isBuyingSuccessful, setIsBuyingSuccessful) = remember { mutableStateOf(false) }
    val (errorMessage, setErrorMessage) = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        getMyPoint()
        getMyStock()
    }

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
                    textState = stockTextState,
                    placeHolder = "최대 N주 구매 가능",
                    label = "몇 주 구매할까요?",
                    helperText = "보유 포인트 ${pointData.points.toInt().formatStockPrice()} P",
                    placerHolderShare = true,
                    onTextChange = {
                        setStockTextState(it)
                        setErrorMessage(null)
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                JDSButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 32.dp),
                    state = if (stockTextState.isEmpty() || stockTextState.toLongOrNull() == null || stockTextState.toLong() == 0L) ButtonState.Disable else ButtonState.Enable,
                    text = "구매 하기",
                    onClick = {
                        val num = stockTextState.toLongOrNull()
                        if (num != null && num > 0) {
                            postBuyStock(num)
                            setIsBuyingSuccessful(true)
                        } else {
                            setErrorMessage("잘못된 입력입니다")
                        }
                    }
                )
            } else {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(120.dp))
                    CostImage(modifier = Modifier.size(177.dp))
                    val firstStock = stockData.firstOrNull()
                    Text(
                        text = if (firstStock != null) {
                            "${firstStock.stock_name} ${stockTextState}주\n" +
                                    " ${(stockTextState.toLong() * firstStock.points)}P 구매 성공"
                        } else {
                            "구매성공"
                        },
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
        navigateToStockDetail = {},
        navigateToOrderHistory = {},
        id = "1L",
        focusManager = LocalFocusManager.current,
        postBuyStock = { _ -> },
        stockText = 0,
        getMyPoint = {},
        pointData = GetMyPointModel(
            points = 0L,
            upDownPercent = 0.0,
            upDownPoints = 0
        ),
        getMyStock = {},
        stockData = listOf()
    )
}