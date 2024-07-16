package com.jusiCool.presentation.main.component

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
import com.jusiCool.domain.model.stock.response.GetStockListResponseModel
import com.jusiCool.presentation.utill.formatStockPrice
import com.jusiCool.presentation.utill.formatStockPriceSign

data class EntireStocksData(
    val id: String,
    val stockName: String,
    val share: Int,
    val myStockPrice: Int,
    val myStockRevenue: Int,
    val myStockRevenuePercent: Float,
)

@Composable
fun EntireStocksItem(
    modifier: Modifier = Modifier,
    entireStocksData: GetStockListResponseModel
) {
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
                text = entireStocksData.name,
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            Text(
                text =  "보유 주식 없음",
                style = JDSTypography.label,
                color = JDSColor.GRAY400
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${"%,d".format(entireStocksData.present_price)} P",
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            Text(
                text = "${entireStocksData.upDownPrice.toInt().formatStockPriceSign()} (${entireStocksData.upDownPercent}%)",
                style = JDSTypography.label,
                color = if (entireStocksData.upDownPrice < 0) JDSColor.MAIN
                else if (entireStocksData.upDownPrice > 0) JDSColor.ERROR
                else JDSColor.GRAY600,
            )
        }
    }
}

@Preview
@Composable
fun EntireStocksPreview() {

}
