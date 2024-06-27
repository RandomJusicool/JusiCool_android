package com.jusiCool.presentation.communityModifier.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JusiCoolAndroidTheme

@Composable
fun CommunityModifierDialog(
    modifier: Modifier = Modifier,
    checkOnClick: () -> Unit,
    cancelOnClick: () -> Unit
) {
    JusiCoolAndroidTheme { colors, typography ->
        Box(
            modifier = modifier
                .background(
                    color = colors.WHITE,
                    shape = MaterialTheme.shapes.medium.copy(all = CornerSize(12.dp))
                )
                .wrapContentSize()
                .width(216.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "수정을 그만둘까요?",
                    style = typography.bodySmall
                )
                Row(
                    modifier = modifier
                        .border(
                            width = 1.dp,
                            color = colors.GRAY100,
                            shape = MaterialTheme.shapes.medium.copy(
                                bottomStart = CornerSize(12.dp),
                                bottomEnd = CornerSize(12.dp),
                                topStart = CornerSize(0.dp),
                                topEnd = CornerSize(0.dp)
                            )
                        )
                ) {
                    Box(
                        modifier = modifier
                            .clip(
                                MaterialTheme.shapes.medium.copy(
                                    bottomStart = CornerSize(12.dp),
                                    bottomEnd = CornerSize(0.dp),
                                    topStart = CornerSize(0.dp),
                                    topEnd = CornerSize(0.dp)
                                )
                            )
                            .background(
                                color = colors.WHITE
                            )
                            .weight(1f)
                            .border(
                                width = 1.dp,
                                color = colors.GRAY100,
                                shape = MaterialTheme.shapes.medium.copy(
                                    bottomStart = CornerSize(12.dp),
                                    topStart = CornerSize(0.dp),
                                    bottomEnd = CornerSize(0.dp),
                                    topEnd = CornerSize(0.dp)
                                )
                            )
                    ) {
                        Text(
                            modifier = modifier
                                .align(Alignment.Center)
                                .padding(vertical = 8.dp)
                                .clickableSingle { cancelOnClick() },
                            text = "취소",
                            color = colors.ERROR,
                            style = typography.bodySmall
                        )
                    }
                    Box(
                        modifier = modifier
                            .clip(
                                MaterialTheme.shapes.medium.copy(
                                    bottomStart = CornerSize(0.dp),
                                    bottomEnd = CornerSize(12.dp),
                                    topStart = CornerSize(0.dp),
                                    topEnd = CornerSize(0.dp)
                                )
                            )
                            .background(
                                color = colors.WHITE
                            )
                            .weight(1f),
                    ) {
                        Text(
                            modifier = modifier
                                .align(Alignment.Center)
                                .padding(vertical = 8.dp)
                                .clickableSingle { checkOnClick() },
                            text = "확인",
                            color = colors.MAIN,
                            style = typography.bodySmall,
                        )
                    }
                }
            }
        }
    }
}