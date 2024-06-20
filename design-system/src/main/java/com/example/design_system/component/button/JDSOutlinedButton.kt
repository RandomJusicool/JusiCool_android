package com.example.design_system.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.example.design_system.theme.color.JDSColor

@Composable
fun JDSOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    outLineColor: Color = JDSColor.MAIN,
    backgroundColor: Color = JDSColor.WHITE,
    textColor: Color = JDSColor.MAIN,
    onClick: () -> Unit,
) {
    JusiCoolAndroidTheme { _, typography ->
        Column(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = outLineColor,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .clickableSingle(
                    enabled = enabled,
                    onClick = onClick,
                )
                .padding(vertical = 16.dp, horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                color = textColor,
                style = typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun JDSOutlinedButtonPreview() {
    Column {
        JDSOutlinedButton(
            text = "text",
            enabled = true,
            onClick = {}
        )
        JDSOutlinedButton(
            text = "text",
            enabled = false,
            onClick = {}
        )
    }
}