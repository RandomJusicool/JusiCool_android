package com.jusiCool.presentation.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.icon_image.icon.LeftArrowIcon
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    startIcon: @Composable () -> Unit,
    textState: String,
    onTextChange: (String) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawRect(
                    JDSColor.GRAY100,
                    Offset(0f, size.height - 1.dp.toPx()),
                    Size(size.width, 1.dp.toPx())
                )
            }
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            )
    ) {
        startIcon()

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState,
            onValueChange = { newText -> onTextChange(newText) },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.height(26.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (textState.isEmpty()) {
                        Text(
                            text = "원하시는 주식을 입력해보세요",
                            style = JDSTypography.bodySmall,
                            color = JDSColor.GRAY400
                        )
                    }
                }
                Row(
                    modifier = Modifier.height(26.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTopBarPreview() {
    val (textState, onTextChange) = remember { mutableStateOf("") }

    SearchTopBar(
        startIcon = { LeftArrowIcon() },
        textState = textState,
        onTextChange = onTextChange
    )
}