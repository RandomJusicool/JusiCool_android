package com.example.design_system.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.example.design_system.theme.color.JDSColor

@Composable
fun JDSNoOutLinedTextField(
    modifier: Modifier = Modifier,
    textState: String,
    placeHolder: @Composable () -> Unit,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle.Default
) {
    JusiCoolAndroidTheme { colors, _ ->

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = modifier.fillMaxWidth()
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colors.GRAY50,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .padding(16.dp),
                value = textState,
                onValueChange = { newText -> onTextChange(newText) },
                singleLine = singleLine,
                minLines = minLines,
                maxLines = maxLines,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                textStyle = textStyle,
                decorationBox = { innerTextField ->
                    Column(verticalArrangement = Arrangement.Center) {
                        if (textState.isEmpty()) {
                            placeHolder()
                        }
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JDSNoOutLinedTextFieldPreview() {
    val (textState, onTextChange) = remember { mutableStateOf("") }

    Column {
        JDSNoOutLinedTextField(
            modifier = Modifier.width(312.dp),
            placeHolder = {
                Text(
                    text = "제목을 입력하세요",
                    style = JDSTypography.titleSmall,
                    color = JDSColor.GRAY200,
                )
            },
            textState = textState,
            onTextChange = onTextChange,
            textStyle = TextStyle()
        )

        JDSNoOutLinedTextField(
            modifier = Modifier.width(312.dp),
            placeHolder = {
                Text(
                    text = "내용을 입력하세요",
                    style = JDSTypography.bodyMedium,
                    color = JDSColor.GRAY200,
                )
            },
            textState = textState,
            onTextChange = onTextChange,
            textStyle = TextStyle()
        )
    }
}