package com.jusiCool.presentation.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

data class PopularStocksSearchData(
    val number: Int,
    val stockName: String,
    val revenuePercent: Float
)

@Composable
fun PopularStocksSearch(
    modifier: Modifier = Modifier,
    popularStocksSearchData: PopularStocksSearchData
) {
    val formattedRevenuePercent = when {
        popularStocksSearchData.revenuePercent == popularStocksSearchData.revenuePercent.toInt().toFloat() -> {
            if (popularStocksSearchData.revenuePercent > 0) "+%.1f%%".format(popularStocksSearchData.revenuePercent)
            else "%.1f%%".format(popularStocksSearchData.revenuePercent)
        }
        popularStocksSearchData.revenuePercent * 10 == (popularStocksSearchData.revenuePercent * 10).toInt().toFloat() -> {
            if (popularStocksSearchData.revenuePercent > 0) "+%.1f%%".format(popularStocksSearchData.revenuePercent)
            else "%.1f%%".format(popularStocksSearchData.revenuePercent)
        }
        else -> {
            if (popularStocksSearchData.revenuePercent > 0) "+%.2f%%".format(popularStocksSearchData.revenuePercent)
            else "%.2f%%".format(popularStocksSearchData.revenuePercent)
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(50.dp)) {
            Text(
                text = "${popularStocksSearchData.number}",
                style = JDSTypography.bodyMedium,
                color = JDSColor.Black
            )

            Text(
                text = popularStocksSearchData.stockName,
                style = JDSTypography.bodySmall,
                color = JDSColor.Black
            )
        }

        Text(
            text = formattedRevenuePercent,
            style = JDSTypography.bodySmall,
            color = if (popularStocksSearchData.revenuePercent < 0) JDSColor.MAIN
            else if (popularStocksSearchData.revenuePercent > 0) JDSColor.ERROR
            else JDSColor.GRAY600,
        )
    }
}

@Preview
@Composable
fun PopularStocksSearchPreview() {
    PopularStocksSearch(popularStocksSearchData = PopularStocksSearchData(2, "게임스탑", +2.9f))
}