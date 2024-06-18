package com.example.design_system.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.design_system.R

object JDSTypography {
    private val pretendard = FontFamily(
        Font(R.font.pretendard),
        Font(R.font.pretendard_regular),
        Font(R.font.pretendard_semi_bold)
    )

    @Stable
    val titleLarge = TextStyle(
        fontFamily = pretendard,
        fontSize = 48.sp,
        lineHeight = 58.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )

    @Stable
    val titleMedium = TextStyle(
        fontFamily = pretendard,
        fontSize = 36.sp,
        lineHeight = 43.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )

    @Stable
    val titleSmall = TextStyle(
        fontFamily = pretendard,
        fontSize = 24.sp,
        lineHeight = 31.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )

    @Stable
    val subTitle = TextStyle(
        fontFamily = pretendard,
        fontSize = 18.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )

    @Stable
    val bodyLarge = TextStyle(
        fontFamily = pretendard,
        fontSize = 18.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
    )

    @Stable
    val bodyMedium = TextStyle(
        fontFamily = pretendard,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )

    @Stable
    val bodySmall = TextStyle(
        fontFamily = pretendard,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
    )

    @Stable
    val label = TextStyle(
        fontFamily = pretendard,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
    )
}