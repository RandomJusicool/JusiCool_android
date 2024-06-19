package com.jusiCool.presentation.stockDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

data class StockQuotesCardData(
    val minString: String,
    val maxString: String,
    val min: Float,
    val max: Float,
)

@Composable
fun StockQuotesCard(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(color = JDSColor.WHITE, shape = RoundedCornerShape(size = 12.dp))
            .padding(16.dp)


    ) {
        Text(
            text = "시세",
            style = JDSTypography.subTitle,
            color = JDSColor.Black
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PriceBoundsChart(
                stockQuotesCardData = StockQuotesCardData(
                    min = 596772f,
                    max = 600449f,
                    minString = "1일 최저가 596,772 P",
                    maxString = "1일 최고가 600,449 P",
                )
            )
            PriceBoundsChart(
                stockQuotesCardData = StockQuotesCardData(
                    maxString = "1년 최고가 600,449 P",
                    min = 420236f,
                    minString = "1년 최저가 596,772 P",
                    max = 597240f,
                )
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(11.5.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "시작가",
                            style = JDSTypography.bodyMedium,
                            color = JDSColor.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "596,772P",
                            style = JDSTypography.label,
                            color = JDSColor.GRAY600,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "종가",
                            style = JDSTypography.bodyMedium,
                            color = JDSColor.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "596,772P",
                            style = JDSTypography.label,
                            color = JDSColor.GRAY600,
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(70.dp)
                        .background(
                            color = JDSColor.GRAY100,
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                )
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "거래량",
                            style = JDSTypography.bodyMedium,
                            color = JDSColor.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "596,772P",
                            style = JDSTypography.label,
                            color = JDSColor.GRAY600,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "거래대금",
                            style = JDSTypography.bodyMedium,
                            color = JDSColor.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "596,772P",
                            style = JDSTypography.label,
                            color = JDSColor.GRAY600,
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun StockQuotesCardPreview() {
    StockQuotesCard()
}

@Composable
fun PriceBoundsChart(
    modifier: Modifier = Modifier,
    stockQuotesCardData: StockQuotesCardData,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .height(70.dp)
                .background(color = JDSColor.MAIN),
        )
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(
                            0.6f
                                    * stockQuotesCardData.min
                                .minus(stockQuotesCardData.min / 2)
                                    / stockQuotesCardData.max
                                .minus(stockQuotesCardData.max / 2)
                        )
                        .height(20.dp)
                        .background(
                            JDSColor.MAIN200,
                            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                        )
                )
                Text(
                    text = stockQuotesCardData.minString,
                    style = JDSTypography.label,
                    color = JDSColor.GRAY400
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(20.dp)
                        .background(
                            JDSColor.MAIN,
                            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                        )
                )
                Text(
                    text = stockQuotesCardData.maxString,
                    style = JDSTypography.label,
                    color = JDSColor.GRAY600
                )
            }
        }
    }
}

@Preview
@Composable
fun PriceBoundsChartPreview() {
    PriceBoundsChart(
        stockQuotesCardData = StockQuotesCardData(
            min = 596772f,
            max = 600449f,
            minString = "1일 최저가 596,772 P",
            maxString = "1일 최고가 600,449 P",
        )
    )
}