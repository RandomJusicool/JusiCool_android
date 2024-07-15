package com.jusiCool.presentation.communityList.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.clickableSingle.clickableSingle
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.jusiCool.domain.model.community.response.GetCommunityListResponseModel
import com.jusiCool.presentation.R

@Composable
fun CommunityMainListItem(
    modifier: Modifier = Modifier,
    data: GetCommunityListResponseModel,
    navigateToCommunity: (Long, String) -> Unit
) {
    JusiCoolAndroidTheme { colors, typography ->
        Spacer(modifier = modifier.height(8.dp))
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .clickableSingle { navigateToCommunity(data.id, data.name) },
            color = colors.WHITE,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = data.name,
                    style = typography.bodySmall,
                )
                Text(
                    text = stringResource(
                        id = R.string.community_count,
                        data.board_num
                    ),
                    style = typography.label,
                    color = colors.GRAY400
                )
            }
        }
    }
}

@Preview
@Composable
private fun Pre() {
//    CommunityMainListItem(
//        data = GetCommunityListResponseModel(
//            name = "마이크로소프트 커뮤니",
//            board_num = 123,
//            id = 0
//        ),
//        navigateToCommunity = {}
//    )
}