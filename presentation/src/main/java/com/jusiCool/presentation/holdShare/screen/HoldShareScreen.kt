package com.jusiCool.presentation.holdShare.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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
import com.jusiCool.presentation.main.component.MyStocksData
import com.jusiCool.presentation.main.component.Stocks
import com.jusiCool.presentation.main.screen.tempMyStockData
import kotlinx.collections.immutable.ImmutableList

const val holdShareRoute = "holdShareRoute"

fun NavController.navigationToHoldShare() {
    this.navigate(holdShareRoute)
}

fun NavGraphBuilder.holdShareRoute(
    navigateToStockDetail: () -> Unit,
    popUpBackStack: () -> Unit
) {
    composable(route = holdShareRoute) {
        HoldShareRoute(
            navigateToStockDetail = navigateToStockDetail,
            popUpBackStack = popUpBackStack
        )
    }
}

@Composable
internal fun HoldShareRoute(
    modifier: Modifier = Modifier,
    navigateToStockDetail: () -> Unit,
    popUpBackStack: () -> Unit
) {
    HoldShareScreen(
        modifier = modifier,
        navigateToStockDetail = navigateToStockDetail,
        popUpBackStack = popUpBackStack,
        myStocksData = tempMyStockData
    )
}

@Composable
internal fun HoldShareScreen(
    modifier: Modifier = Modifier,
    navigateToStockDetail: () -> Unit,
    popUpBackStack: () -> Unit,
    myStocksData: ImmutableList<MyStocksData>,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.GRAY50,),
        verticalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        JDSArrowTopBar(
            startIcon = { LeftArrowIcon(modifier = Modifier.clickableSingle { popUpBackStack() }) },
            betweenText = "보유 주식"
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(myStocksData) { item ->
                Stocks(
                    modifier = Modifier
                        .background(
                            color = JDSColor.WHITE,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .padding(16.dp),
                    myStocksData = item,
                    navigateToStockDetail = navigateToStockDetail
                )
            }
        }
    }
}

@Preview
@Composable
fun HoldShareScreenPreview() {
    HoldShareScreen(
        navigateToStockDetail = { /*TODO*/},
        popUpBackStack = { /*TODO*/ },
        myStocksData = tempMyStockData
    )
}