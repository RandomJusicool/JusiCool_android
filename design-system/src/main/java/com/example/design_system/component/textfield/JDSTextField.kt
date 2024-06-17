package com.example.design_system.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JusiCoolAndroidTheme

@Composable
fun JDSTextField(
    modifier: Modifier = Modifier,
    label: String,
    textFieldInfo: String,
    textState: String,
    textFieldOutlineColor: Color,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    supportText: String = "",
    supportTextClick: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextChange: (String) -> Unit,
    onClickSupportText: () -> Unit = {}
) {
    JusiCoolAndroidTheme { colors, typography ->
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }
    val currentOnTextChange by rememberUpdatedState(newValue = onTextChange)

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Text(
            text = label,
            color = if (isError) colors.ERROR else colors.Black,
            style = typography.bodySmall
        )

        BasicTextField(
            value = textState,
            onValueChange = { newText -> currentOnTextChange(newText) },
            enabled = isEnabled,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = if (isFocused.value && textFieldOutlineColor == colors.GRAY100) colors.MAIN else textFieldOutlineColor,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .height(54.dp)
                .fillMaxWidth()
                .background(
                    color = if (isEnabled) colors.WHITE else colors.GRAY100,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused.value = focusState.isFocused
                },
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    if (textState.isEmpty()) {
                        Text(
                            text = textFieldInfo,
                            color = if (textState.isEmpty()) colors.GRAY2 else colors.Black,
                            style = typography.bodySmall,
                        )
                    }
                }
                innerTextField()
            }
        )

        Text(
            text = supportText,
            color = if (isError) colors.ERROR else colors.MAIN,
            style = typography.label,
            modifier = if (!isError) Modifier
                .align(Alignment.End)
                .clickableSingle(
                    enabled = supportTextClick,
                    onClick = onClickSupportText
                ) else Modifier
        )
    }
    }
}

@Preview(showBackground = true)
@Composable
fun JDSTextFieldPreview() {
    JusiCoolAndroidTheme { colors, typography ->
        val (textState, onTextChange) = remember { mutableStateOf("") }

        Column {
            JDSTextField(
                modifier = Modifier.width(312.dp),
                label = "아이디",
                textFieldInfo = "아이디를 입력해주세요",
                textFieldOutlineColor = colors.GRAY100,
                textState = textState,
                onTextChange = onTextChange
            )

            JDSTextField(
                modifier = Modifier.width(312.dp),
                label = "아이디",
                textFieldInfo = "아이디를 입력해주세요",
                textFieldOutlineColor = colors.MAIN,
                textState = textState,
                onTextChange = onTextChange
            )

            JDSTextField(
                modifier = Modifier.width(312.dp),
                label = "아이디",
                textFieldInfo = "아이디를 입력해주세요",
                textFieldOutlineColor = colors.GRAY200,
                isEnabled = false,
                textState = textState,
                onTextChange = onTextChange
            )

            JDSTextField(
                modifier = Modifier.width(312.dp),
                label = "아이디",
                textFieldInfo = "아이디를 입력해주세요",
                textFieldOutlineColor = colors.ERROR,
                supportText = "Error",
                isError = true,
                textState = textState,
                onTextChange = onTextChange
            )

            JDSTextField(
                modifier = Modifier.width(312.dp),
                label = "아이디",
                textFieldInfo = "아이디를 입력해주세요",
                textFieldOutlineColor = colors.GRAY100,
                textState = textState,
                onTextChange = onTextChange,
                visualTransformation = PasswordVisualTransformation()
            )

            JDSTextField(
                modifier = Modifier.width(312.dp),
                label = "아이디",
                textFieldInfo = "아이디를 입력해주세요",
                textFieldOutlineColor = colors.ERROR,
                supportText = "영문, 숫자, 특수문자 중 2개 이상의 조합으로 8글자 이상",
                textState = textState,
                onTextChange = onTextChange
            )

            JDSTextField(
                modifier = Modifier.width(312.dp),
                label = "아이디",
                textFieldInfo = "아이디를 입력해주세요",
                textFieldOutlineColor = colors.ERROR,
                supportText = "이메일 수정하기",
                textState = textState,
                onTextChange = onTextChange,
                supportTextClick = true,
                onClickSupportText = {}
            )
        }
    }
}