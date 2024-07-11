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
import com.jusiCool.domain.model.board.response.GetCommunityBoardListResponseModel
import com.jusiCool.domain.model.community.response.GetCommunityListResponseModel

@Composable
fun WritingCommunityButton(
    modifier: Modifier = Modifier,
    navigateToCommunityWriting: (Long) -> Unit,
    data: Long
) {
    JusiCoolAndroidTheme { colors, _ ->
        Box(
            modifier = modifier
                .background(
                    color = colors.MAIN,
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(all = 24.dp)
                .clickableSingle {
                    navigateToCommunityWriting(data)
                }
        ) {
            PencilIcon()
        }
    }
}

@Preview
@Composable
private fun pre() {
//    WritingCommunityButton(
//        navigateToCommunityWriting = {},
//        data = listOf()
//    )
}