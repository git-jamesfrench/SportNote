package com.jamesfrench.sportnote.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1A34DA),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFC4CCFF),
    error = Color(0xFFD22B2B),

    surface = Color(0xFF1B1C23),
    surfaceTint = Color(0xFF34373F),
    onSurface = Color(0xFFFFFFFF),

    background = Color(0xFF121318),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1A34DA),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF182056),

    surface = Color(0xFFE1E1E8),
    surfaceTint = Color(0xFFF4F4FF),
    onSurface = Color(0xFF000000),

    background = Color(0xFFE6E7ED),

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


@Composable
fun SportNoteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typo,
        content = content
    )
}