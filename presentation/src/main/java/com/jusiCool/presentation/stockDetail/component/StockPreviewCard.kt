package com.jusiCool.presentation.stockDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
fun StockPreviewCard(
    modifier: Modifier = Modifier,
    currentStock: String,
    stockDiff: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = JDSColor.WHITE,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .padding(16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.Start
            ),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = currentStock,
                style = JDSTypography.titleMedium,
                color = JDSColor.Black
            )
            Text(
                text = "P",
                style = JDSTypography.titleMedium,
                color = JDSColor.Black
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.Start
            ),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = "어제보다",
                style = JDSTypography.bodySmall,
                color = JDSColor.GRAY400,
            )
            Text(
                text = stockDiff,
                style = JDSTypography.bodySmall,
                color = JDSColor.MAIN,
            )
        }
    }
}

@Preview
@Composable
fun StockPreviewCardPreview() {
    StockPreviewCard(currentStock = "218,951", stockDiff = "-1,900P (0.8%)")
}