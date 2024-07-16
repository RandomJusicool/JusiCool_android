package com.jusiCool.presentation.stockDetail.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.button.JDSCustomButton
import com.example.design_system.component.button.JDSOutlinedButton
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.modifier.padding.paddingHorizontal
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.domain.model.day.GetDayModel
import com.jusiCool.domain.model.stock.response.GetStockDetailResponseModel
import com.jusiCool.presentation.stockDetail.component.CommunityCard
import com.jusiCool.presentation.stockDetail.component.StockGraphCard
import com.jusiCool.presentation.stockDetail.component.StockPreviewCard
import com.jusiCool.presentation.stockDetail.component.StockQuotesCard
import com.jusiCool.presentation.stockDetail.component.TimeSegment
import com.jusiCool.presentation.stockDetail.viewModel.StockDetailViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

const val stockDetail = "stockDetail"

// 화면이동을 구현하는 NavController확장함수
fun NavController.navigateToStockDetail(id: String) {
    this.navigate("$stockDetail/$id")
}

// navHost에 화면을 등록할 수 있게 하는 확장 함수
fun NavGraphBuilder.stockDetailRoute(
    popUpBackStack: () -> Unit,
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunity: (String, String) -> Unit,
) {
    composable("$stockDetail/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id") ?: ""
        StockDetailRoute(
            id = "005930",
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
    stockDetailViewModel: StockDetailViewModel = hiltViewModel(),
    id: String,
    popUpBackStack: () -> Unit,
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunity: (String, String) -> Unit,
) {
    val stockDetail by stockDetailViewModel.stockDetail.collectAsStateWithLifecycle()
    val stockGraph by stockDetailViewModel.stockGraph.collectAsStateWithLifecycle()

    StockDetailScreen(
        modifier = modifier,
        stockDetailData = stockDetail,
        popUpBackStack = popUpBackStack,
        navigateToStockBuying = navigateToStockBuying,
        navigateToStockSell = navigateToStockSell,
        navigateToCommunity = {
            navigateToCommunity(id,stockDetail.name)
        },
        graphData = stockGraph.toImmutableList()
    )

    LaunchedEffect(Unit) {
        stockDetailViewModel.getStockDetail(id)
        stockDetailViewModel.getDay(id)
    }
}

// Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StockDetailScreen(
    modifier: Modifier = Modifier,
    stockDetailData: GetStockDetailResponseModel,
    graphData: ImmutableList<GetDayModel>,
    popUpBackStack: () -> Unit,
    scrollState: ScrollState = rememberScrollState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val (whichTimeSegmentSelected, setWhichTimeSegmentSelected) = remember {
        mutableStateOf(TimeSegment.ONE_MINUTE)
    }
    val (isSellBottomSheet, setIsSellBottomSheet) = remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = sheetState,
        sheetContent = {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    24.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(
                    vertical = 32.dp,
                    horizontal = 24.dp,
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stockDetailData.name,
                        style = JDSTypography.subTitle,
                        color = JDSColor.Black
                    )
                    Text(
                        text = "현재가 1주 ${stockDetailData.presentPrice}",
                        style = JDSTypography.bodySmall,
                        color = JDSColor.GRAY400
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isSellBottomSheet) {
                        JDSOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "현재가 판매 하기",
                            outLineColor = JDSColor.MAIN,
                            onClick = navigateToStockBuying
                        )
                        JDSOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "예약 판매 하기",
                            outLineColor = JDSColor.MAIN,
                            onClick = navigateToStockBuying
                        )
                    } else {
                        JDSOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "현재가 구매 하기",
                            textColor = JDSColor.ERROR,
                            outLineColor = JDSColor.ERROR,
                            onClick = navigateToStockSell
                        )
                        JDSOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "예약 구매 하기",
                            textColor = JDSColor.ERROR,
                            outLineColor = JDSColor.ERROR,
                            onClick = navigateToStockSell
                        )
                    }
                }
            }
        },
        sheetBackgroundColor = JDSColor.WHITE,
        sheetShape = RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 12.dp
        )
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .background(JDSColor.GRAY50),
            topBar = {
                JDSArrowTopBar(
                    startIcon = {
                        LeftArrowIcon(modifier = Modifier.clickableSingle {
                            popUpBackStack()
                        })
                    },
                    betweenText = stockDetailData.name
                )
            },
            bottomBar = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .paddingHorizontal(
                            horizontal = 24.dp,
                            bottom = 36.dp
                        )
                ) {
                    JDSCustomButton(
                        modifier = Modifier.weight(1f),
                        text = "판매 하기",
                        buttonColor = JDSColor.MAIN,
                        onClick = {
                            coroutineScope.launch { sheetState.show() }
                            setIsSellBottomSheet(true)
                        }
                    )
                    JDSCustomButton(
                        modifier = Modifier.weight(1f),
                        buttonColor = JDSColor.ERROR,
                        text = "구매 하기",
                        onClick = {
                            coroutineScope.launch { sheetState.show() }
                            setIsSellBottomSheet(false)
                        }
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
            ) {
                StockPreviewCard(
                    currentStock = stockDetailData.presentPrice.toString(),
                    stockDiff = "어제보다 ${stockDetailData.upDownPrice} (${stockDetailData.upDownPercent}%)"
                )
                Spacer(modifier = Modifier.height(4.dp))
                StockGraphCard(
                    whichTimeSegmentSelected = whichTimeSegmentSelected,
                    setWhichTimeSegmentSelected = setWhichTimeSegmentSelected,
                    data = graphData
                )
                Spacer(modifier = Modifier.height(8.dp))
                CommunityCard(navigateToCommunity = navigateToCommunity)
                Spacer(modifier = Modifier.height(6.dp))
                StockQuotesCard(
                    transactionVolume = stockDetailData.transactionVolume ?: 0L,
                    transactionPrice = stockDetailData.transactionPrice,
                )
            }
        }
    }
}

@Preview
@Composable
fun StockDetailScreenPreView() {
    StockDetailScreen(
        popUpBackStack = {},
        navigateToStockBuying = {},
        navigateToStockSell = {},
        navigateToCommunity = {},
        stockDetailData = GetStockDetailResponseModel(
            name = "삼성전자",
            code = 5930,
            upDownPrice = -1500, // 예시: -1500원 하락
            upDownPercent = -2.35, // 예시: -2.35% 하락
            presentPrice = 61000, // 예시: 현재 거래 가격 61,000원
            transactionVolume = 3000000, // 예시: 총 거래량 3,000,000주
            transactionPrice = 183000000000, // 예시: 총 거래 금액 1830억원
        ),
        graphData = persistentListOf(
            GetDayModel(
                marketPrice = 0,
                highPrice = 0,
                headPrice = 0,
                lowPrice = 0,
                presentPrice = 0,
                upDownPercent = 0,
                storeAt = "",
                volume = 0,
            )
        )
    )
}