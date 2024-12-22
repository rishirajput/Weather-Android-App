package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rishirajput.weather.presentation.ui.utils.updateImageSizeInUrl

/**
 * Composable function to display the weather status icon.
 *
 * @param iconUrl The URL of the weather icon.
 * @param modifier The modifier to be applied to the composable.
 */
@Composable
fun WeatherStatusIcon(iconUrl: String, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(model = updateImageSizeInUrl("https:$iconUrl", 128))
    Image(
        painter = painter,
        contentDescription = "Weather Icon",
        contentScale = ContentScale.Fit,
        modifier = modifier
            .height(128.dp)
            .width(128.dp)
    )
}

/**
 * Preview function for WeatherStatusIcon composable.
 */
@Preview(showBackground = true)
@Composable
fun PreviewWeatherStatusIcon() {
    WeatherStatusIcon(iconUrl = "//cdn.weatherapi.com/weather/64x64/night/176.png")
}