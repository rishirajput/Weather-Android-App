package com.rishirajput.weather.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.domain.model.WeatherData
import highlightedBackGround

@Composable
fun LocationDetail(weatherData: WeatherData) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(56.dp)
    ) {
        WeatherStatusIcon(iconUrl = weatherData.icon, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(11.dp))
        LocationName(locationName = weatherData.locationName, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .highlightedBackGround()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherFieldInfo(label = "Humidity", value = "${weatherData.humidity}%")
            WeatherFieldInfo(label = "UV", value =  "${weatherData.uvIndex}")
            WeatherFieldInfo(label = "Feels Like", value = "${weatherData.feelsLike}°")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationDetail() {
    val dummyWeatherData = WeatherData(
        locationName = "Hyderabad",
        temperature = 31.0,
        condition = "Patchy rain nearby",
        icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
        humidity = 76,
        uvIndex = 4.0,
        feelsLike = 30.4
    )
    LocationDetail(weatherData = dummyWeatherData)
}