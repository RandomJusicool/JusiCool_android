package com.jusiCool.presentation.orderHistory.component

import android.graphics.Paint.Style
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

data class MyStocksOrderHistoryData(
    val stockName: String,
    val price: Int,
    val isSell: Boolean,
)

@Composable
fun MyStocksOrderHistory(
    modifier: Modifier = Modifier,
    myStocksOrderHistoryData: MyStocksOrderHistoryData
) {
    val formmatedMyPrice = "%,d".format(myStocksOrderHistoryData.price)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = myStocksOrderHistoryData.stockName,
            style = JDSTypography.bodySmall,
            color = JDSColor.Black
        )

        if(myStocksOrderHistoryData.isSell) {
            Text(
                text = "${formmatedMyPrice}원 구매완료",
                style = JDSTypography.label,
                color = JDSColor.GRAY400
            )
        } else {
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
        myStocksOrderHistoryData = MyStocksOrderHistoryData("마이크로소프트", 37250, false)
    )
}