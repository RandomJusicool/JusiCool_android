package com.jusiCool.presentation.communityDetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JusiCoolAndroidTheme

data class TemCommentData(
    val name: String,
    val comment: String
)

@Composable
fun CommentCard(
    modifier: Modifier = Modifier,
    data: TemCommentData
) {
    JusiCoolAndroidTheme { colors, typography ->
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ){
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = data.name,
                style = typography.bodySmall
            )
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = data.comment,
                style = typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun CommentCardPre() {
    CommentCard(data = TemCommentData(
        name = "o0뀨0o",
        comment = "커뮤니티는공통의관심사목표가치혹은지리적위치를공유하는사람들로이루어진집단입니다이러한집단은개인들이소속감을느끼고상호작용하며협력하는장소로서중요한역할을합니다커뮤니티는온라인과오프라인에서모두존재할"
    ))
}