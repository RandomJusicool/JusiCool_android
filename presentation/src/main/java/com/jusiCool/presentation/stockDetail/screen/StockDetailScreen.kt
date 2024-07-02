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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.jusiCool.presentation.stockDetail.component.CommunityCard
import com.jusiCool.presentation.stockDetail.component.StockGraphCard
import com.jusiCool.presentation.stockDetail.component.StockPreviewCard
import com.jusiCool.presentation.stockDetail.component.StockQuotesCard
import com.jusiCool.presentation.stockDetail.component.TimeSegment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

const val stockDetail = "stockDetail"

// 화면이동을 구현하는 NavController확장함수
fun NavController.navigateToStockDetail() {
    this.navigate(stockDetail)
}

// navHost에 화면을 등록할 수 있게 하는 확장 함수
fun NavGraphBuilder.stockDetailRoute(
    popUpBackStack: () -> Unit,
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunityList: () -> Unit,
) {
    composable(stockDetail) {
        StockDetailRoute(
            popUpBackStack = popUpBackStack,
            navigateToStockBuying = navigateToStockBuying,
            navigateToStockSell = navigateToStockSell,
            navigateToCommunityList = navigateToCommunityList,
        )
    }
}

// Route

@Composable
fun StockDetailRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunityList: () -> Unit,
) {
    StockDetailScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToStockBuying = navigateToStockBuying,
        navigateToStockSell = navigateToStockSell,
        navigateToCommunityList = navigateToCommunityList,
    )
}

// Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StockDetailScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    scrollState: ScrollState = rememberScrollState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunityList: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val (whichTimeSegmentSelected, setWhichTimeSegmentSelected) = remember { mutableStateOf(TimeSegment.ONE_MINUTE) }
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
                        text = "마이크로소프트",
                        style = JDSTypography.subTitle,
                        color = JDSColor.Black
                    )
                    Text(
                        text = "현재가 1주 50,000",
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
                            text = "현재가 구매 하기",
                            outLineColor = JDSColor.MAIN,
                            onClick = navigateToStockBuying
                        )
                        JDSOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "예약 구매 하기",
                            outLineColor = JDSColor.MAIN,
                            onClick = navigateToStockBuying
                        )
                    } else {
                        JDSOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "현재가 판매 하기",
                            textColor = JDSColor.ERROR,
                            outLineColor = JDSColor.ERROR,
                            onClick = navigateToStockSell
                        )
                        JDSOutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "예약 판매 하기",
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
                    modifier = Modifier.background(JDSColor.GRAY50),
                    startIcon = {
                        LeftArrowIcon(modifier = Modifier.clickableSingle {
                            popUpBackStack()
                        })
                    },
                    betweenText = "회사이름"
                ) // TODO: viewModel에서 데이터 받아오기
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
                    currentStock = "218,951",
                    stockDiff = "-1,900P (0.8%)"
                ) // TODO: viewModel에서 데이터 받아오기
                Spacer(modifier = Modifier.height(4.dp))
                StockGraphCard(
                    whichTimeSegmentSelected = whichTimeSegmentSelected,
                    setWhichTimeSegmentSelected = setWhichTimeSegmentSelected,
                    toggleOnClick = { /* TODO: 통신 로직 추가 */ }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CommunityCard(navigateToCommunity = navigateToCommunityList)
                Spacer(modifier = Modifier.height(6.dp))
                StockQuotesCard() // TODO: viewModel에서 데이터 받아오기
            }
        }
    }
}

@Preview
@Composable
fun StockDetailScreenPreView() {
    StockDetailRoute(
        popUpBackStack = {},
        navigateToStockBuying = {},
        navigateToStockSell = {},
        navigateToCommunityList = {},
    )
}