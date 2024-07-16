package com.jusiCool.presentation.main.component

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor
import com.jusiCool.domain.model.user.response.GetMyPointModel
import com.jusiCool.presentation.utill.formatStockPriceSign

data class MyAccountData(
    val point: Int,
    val revenue: Int,
    val revenuePercent: Float,
    val orderesStockHistory: Int,
)

@Composable
fun MyAccount(
    modifier: Modifier = Modifier,
    myPoint: GetMyPointModel,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
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

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "%,d".format(myPoint.points),
                color = JDSColor.Black,
                style = JDSTypography.titleMedium
            )

            Text(
                text = "P",
                color = JDSColor.Black,
                style = JDSTypography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${myPoint.upDownPoints.toInt().formatStockPriceSign()} 원 (${myPoint.upDownPercent}%)",
            color = if (myPoint.upDownPoints < 0) JDSColor.MAIN
            else if (myPoint.upDownPoints > 0) JDSColor.ERROR
            else JDSColor.GRAY600,
            style = JDSTypography.bodySmall
        )
    }
}

@Preview
@Composable
fun MyAccountPreview() {

}