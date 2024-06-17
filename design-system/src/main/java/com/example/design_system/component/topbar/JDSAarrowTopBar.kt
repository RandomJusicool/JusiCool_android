package com.example.design_system.component.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.component.text.JDSSubjectTitleText
import com.example.design_system.theme.JusiCoolAndroidTheme

@Composable
fun JDSArrowTopBar(
    modifier: Modifier = Modifier,
    startIcon: @Composable () -> Unit,
    betweenText: String = "",
    endIcon: @Composable () -> Unit = { Spacer(modifier = Modifier.size(24.dp)) }
) {
    JusiCoolAndroidTheme { colors, typography ->
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
        ) {
            startIcon()
            JDSSubjectTitleText(subjectText = betweenText)
            endIcon()
        }
    }
}