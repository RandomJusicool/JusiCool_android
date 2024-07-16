package com.jusiCool.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.jusiCool.domain.model.user.response.GetMyStockModel
import com.jusiCool.presentation.utill.formatStockPriceSign

@Composable
fun Stocks(
    modifier: Modifier = Modifier,
    myStocksData: GetMyStockModel,
    navigateToStockDetail: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = JDSColor.WHITE)
            .clickableSingle { navigateToStockDetail(myStocksData.code) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = myStocksData.stock_name,
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            Text(
                text = "${"%,d".format(myStocksData.stock_num)} 주",
                style = JDSTypography.label,
                color = JDSColor.GRAY400
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${"%,d".format(myStocksData.points)} P",
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )

            Text(
                text = "${myStocksData.upDownPoints.toInt().formatStockPriceSign()} (${myStocksData.upDownPercent}%)",
                style = JDSTypography.label,
                color = if (myStocksData.upDownPoints < 0) JDSColor.MAIN
                else if (myStocksData.upDownPoints > 0) JDSColor.ERROR
                else JDSColor.GRAY600,
            )
        }
    }
}

@Preview
@Composable
fun StocksPreview() {
}