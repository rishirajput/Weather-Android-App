package com.rishirajput.weather.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rishirajput.weather.presentation.utils.updateImageSizeInUrl

@Composable
fun WeatherStatusIcon(iconUrl: String, modifier: Modifier) {
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