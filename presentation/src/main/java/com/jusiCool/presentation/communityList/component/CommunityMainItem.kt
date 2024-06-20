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
import com.jusiCool.presentation.R

data class TemList(
    val company: String,
    val count: Int
)

@Composable
fun CommunityMainListItem(
    modifier: Modifier = Modifier,
    data: TemList,
    onClick: () -> Unit
) {
    JusiCoolAndroidTheme { colors, typography ->
        Spacer(modifier = modifier.height(30.dp))
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .clickableSingle { onClick() },
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
                    text = data.company,
                    style = typography.bodySmall,
                )
                Text(
                    text = stringResource(
                        id = R.string.community_count,
                        data.count
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
    CommunityMainListItem(
        data = TemList(
            company = "마이크로소프트 커뮤니티",
            count = 123,
        ),
        onClick = {}
    )
}