package com.khalore.snapwords.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xffc8c8c8), // Chinese Silver
    secondary = Color(0xffffffff), // White
    background = Color(0xff141414), // Sooty Black
    surface = Color(0xff282828), // Dire Wolf Black
    tertiary = Color(0xff333333), // Tertiary color
    onPrimary = Color(0xff141414), // Sooty Black Text
    onSecondary = Color(0xff141414), // Sooty Black Text
    onBackground = Color(0xffc8c8c8), // Chinese Silver Text
    onSurface = Color(0xffc8c8c8), // Chinese Silver Text
    secondaryContainer = Color(0xff5174e2)
)


private val LightColorScheme = lightColorScheme(
    primary = Color(0xff141414), // Sooty Black
    secondary = Color(0xff282828), // Dire Wolf Black
    background = Color(0xffffffff), // White
    surface = Color(0xffc8c8c8), // Chinese Silver
    tertiary = Color(0xff999999), // Tertiary color
    onPrimary = Color(0xffffffff), // White Text
    onSecondary = Color(0xffffffff), // White Text
    onBackground = Color(0xff141414), // Sooty Black Text
    onSurface = Color(0xff141414), // Sooty Black Text
    secondaryContainer = Color(0xff5174e2)
)

@Composable
fun SnapWordsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}