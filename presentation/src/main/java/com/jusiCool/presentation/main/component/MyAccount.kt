package com.jusiCool.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

data class MyAccountData(
    val point: Int,
    val revenue: Int,
    val revenuePercent: Float
)

@Composable
fun MyAccount(
    modifier: Modifier = Modifier,
    myAccountData: MyAccountData
) {
    val formattedPoint = "%,d".format(myAccountData.point)
    val formattedRevenue = if (myAccountData.revenue > 0) "+%,d".format(myAccountData.revenue)
    else "%,d".format(myAccountData.revenue)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(135.dp)
            .background(
                color = JDSColor.WHITE,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "내 계좌",
            color = JDSColor.Black,
            style = JDSTypography.bodyMedium
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = formattedPoint,
                color = JDSColor.Black,
                style = JDSTypography.titleMedium
            )

            Text(
                text = "P",
                color = JDSColor.Black,
                style = JDSTypography.titleMedium
            )
        }

        Text(
            text = "$formattedRevenue 원 (${myAccountData.revenuePercent}%)",
            color = if (myAccountData.revenue < 0) JDSColor.MAIN
            else if (myAccountData.revenue > 0) JDSColor.ERROR
            else JDSColor.GRAY600,
            style = JDSTypography.bodySmall
        )
    }
}

@Preview
@Composable
fun MyAccountPreview() {
    Column {
        MyAccount(
            modifier = Modifier
                .width(312.dp),
            myAccountData = MyAccountData(137871,-5778,4.0f)
        )

        MyAccount(
            modifier = Modifier
                .width(312.dp),
            myAccountData = MyAccountData(137871,5778,4.0f)
        )

        MyAccount(
            modifier = Modifier
                .width(312.dp),
            myAccountData = MyAccountData(137871,0,0.0f)
        )
    }
}