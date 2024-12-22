package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.weather.presentation.ui.theme.highLightedBackGround

/**
 * Composable function to display weather information.
 *
 * @param weatherData The weather data to display.
 * @param modifier The modifier to be applied to the composable.
 */
@Composable
fun WeatherInfo(weatherData: WeatherData, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .highLightedBackGround()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WeatherFieldInfo(label = "Humidity", value = "${weatherData.humidity}%")
        WeatherFieldInfo(label = "UV", value = "${weatherData.uvIndex}")
        WeatherFieldInfo(label = "Feels Like", value = "${weatherData.feelsLike}°")
    }
}

/**
 * Preview function for WeatherInfo composable.
 */
@Preview(showBackground = true)
@Composable
fun PreviewWeatherInfo() {
    val dummyWeatherData = WeatherData(
        locationName = "Hyderabad",
        temperature = 31.0,
        condition = "Patchy rain nearby",
        icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
        humidity = 76,
        uvIndex = 4.0,
        feelsLike = 30.4
    )
    WeatherInfo(weatherData = dummyWeatherData)
}