package com.jusiCool.presentation.community.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.icon_image.icon.CommentIcon
import com.example.design_system.icon_image.icon.HeartIcon
import com.example.design_system.icon_image.icon.RectangleIcon
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.jusiCool.presentation.R

data class CommunityListItemTemData(
    val title: String,
    val content: String,
    val name: String,
    val started_date: String,
    val started_time: String,
    val heart_count: Int,
    val comment_count: Int
)

@Composable
internal fun CommunityListItem(
    modifier: Modifier = Modifier,
    data: CommunityListItemTemData,
    onClick: () -> Unit
    ) {
    JusiCoolAndroidTheme { colors, typography ->
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .clickableSingle { onClick() },
            shape = RoundedCornerShape(12.dp),
            color = colors.WHITE
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = data.title,
                    style = typography.bodyMedium,
                    color = colors.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Text(
                    text = data.content,
                    style = typography.label,
                    color = colors.GRAY600,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = data.name,
                        style = typography.label,
                        color = colors.Black
                    )
                    Spacer(modifier = Modifier.padding(start = 4.dp))
                    RectangleIcon(tint = colors.GRAY100)
                    Spacer(modifier = Modifier.padding(start = 4.dp))
                    Text(
                        text = stringResource(
                            id = R.string.community_data,
                            data.started_date
                        ),
                        style = typography.label,
                        color = colors.GRAY400
                    )
                    Spacer(modifier = Modifier.padding(start = 4.dp))
                    Text(
                        text = data.started_time,
                        style = typography.label,
                        color = colors.GRAY400
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HeartIcon()
                        Spacer(modifier = Modifier.padding(start = 4.dp))
                        Text(
                            text = data.heart_count.toString(),
                            style = typography.label,
                            color = colors.GRAY400
                        )
                        Spacer(modifier = Modifier.padding(start = 3.dp))
                        CommentIcon()
                        Spacer(modifier = Modifier.padding(start = 3.dp))
                        Text(
                            text = data.comment_count.toString(),
                            style = typography.label,
                            color = colors.GRAY400
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommunityListItemPre() {
    CommunityListItem(
        data = CommunityListItemTemData(
            title = "커뮤니티는공통의관심사목표가치혹은지리적커뮤니티는공통의관심사목표가치혹은지리적",
            content = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인",
            name = "이명훈",
            started_date = "06.20",
            started_time = "17:06",
            heart_count = 12,
            comment_count = 13
        )
    ) {
        
    }
}