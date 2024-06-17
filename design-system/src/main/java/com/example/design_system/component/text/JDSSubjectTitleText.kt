package com.example.design_system.component.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.JusiCoolAndroidTheme

@Composable
fun JDSSubjectTitleText(
    modifier: Modifier = Modifier,
    subjectText: String,
) {
    JusiCoolAndroidTheme { colors, typography ->
        Column {
            Text(
                text = subjectText,
                color = colors.Black,
                style = typography.subTitle,
            )
            Spacer(modifier = modifier.height(8.dp))
        }
    }
}