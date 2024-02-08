package fr.medicapp.medicapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val EUYellowColorShema = ThemeColorScheme(
    lightColorScheme = lightColorScheme(
        primary = EUYellow100,
        onPrimary = EUBlack100
    ),
    darkColorScheme = darkColorScheme(
        primary = EUYellow100,
        onPrimary = EUWhite100
    )
)

val EUPurpleColorShema = ThemeColorScheme(
    lightColorScheme = lightColorScheme(
        primary = EUPurple100,
        onPrimary = EUBlack100
    ),
    darkColorScheme = darkColorScheme(
        primary = EUPurple100,
        onPrimary = EUWhite100
    )
)

val EURedColorShema = ThemeColorScheme(
    lightColorScheme = lightColorScheme(
        primary = EURed100,
        onPrimary = EUBlack100
    ),
    darkColorScheme = darkColorScheme(
        primary = EURed100,
        onPrimary = EUWhite100
    )
)

@Composable
fun MedicAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    theme: ThemeColorScheme,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> theme.darkColorScheme
        else -> theme.lightColorScheme
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
