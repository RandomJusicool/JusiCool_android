package com.example.design_system.component.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.button.JDSButton
import com.example.design_system.component.button.JDSOutlinedButton
import com.example.design_system.theme.JusiCoolAndroidTheme

@Composable
fun JDSBottomSheet(
    modifier: Modifier = Modifier,
    companyName: String,
    stockStatus: String,
    primaryButton: @Composable () -> Unit,
    secondaryButton: @Composable () -> Unit,
) {
    JusiCoolAndroidTheme { colors, typography ->
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(
                    color = colors.WHITE,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .padding(top = 32.dp, bottom = 32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = companyName,
                    style = typography.label,
                    color = colors.Black,
                )
                Text(
                    text = stockStatus,
                    style = typography.bodySmall,
                    color = colors.GRAY400,
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                primaryButton()
                secondaryButton()
            }
        }
    }
}

@Preview
@Composable
fun JDSBottomSheetPreview() {
    Column {

        JDSBottomSheet(
            companyName = "회사 이름",
            stockStatus = "현재가 1주 50,000",
            primaryButton = {
                JDSButton(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.03f), text = "현재가 구매 하기"
                ) {}
            },
            secondaryButton = {
                JDSButton(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.03f), text = "예약 구매 하기"
                ) {}
            }
        )

        JDSBottomSheet(
            companyName = "회사 이름",
            stockStatus = "현재가 1주 50,000",
            primaryButton = {
                JDSOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.03f),
                    text = "현재가 구매 하기"
                ) {}
            },
            secondaryButton = {
                JDSOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.03f), text = "예약 구매 하기"
                ) {}
            }
        )
    }
}