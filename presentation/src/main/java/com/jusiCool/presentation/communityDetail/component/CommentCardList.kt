package com.jusiCool.presentation.communityDetail.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JusiCoolAndroidTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun CommentCardList(
    modifier: Modifier = Modifier,
    data: ImmutableList<TemCommentData> = persistentListOf()
) {
    JusiCoolAndroidTheme { _, _ ->
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 24.dp)
                .heightIn(max = 10000.dp)
        ) {
            itemsIndexed(data) { _, item ->
                CommentCard(data = item)
            }
        }
    }
}