package com.rishirajput.weather.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.weather.presentation.theme.textStyleCurrentTemperature
import com.rishirajput.weather.presentation.theme.textStyleLoationName
import highlightedBackGround
import androidx.compose.ui.Alignment
import com.rishirajput.weather.presentation.utils.updateImageSizeInUrl

@Composable
fun LocationResultCard(weatherData: WeatherData, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .highlightedBackGround()
            .clickable(onClick = onClick)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = weatherData.locationName,
                style = textStyleLoationName
            )
            Text(
                text = "${weatherData.temperature}°",
                style = textStyleCurrentTemperature,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            )
        }
        val painter = rememberAsyncImagePainter(model = updateImageSizeInUrl("https:${weatherData.icon}", 128))
        Image(
            painter = painter,
            contentDescription = "Weather Icon",
            modifier = Modifier
                .height(110.dp)
                .width(110.dp)
                .padding(bottom = 8.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationResult() {
    val dummyWeatherData = WeatherData(
        locationName = "Airport Village",
        temperature = 27.3,
        condition = "Patchy rain nearby",
        icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
        humidity = 76,
        uvIndex = 0.1,
        feelsLike = 30.4
    )
    LocationResultCard(weatherData = dummyWeatherData) {}
}