package com.example.design_system.icon_image.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jusiCool.`design-system`.R

@Composable
fun LeftArrowIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.left_arrow_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
    )
}

@Composable
fun ProfileIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.profile_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
    )
}

@Composable
fun RightArrowIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.right_arrow_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
    )
}

@Composable
fun SearchIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.search_icon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
    )
}