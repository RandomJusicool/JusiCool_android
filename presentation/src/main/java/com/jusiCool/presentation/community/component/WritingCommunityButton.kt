package com.jusiCool.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.icon_image.icon.PencilIcon
import com.example.design_system.theme.JusiCoolAndroidTheme

@Composable
fun WritingCommunityButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    JusiCoolAndroidTheme { colors, typography ->
        Box(
            modifier = modifier
                .background(
                    color = colors.MAIN,
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(all = 24.dp)
                .clickableSingle { onClick() }
        ) {
            PencilIcon()
        }
    }
}

@Preview
@Composable
private fun pre() {
    WritingCommunityButton {

    }
}