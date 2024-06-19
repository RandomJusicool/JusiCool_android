package com.example.design_system.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JusiCoolAndroidTheme

@Composable
fun JDSTextField(
    modifier: Modifier = Modifier,
    textState: String,
    placeHolder: String,
    helperText: String = "",
    label: String,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    isEnabledHelperTextClick: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    onTextChange: (String) -> Unit,
    onClickHelperText: () -> Unit = {},
) {
    JusiCoolAndroidTheme { colors, typography ->
        val isFocused = remember { mutableStateOf(false) }
        DisposableEffect(Unit) {
            onDispose {
                focusManager.clearFocus()
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                color = if (isError) colors.ERROR else colors.Black,
                style = typography.bodySmall
            )

            BasicTextField(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    }
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (isFocused.value) colors.MAIN
                        else if (isEnabled) colors.GRAY100 else colors.GRAY200,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .background(
                        color = if (isEnabled) colors.WHITE else colors.GRAY100,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .padding(16.dp),
                value = textState,
                onValueChange = { newText -> onTextChange(newText) },
                singleLine = singleLine,
                minLines = minLines,
                maxLines = maxLines,
                enabled = isEnabled,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                decorationBox = { innerTextField ->
                    Column(verticalArrangement = Arrangement.Center) {
                        if (textState.isEmpty()) {
                            Text(
                                text = placeHolder,
                                color = if (textState.isEmpty()) colors.GRAY2 else colors.Black,
                                style = typography.bodySmall,
                            )
                        }
                    }
                    innerTextField()
                }
            )
            Text(
                text = helperText,
                color = if (isError) colors.ERROR else colors.MAIN,
                style = typography.label,
                modifier = if (!isError) Modifier
                    .align(Alignment.End)
                    .clickableSingle(
                        enabled = isEnabledHelperTextClick,
                        onClick = onClickHelperText
                    ) else Modifier
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JDSTextFieldPreview() {
    val (textState, onTextChange) = remember { mutableStateOf("") }

    Column {
        JDSTextField(
            modifier = Modifier.width(312.dp),
            label = "아이디",
            placeHolder = "아이디를 입력해주세요",
            textState = textState,
            onTextChange = onTextChange
        )

        JDSTextField(
            modifier = Modifier.width(312.dp),
            label = "아이디",
            placeHolder = "아이디를 입력해주세요",
            textState = textState,
            onTextChange = onTextChange
        )

        JDSTextField(
            modifier = Modifier.width(312.dp),
            label = "아이디",
            placeHolder = "아이디를 입력해주세요",
            isEnabled = false,
            textState = textState,
            onTextChange = onTextChange
        )

        JDSTextField(
            modifier = Modifier.width(312.dp),
            label = "아이디",
            helperText = "아이디를 입력해주세요",
            placeHolder = "Error",
            isError = true,
            textState = textState,
            onTextChange = onTextChange
        )

        JDSTextField(
            modifier = Modifier.width(312.dp),
            label = "아이디",
            placeHolder = "아이디를 입력해주세요",
            textState = textState,
            onTextChange = onTextChange,
            visualTransformation = PasswordVisualTransformation()
        )

        JDSTextField(
            modifier = Modifier.width(312.dp),
            label = "아이디",
            placeHolder = "아이디를 입력해주세요",
            helperText = "영문, 숫자, 특수문자 중 2개 이상의 조합으로 8글자 이상",
            textState = textState,
            onTextChange = onTextChange
        )

        JDSTextField(
            modifier = Modifier.width(312.dp),
            label = "아이디",
            placeHolder = "아이디를 입력해주세요",
            helperText = "이메일 수정하기",
            textState = textState,
            onTextChange = onTextChange,
            isEnabledHelperTextClick = true,
            onClickHelperText = {}
        )
    }
}