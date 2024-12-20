package com.rishirajput.weather.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.rishirajput.weather.R

val onSurfaceColor = Color(0xFFC4C4C4)
val highLightColor = Color(0xFFF2F2F2)
val appFontColor = Color(0xFF2C2C2C)

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_thin, FontWeight.Thin),
    Font(R.font.poppins_extra_light, FontWeight.ExtraLight),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(R.font.poppins_black, FontWeight.Black),
    Font(R.font.poppins_thin_italic, FontWeight.Thin),
    Font(R.font.poppins_extra_light_italics, FontWeight.ExtraLight),
    Font(R.font.poppins_light_italic, FontWeight.Light),
    Font(R.font.poppins_italic, FontWeight.Normal),
    Font(R.font.poppins_medium_italic, FontWeight.Medium),
    Font(R.font.poppins_semi_bold_italic, FontWeight.SemiBold),
    Font(R.font.poppins_bold_italic, FontWeight.Bold),
    Font(R.font.poppins_extra_bold_italic, FontWeight.ExtraBold),
    Font(R.font.poppins_black_italic, FontWeight.Black)
)

val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 22.5.sp,
        textAlign = TextAlign.Start,
    ),
    headlineMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
)

val textStyleNoCitySelected = TextStyle(
    fontFamily = poppinsFontFamily,
    fontSize = 30.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 45.sp,
    textAlign = TextAlign.Center,
    color = appFontColor
)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    onSurface = onSurfaceColor,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    onSurface = onSurfaceColor,
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
fun WeatherTheme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}