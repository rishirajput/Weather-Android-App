package com.rishirajput.weather.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.rishirajput.weather.R

// Define the Poppins font family
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

// Define the typography for the app
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

// City name text style
val textStyleCityName = TextStyle(
    fontFamily = poppinsFontFamily,
    fontSize = 30.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 45.sp,
    textAlign = TextAlign.Center,
    color = appFontColor
)

// Search city label text style
val textStyleSearchCityLabel = TextStyle(
    fontFamily = poppinsFontFamily,
    fontSize = 15.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 22.5.sp,
    textAlign = TextAlign.Center,
    color = appFontColor
)

// Location name text style
val textStyleLocationName = TextStyle(
    fontFamily = poppinsFontFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 30.sp,
    textAlign = TextAlign.Center
)

// Current temperature text style
val textStyleCurrentTemperature = TextStyle(
    fontFamily = poppinsFontFamily,
    fontSize = 60.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 90.sp,
    textAlign = TextAlign.Left,
)

// Large temperature text style
val textStyleTemperatureLarge = TextStyle(
    fontFamily = poppinsFontFamily,
    fontSize = 70.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 105.sp,
    textAlign = TextAlign.Center,
)

// Medium text style
val textStyleMedium = TextStyle(
    fontFamily = poppinsFontFamily,
    fontSize = 15.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 22.5.sp,
    textAlign = TextAlign.Center,
    color = appFontColorHeavy
)

// Small text style
val textStyleSmall = TextStyle(
    fontFamily = poppinsFontFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 18.sp,
    textAlign = TextAlign.Center,
    color = onSurfaceColor
)