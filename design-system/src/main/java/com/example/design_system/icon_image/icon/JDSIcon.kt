package com.example.design_system.icon_image.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.design_system.R

@Composable
fun LeftArrowIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.left_arrow_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = tint
    )
}

@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.profile_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = tint
    )
}

@Composable
fun RightArrowIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.right_arrow_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = tint
    )
}

@Composable
fun SearchIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.search_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = tint
    )
}

@Composable
fun RightChevronIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.right_chevron_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = tint
    )
}

@Composable
fun RectangleIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painterResource(id = R.drawable.rectangle),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = tint
    )
}

@Composable
fun HeartIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painterResource(id = R.drawable.heart_streamline_core_1),
        contentDescription = null,
        modifier = modifier.size(16.dp),
        tint = tint
    )
}

@Composable
fun CommentIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.jusicool_comment),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = tint
    )
}

@Composable
fun PencilIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.pencil),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = tint
    )
}

@Composable
fun GraphIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.graph_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = tint
    )
}