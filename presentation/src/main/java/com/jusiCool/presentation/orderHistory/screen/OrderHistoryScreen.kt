package com.jusiCool.presentation.orderHistory.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.component.topbar.JDSArrowTopBar
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.communityModifier.screen.CommunityModifierRoute
import com.jusiCool.presentation.orderHistory.component.MyStocksOrderHistory
import com.jusiCool.presentation.orderHistory.component.MyStocksOrderHistoryData
import com.jusiCool.presentation.orderHistory.component.MyStocksOrderReservation
import com.jusiCool.presentation.orderHistory.component.MyStocksOrderReservationData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val orderHistoryRoute = "orderHistoryRoute"

fun NavController.orderHistoryRoute() {
    this.navigate(orderHistoryRoute)
}

fun NavGraphBuilder.orderHistoryRoute(popUpBackStack: () -> Unit) {
    composable(route = orderHistoryRoute) {
        OrderHistoryRoute(popUpBackStack = popUpBackStack)
    }
}

val tempMyStocksOrderHistoryData = persistentListOf(
    MyStocksOrderHistoryData("마이크로소프트", 37250, true),
    MyStocksOrderHistoryData("SOXL", 37250, false),
    MyStocksOrderHistoryData("AMD", 37250, true),
    MyStocksOrderHistoryData("엔비디아", 37250, false),
    MyStocksOrderHistoryData("마이크로소프트", 37250, true),
    MyStocksOrderHistoryData("마이크로소프트", 37250, false),
    MyStocksOrderHistoryData("마이크로소프트", 37250, true),
    MyStocksOrderHistoryData("마이크로소프트", 37250, false),
    MyStocksOrderHistoryData("마이크로소프트", 37250, true),
    MyStocksOrderHistoryData("마이크로소프트", 37250, false),
    MyStocksOrderHistoryData("마이크로소프트", 37250, true),
    MyStocksOrderHistoryData("마이크로소프트", 37250, false),
    MyStocksOrderHistoryData("마이크로소프트", 37250, true),
    MyStocksOrderHistoryData("마이크로소프트", 37250, false),
)

val tempMyStocksOrderReservationData = persistentListOf(
    MyStocksOrderReservationData("마이크로소프트", 3725000),
    MyStocksOrderReservationData("마이크로소프트", 3725000),
    MyStocksOrderReservationData("마이크로소프트", 3725000),
    MyStocksOrderReservationData("마이크로소프트", 3725000),
    MyStocksOrderReservationData("마이크로소프트", 3725000),
)

@Composable
internal fun OrderHistoryRoute(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
) {
    OrderHistoryScreen(
        popUpBackStack = popUpBackStack,
        orderHistoryData = tempMyStocksOrderHistoryData,
        orderReservationData = tempMyStocksOrderReservationData
    )
}


@Composable
internal fun OrderHistoryScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    orderHistoryData: ImmutableList<MyStocksOrderHistoryData>,
    orderReservationData: ImmutableList<MyStocksOrderReservationData>,
) {
    val (orderState, setOrderState) = remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = JDSColor.GRAY50)
    ) {
        JDSArrowTopBar(
            startIcon = { LeftArrowIcon(modifier = Modifier.clickableSingle { popUpBackStack }) },
            betweenText = "주문내역"
        )

        Row(modifier = Modifier.padding(horizontal = 24.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .drawBehind {
                        drawRect(
                            if (orderState) JDSColor.Black else JDSColor.GRAY400,
                            Offset(0f, size.height - 1.dp.toPx()),
                            Size(size.width, 1.dp.toPx())
                        )
                    }
                    .padding(
                        horizontal = 10.dp,
                        vertical = 8.dp
                    )
                    .clickableSingle { setOrderState(true) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "완료된 주문",
                    style = JDSTypography.subTitle,
                    color = if (orderState) JDSColor.Black else JDSColor.GRAY200,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawRect(
                            if (orderState) JDSColor.GRAY400 else JDSColor.Black,
                            Offset(0f, size.height - 1.dp.toPx()),
                            Size(size.width, 1.dp.toPx())
                        )
                    }
                    .padding(
                        horizontal = 10.dp,
                        vertical = 8.dp
                    )
                    .clickableSingle { setOrderState(false) },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "주문 예약",
                    style = JDSTypography.subTitle,
                    color = if (orderState) JDSColor.GRAY200 else JDSColor.Black,
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            if (orderState) {
                items(orderHistoryData.size) { item ->
                    MyStocksOrderHistory(
                        myStocksOrderHistoryData = orderHistoryData[item]
                    )
                }
            } else {
                items(orderReservationData.size) { item ->
                    MyStocksOrderReservation(
                        myStocksOrderReservationData = orderReservationData[item]
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun OrderHistoryScreenPreview() {
    OrderHistoryScreen(
        popUpBackStack = { /*TODO*/ },
        orderHistoryData = tempMyStocksOrderHistoryData,
        orderReservationData = tempMyStocksOrderReservationData
    )
}