package com.jusiCool.presentation.stockDetail.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.stockDetail.component.CommunityCard
import com.jusiCool.presentation.stockDetail.component.StockGraphCard
import com.jusiCool.presentation.stockDetail.component.StockPreviewCard
import com.jusiCool.presentation.stockDetail.component.StockQuotesCard
import com.jusiCool.presentation.stockDetail.component.TimeSegment

const val stockDetail = "stockDetail"

// 화면이동을 구현하는 NavController확장함수
fun NavController.navigationToStockDetail() {
    this.navigate(stockDetail)
}

// navHost에 화면을 등록할 수 있게 하는 확장 함수
fun NavGraphBuilder.stockDetailRoute(
    popUpBackStack: () -> Unit,
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    composable(stockDetail) {
        StockDetailRoute(
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
    popUpBackStack: () -> Unit,
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    StockDetailScreen(
        modifier = modifier,
        popUpBackStack = popUpBackStack,
        navigateToStockBuying = navigateToStockBuying,
        navigateToStockSell = navigateToStockSell,
        navigateToCommunity = navigateToCommunity,
    )
}

// Screen

@Composable
fun StockDetailScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    scrollState: ScrollState = rememberScrollState(),
    navigateToStockBuying: () -> Unit,
    navigateToStockSell: () -> Unit,
    navigateToCommunity: () -> Unit,
) {
    val (whichTimeSegmentSelected, setWhichTimeSegmentSelected)
            = remember { mutableStateOf(TimeSegment.ONE_MINUTE) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(JDSColor.GRAY50),
        topBar = {
            JDSArrowTopBar(
                modifier = Modifier.background(JDSColor.WHITE),
                startIcon = {
                    LeftArrowIcon(modifier = Modifier.clickableSingle {
                        popUpBackStack()
                    })
                },
                betweenText = "회사이름"
            ) // TODO: viewModel에서 데이터 받아오기
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            StockPreviewCard(
                currentStock = "218,951",
                stockDiff = "-1,900P (0.8%)"
            ) // TODO: viewModel에서 데이터 받아오기
            Spacer(modifier = Modifier.height(4.dp))
            StockGraphCard(
                whichTimeSegmentSelected = whichTimeSegmentSelected,
                setWhichTimeSegmentSelected = setWhichTimeSegmentSelected,
                toggleOnClick = { /* TODO: 통신 로직 추가 */  }
            )
            Spacer(modifier = Modifier.height(8.dp))
            CommunityCard(navigateToCommunity = navigateToCommunity)
            Spacer(modifier = Modifier.height(6.dp))
            StockQuotesCard() // TODO: viewModel에서 데이터 받아오기
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
        navigateToCommunity = {},
    )
}