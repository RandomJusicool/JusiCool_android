package com.example.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import com.example.design_system.theme.color.ColorTheme
import com.example.design_system.theme.color.JDSColor

@Composable
fun JusiCoolAndroidTheme(
     colors: ColorTheme = JDSColor,
     typography: JDSTypography = JDSTypography,
    content: @Composable (colors: ColorTheme, typography: JDSTypography) -> Unit
) {
    content(colors, typography)
}