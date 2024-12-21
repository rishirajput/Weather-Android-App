package com.rishirajput.weather.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.domain.model.WeatherData
import com.rishirajput.weather.presentation.theme.textStyleCurrentTemperature
import com.rishirajput.weather.presentation.theme.textStyleLoationName
import com.rishirajput.weather.presentation.theme.textStyleSearchCityLabel

@Composable
fun LocationDetail(weatherData: WeatherData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = weatherData.locationName,
            style = textStyleLoationName,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${weatherData.temperature}°",
            style = textStyleCurrentTemperature,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Condition: ${weatherData.condition}",
            style = textStyleSearchCityLabel,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Humidity: ${weatherData.humidity}%",
            style = textStyleSearchCityLabel,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "UV Index: ${weatherData.uvIndex}",
            style = textStyleSearchCityLabel,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLocationDetail() {
    val dummyWeatherData = WeatherData(
        locationName = "Airport Village",
        temperature = 27.3,
        condition = "Patchy rain nearby",
        icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
        humidity = 76,
        uvIndex = 0.1,
        feelsLike = 30.4
    )
    LocationDetail(weatherData = dummyWeatherData)
}