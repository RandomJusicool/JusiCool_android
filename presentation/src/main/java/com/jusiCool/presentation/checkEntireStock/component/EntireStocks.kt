package com.jusiCool.presentation.checkEntireStock.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

data class EntireStocksData(
    val stockName: String,
    val share: Int,
    val myStockPrice: Int,
    val myStockRevenue: Int,
    val myStockRevenuePercent: Float,
)

@Composable
fun EntireStocksItem(
    modifier: Modifier = Modifier,
    entireStocksData: EntireStocksData
) {
    val formattedMyStockRevenue =
        if (entireStocksData.myStockRevenue > 0) "+%,d".format(entireStocksData.myStockRevenue)
        else "%,d".format(entireStocksData.myStockRevenue)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = JDSColor.WHITE,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .padding(16.dp)
            .clickableSingle { },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = entireStocksData.stockName,
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            Text(
                text =
                if(entireStocksData.share != 0)"${"%,d".format(entireStocksData.share)} 주 보유"
                else "보유 주식 없음",
                style = JDSTypography.label,
                color = JDSColor.GRAY400
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${"%,d".format(entireStocksData.myStockPrice)} P",
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            Text(
                text = "$formattedMyStockRevenue (${entireStocksData.myStockRevenuePercent}%)",
                style = JDSTypography.label,
                color = if (entireStocksData.myStockRevenue < 0) JDSColor.MAIN
                else if (entireStocksData.myStockRevenue > 0) JDSColor.ERROR
                else JDSColor.GRAY600,
            )
        }
    }
}

@Preview
@Composable
fun EntireStocksPreview() {
    Column {
        EntireStocksItem(
            modifier = Modifier.width(312.dp),
            entireStocksData = EntireStocksData(
                stockName = "마이크로소프트",
                share = 1231,
                myStockPrice = 11131,
                myStockRevenue = -8160,
                myStockRevenuePercent = 7.9f
            )
        )

        EntireStocksItem(
            modifier = Modifier.width(312.dp),
            entireStocksData = EntireStocksData(
                stockName = "마이크로소프트",
                share = 0,
                myStockPrice = 11131,
                myStockRevenue = +8160,
                myStockRevenuePercent = 7.9f
            )
        )
    }
}
