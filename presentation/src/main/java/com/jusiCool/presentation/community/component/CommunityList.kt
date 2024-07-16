package com.jusiCool.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.component.modifier.padding.paddingHorizontal
import com.example.design_system.theme.JusiCoolAndroidTheme
import com.jusiCool.domain.model.board.response.GetCommunityBoardListResponseModel

@Composable
fun CommunityList(
    modifier: Modifier = Modifier,
    id: String,
    name: String,
    data: List<GetCommunityBoardListResponseModel> = listOf(),
    navigateToDetailCommunity: (String, String, String) -> Unit
) {
    JusiCoolAndroidTheme { colors, _ ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.GRAY50)
                .paddingHorizontal(horizontal = 24.dp, bottom = 8.dp)
        ) {
            itemsIndexed(data) {_, item ->
                CommunityListItem(
                    data = item,
                    onClick = navigateToDetailCommunity,
                    id = id,
                    name = name
                )
            }
        }
    }
}

@Preview
@Composable
private fun CommunityListPre() {

}