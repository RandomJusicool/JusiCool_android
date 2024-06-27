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

data class MyStocksOrderReservationData(
    val stockName: String,
    val price: Int,
)

@Composable
fun MyStocksOrderReservation(
    modifier: Modifier = Modifier,
    myStocksOrderReservationData: MyStocksOrderReservationData
) {
    val formmatedMyPrice = "%,d".format(myStocksOrderReservationData.price)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = myStocksOrderReservationData.stockName,
            style = JDSTypography.bodySmall,
            color = JDSColor.Black
        )

        Text(
            text = "${formmatedMyPrice}원 예약완료",
            style = JDSTypography.label,
            color = JDSColor.GRAY400
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun MyStocksOrderReservationPreview() {
    MyStocksOrderReservation(
        modifier = Modifier.width(312.dp),
        myStocksOrderReservationData = MyStocksOrderReservationData("마이크로소프트", 37250)
    )
}