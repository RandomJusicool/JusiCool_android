package com.jusiCool.presentation.orderHistory.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.domain.enumtype.ReceiptEnumType
import com.jusiCool.domain.model.receipt.response.GetReceiptModel

@Composable
fun MyStocksOrderHistory(
    modifier: Modifier = Modifier,
    data: GetReceiptModel
) {
    val formmatedMyPrice = "%,d".format(data.price)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = data.stockName,
            style = JDSTypography.bodySmall,
            color = JDSColor.Black
        )

        if (data.status == ReceiptEnumType.BUY) {
            Text(
                text = "${formmatedMyPrice}원 구매완료",
                style = JDSTypography.label,
                color = JDSColor.GRAY400
            )
        } else if (data.status == ReceiptEnumType.SELL) {
            Text(
                text = "${formmatedMyPrice}원 판매완료",
                style = JDSTypography.label,
                color = JDSColor.MAIN
            )
        }
    }
}

@Preview(showBackground = true,backgroundColor = 0xFFFFFF)
@Composable
fun MyStocksOrderHistoryPreview() {
    MyStocksOrderHistory(
        modifier = Modifier.width(312.dp),
        data = GetReceiptModel(ReceiptEnumType.BUY, "adf", 12)
    )
}