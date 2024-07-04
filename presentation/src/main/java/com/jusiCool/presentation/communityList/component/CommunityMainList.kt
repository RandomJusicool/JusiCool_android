package com.jusiCool.presentation.communityList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.jusiCool.domain.model.community.response.GetCommunityListResponseModel

data class TemList(
    val company: String,
    val count: Int
)

@Composable
fun CommunityMainList(
    modifier: Modifier = Modifier,
    data: List<GetCommunityListResponseModel> = listOf(),
    navigateToCommunity: () -> Unit
) {
    JusiCoolAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.GRAY50)
                .padding(
                    horizontal = 24.dp,
                    vertical = 8.dp
                )
        ) {
            itemsIndexed(data) {_, item ->
                CommunityMainListItem(
                    data = item,
                    onClick = navigateToCommunity
                )
            }
        }
    }
}

@Preview
@Composable
private fun CommunityMainListPre() {
    CommunityMainList{}
}
