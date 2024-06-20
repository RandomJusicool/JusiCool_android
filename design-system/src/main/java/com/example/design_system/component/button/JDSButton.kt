package com.example.design_system.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.example.design_system.theme.color.JDSColor

@Composable
fun JDSButton(
    modifier: Modifier = Modifier,
    text: String,
    state: ButtonState = ButtonState.Enable,
    onClick: () -> Unit,
) {
    JusiCoolAndroidTheme { colors, typography ->

        val interactionSource = remember { MutableInteractionSource() }

        val enabledState: (buttonState: ButtonState) -> Boolean = {
            when (it) {
                ButtonState.Enable -> true
                ButtonState.Disable -> false
            }
        }
        Button(
            modifier = modifier,
            interactionSource = interactionSource,
            enabled = enabledState(state),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.MAIN,
                contentColor = colors.WHITE,
                disabledContainerColor = colors.GRAY300,
                disabledContentColor = colors.GRAY600
            ),
            contentPadding = PaddingValues(vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = onClick,
        ) {
            Text(
                text = text,
                style = typography.bodyMedium
            )
        }
    }
}

@Composable
fun JDSMainColorOutLineButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    JusiCoolAndroidTheme { colors, typography ->
        OutlinedButton(
            modifier = modifier,
            border = BorderStroke(1.dp, colors.MAIN),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = colors.WHITE,
                contentColor = colors.MAIN
            ),
            contentPadding = PaddingValues(vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = onClick
        ) {
            Text(
                text = text,
                style = typography.bodyMedium,
            )
        }
    }
}

@Composable
fun JDSRedColorOutLineButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    JusiCoolAndroidTheme { colors, typography ->
        OutlinedButton(
            modifier = modifier,
            border = BorderStroke(1.dp, colors.ERROR),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = colors.WHITE,
                contentColor = colors.ERROR
            ),
            contentPadding = PaddingValues(vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = onClick
        ) {
            Text(
                text = text,
                style = typography.bodyMedium
            )
        }
    }
}


@Composable
fun JDSCustomButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonColor: Color = JDSColor.MAIN,
    textColor: Color = JDSColor.WHITE,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(12.dp),
            )
            .clickableSingle { onClick() }
            .padding(horizontal = 32.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = JDSTypography.bodyMedium,
            color = textColor
        )
    }
}


@Preview
@Composable
fun JDSButtonPreview() {
    Column(
        modifier = Modifier.height(300.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        JDSButton(
            text = "o0뀨0o",
            modifier = Modifier.fillMaxWidth()
        ) {}
        JDSButton(
            text = "o0뀨0o",
            modifier = Modifier.fillMaxWidth(),
            state = ButtonState.Disable
        ) {}
        JDSMainColorOutLineButton(
            text = "o0뀨0o",
            modifier = Modifier.fillMaxWidth()
        ) {}
        JDSRedColorOutLineButton(
            text = "o0뀨0o",
            modifier = Modifier.fillMaxWidth()
        ) {}
        JDSCustomButton(
            buttonColor = JDSColor.ERROR,
            text = "o0뀨0o",
            modifier = Modifier.fillMaxWidth()
        ) {}
    }
}