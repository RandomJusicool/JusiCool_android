package com.example.design_system.component.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.icon_image.icon.ProfileIcon
import com.example.design_system.icon_image.icon.SearchIcon
import com.example.design_system.icon_image.image.LogoImage
import com.example.design_system.theme.JusiCoolAndroidTheme

@Composable
fun JDSMainTopBar(
    modifier: Modifier = Modifier,
    startIcon: @Composable () -> Unit,
    betweenIcon: @Composable () -> Unit = { Spacer(modifier = Modifier.size(24.dp)) },
    endIcon: @Composable () -> Unit = { Spacer(modifier = Modifier.size(24.dp)) },
) {
    JusiCoolAndroidTheme { _, _ ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                startIcon()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                betweenIcon()
                Spacer(modifier = Modifier.size(24.dp))
                endIcon()
            }
        }
    }
}

@Preview
@Composable
private fun JDSTopBarPreview() {
    Column {
        JDSMainTopBar(
            startIcon = { LogoImage() },
            betweenIcon = { SearchIcon() },
            endIcon = { ProfileIcon() }
        )
    }
}