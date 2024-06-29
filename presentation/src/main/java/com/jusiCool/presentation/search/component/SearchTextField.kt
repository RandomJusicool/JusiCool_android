package com.jusiCool.presentation.search.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JDSTypography
import com.example.design_system.theme.color.JDSColor

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    textState: String,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = textState,
        onValueChange = { newText -> onTextChange(newText) },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.height(26.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (textState.isEmpty()) {
                    Text(
                        text = "원하시는 주식을 검색해보세요",
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

@Preview(showBackground = true)
@Composable
fun SearchTopBarPreview() {
    val (textState, onTextChange) = remember { mutableStateOf("") }

    SearchTextField(
        textState = textState,
        onTextChange = onTextChange
    )
}