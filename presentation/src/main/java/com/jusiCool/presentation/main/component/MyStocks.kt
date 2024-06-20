package com.jusiCool.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.icon_image.icon.RightChevronIcon
import com.example.design_system.icon_image.image.GraphSkeletonImage
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.presentation.main.screen.TempMyAccountData
import com.jusiCool.presentation.main.screen.TempMyStockData

@Composable
fun MyStocks(
    modifier: Modifier = Modifier,
    myStocksData: List<MyStocksData>,
    myAccountData: MyAccountData,
    navigateToStockDetail: () -> Unit,
    navigateToOrderHistory: () -> Unit,
    navigateToHeldShare: () -> Unit,
    navigateToEntireShare: () -> Unit,
) {
    val itemHeight = 48.dp
    val itemSpacing = 16.dp
    val maxVisibleItems = 6
    val visibleItems = myStocksData.size.coerceAtMost(maxVisibleItems)
    val columnHeight = if (visibleItems > 0) {
        itemHeight * visibleItems + itemSpacing * (visibleItems - 1) + 150.dp
    } else {
        246.dp
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .height(columnHeight)
            .background(color = JDSColor.WHITE, shape = RoundedCornerShape(size = 12.dp))
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(27.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "보유 주식",
                style = JDSTypography.subTitle,
                color = JDSColor.Black
            )

            if (visibleItems > 5) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickableSingle { navigateToHeldShare() },
                ) {
                    Text(
                        text = "더보기",
                        style = JDSTypography.label,
                        color = JDSColor.GRAY400,
                    )
                    RightChevronIcon(
                        tint = JDSColor.GRAY400,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                }
            } else if(visibleItems == 0){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickableSingle { navigateToEntireShare() },
                ) {
                    Text(
                        text = "주식 보러가기",
                        style = JDSTypography.label,
                        color = JDSColor.GRAY400,
                    )
                    RightChevronIcon(
                        tint = JDSColor.GRAY400,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))

        if (visibleItems > 0) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                modifier = Modifier.fillMaxWidth()
            ) {
                myStocksData.take(6).forEach { stockData ->
                    Stocks(
                        myStocksData = stockData,
                        navigateToStockDetail = navigateToStockDetail
                    )
                }
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GraphSkeletonImage(modifier = Modifier.size(64.dp))

                Text(
                    text = "현재 보유중인 주식이 없어요",
                    style = JDSTypography.bodySmall,
                    color = JDSColor.GRAY400
                )
            }
        }

        Column(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(JDSColor.GRAY100, RoundedCornerShape(5.dp))
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
        ) {
            Text(
                text = "주문내역",
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickableSingle { navigateToOrderHistory() }
            ) {
                Text(
                    text = "이번 달 ${myAccountData.orderesStockHistory}건",
                    style = JDSTypography.label,
                    color = JDSColor.GRAY600
                )

                RightChevronIcon(
                    tint = JDSColor.GRAY400,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun MyStocksPreview() {
    Row {
        MyStocks(
            modifier = Modifier.width(280.dp),
            myStocksData = TempMyStockData,
            myAccountData = TempMyAccountData,
            navigateToStockDetail = { /*TODO*/ },
            navigateToOrderHistory = { /*TODO*/ },
            navigateToHeldShare = { /*TODO*/ },
            navigateToEntireShare = { /*TODO*/ },
        )
    }
}