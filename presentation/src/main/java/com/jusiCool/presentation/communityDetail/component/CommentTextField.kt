package com.jusiCool.presentation.communityDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.icon_image.icon.SendIcon
import com.example.design_system.theme.JusiCoolAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentTextField(
    modifier: Modifier,
    placeholder: String,
    isDisabled: Boolean,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit,
    onButtonClicked: () -> Unit,
    value: String? = null,
) {
    var text by remember { mutableStateOf(value ?: "") }

    JusiCoolAndroidTheme { colors, typography ->
        Column {
            OutlinedTextField(
                placeholder = {
                    Text(
                        text = placeholder,
                        style = typography.bodySmall,
                        color = colors.GRAY400
                    )
                },
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                modifier = modifier
                    .border(
                        1.dp,
                        shape = RoundedCornerShape(12.dp),
                        color = colors.GRAY400
                    )
                    .background(color = Color.Transparent,),
                textStyle = typography.bodySmall.copy(color = colors.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedPlaceholderColor = colors.GRAY400,
                    unfocusedPlaceholderColor = colors.GRAY400,
                    focusedTextColor = colors.Black,
                    unfocusedTextColor = colors.Black,
                    disabledTextColor = colors.GRAY400,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                ),
                enabled = !isDisabled,
                singleLine = singleLine,
                trailingIcon = {
                    IconButton(onClick = onButtonClicked) {
                        SendIcon(tint = if (text.isEmpty()) colors.GRAY400 else colors.MAIN)
                    }
                }
            )
        }
    }
}