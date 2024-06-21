package com.jusiCool.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

data class MyStocksData(
    val stockName: String,
    val share: Int,
    val myStockPrice: Int,
    val myStockRevenue: Int,
    val myStockRevenuePercent: Float
)

@Composable
fun Stocks(
    modifier: Modifier = Modifier,
    myStocksData: MyStocksData,
    navigateToStockDetail: () -> Unit,
) {
    val formmatedMyShare = "%,d".format(myStocksData.share)
    val formattedMyStockPrice = "%,d".format(myStocksData.myStockPrice)
    val formattedMyStockRevenue = if (myStocksData.myStockRevenue > 0) "+%,d".format(myStocksData.myStockRevenue)
    else "%,d".format(myStocksData.myStockRevenue)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = JDSColor.WHITE)
            .clickableSingle { navigateToStockDetail() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = myStocksData.stockName,
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            Text(
                text = "$formmatedMyShare 주",
                style = JDSTypography.label,
                color = JDSColor.GRAY400
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$formattedMyStockPrice P",
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            Text(
                text = "$formattedMyStockRevenue (${myStocksData.myStockRevenuePercent}%)",
                style = JDSTypography.label,
                color = if (myStocksData.myStockRevenue < 0) JDSColor.MAIN
                else if (myStocksData.myStockRevenue > 0) JDSColor.ERROR
                else JDSColor.GRAY600,
            )
        }
    }
}

@Preview
@Composable
fun StocksPreview() {
    Column {
        Stocks(
            modifier = Modifier.width(280.dp),
            myStocksData = MyStocksData(
                stockName = "마이크로소프트",
                share = 1231,
                myStockPrice = 11131,
                myStockRevenue = 8160,
                myStockRevenuePercent = 7.9f
            ),
            navigateToStockDetail = { /*TODO*/ }
        )

        Stocks(
            modifier = Modifier.width(280.dp),
            myStocksData = MyStocksData(
                stockName = "마이크로소프트",
                share = 1231,
                myStockPrice = 11131,
                myStockRevenue = -8160,
                myStockRevenuePercent = 7.9f
            ),
            navigateToStockDetail = { /*TODO*/ }
        )

        Stocks(
            modifier = Modifier.width(280.dp),
            myStocksData = MyStocksData(
                stockName = "마이크로소프트",
                share = 1231,
                myStockPrice = 11131,
                myStockRevenue = 0,
                myStockRevenuePercent = 0.0f
            ),
            navigateToStockDetail = { /*TODO*/ }
        )
    }
}