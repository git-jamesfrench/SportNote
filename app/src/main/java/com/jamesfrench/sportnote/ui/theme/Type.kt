package com.jamesfrench.sportnote.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typo = Typography(
    displayLarge = TextStyle(
        fontFamily = fontJost,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val TrainingTypo = TextStyle(
    fontFamily = fontInter,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    letterSpacing = (-1).sp,
    fontFeatureSettings = "ss01"
)