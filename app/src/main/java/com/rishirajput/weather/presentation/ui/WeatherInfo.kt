package com.rishirajput.weather.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rishirajput.domain.model.WeatherData
import highlightedBackGround

@Composable
fun WeatherInfo(weatherData: WeatherData, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .highlightedBackGround()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WeatherFieldInfo(label = "Humidity", value = "${weatherData.humidity}%")
        WeatherFieldInfo(label = "UV", value = "${weatherData.uvIndex}")
        WeatherFieldInfo(label = "Feels Like", value = "${weatherData.feelsLike}°")
    }
}