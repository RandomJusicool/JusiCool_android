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
import com.jusiCool.domain.model.board.response.GetCommunityBoardListResponseModel
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
    data: GetCommunityBoardListResponseModel,
    onClick: (Long) -> Unit
    ) {
    JusiCoolAndroidTheme { colors, typography ->
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .clickableSingle { onClick(data.id) },
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
                    Text( // todo -> name
                        text = "",
                        style = typography.label,
                        color = colors.Black
                    )
                    Spacer(modifier = Modifier.padding(start = 4.dp))
                    RectangleIcon(tint = colors.GRAY100)
                    Spacer(modifier = Modifier.padding(start = 4.dp))
                    Text( // todo -> 날짜 및 시간 서버에서 오는 방식으로 수정
                        text = stringResource(
                            id = R.string.community_data,
                            data.createdAt
                        ),
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
                            text = data.likes.toString(),
                            style = typography.label,
                            color = colors.GRAY400
                        )
                        Spacer(modifier = Modifier.padding(start = 3.dp))
                        CommentIcon()
                        Spacer(modifier = Modifier.padding(start = 3.dp))
                        Text(
                            text = data.commentNum.toString(),
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
    CommunityListItem(data = GetCommunityBoardListResponseModel(
        id = 0,
        title = "커뮤니티커뮤니티커뮤니티커뮤니티커뮤니티커뮤니티커뮤니티커뮤니티",
        content = "커뮤니티커뮤니티커뮤니티커뮤니티커뮤니티커뮤니티커뮤니티커뮤니티커뮤니커뮤니티티커뮤니티커뮤니티커뮤니티커뮤니티커뮤니티",
        likes = 12,
        commentNum = 12,
        createdAt = ""
    )) {
    }
}