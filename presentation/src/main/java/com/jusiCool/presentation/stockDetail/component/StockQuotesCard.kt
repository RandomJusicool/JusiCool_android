package com.jusiCool.presentation.stockDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

@Composable
fun StockQuotesCard(
    modifier: Modifier = Modifier,
    transactionVolume: Long,
    transactionPrice: Long,
) {
    Column(
        modifier = modifier
            .background(
                color = JDSColor.WHITE,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
            Alignment.Top
        ),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "정보",
            style = JDSTypography.subTitle,
            color = JDSColor.Black
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(
                20.dp,
                Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    11.5.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "거래량",
                        style = JDSTypography.bodyMedium,
                        color = JDSColor.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${transactionVolume}p",
                        style = JDSTypography.label,
                        color = JDSColor.GRAY600,
                    )
                }
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "거래대금",
                        style = JDSTypography.bodyMedium,
                        color = JDSColor.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${transactionPrice}p",
                        style = JDSTypography.label,
                        color = JDSColor.GRAY600,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun StockQuotesCardPreview() {
    StockQuotesCard(
        transactionVolume = 3000000, // 예시: 총 거래량 3,000,000주
        transactionPrice = 183000000000, // 예시: 총 거래 금액 1830억원
    )
}
