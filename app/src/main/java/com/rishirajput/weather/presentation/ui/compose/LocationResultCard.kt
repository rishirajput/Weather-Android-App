package com.rishirajput.weather.presentation.ui.compose

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.weather.presentation.ui.theme.textStyleCurrentTemperature
import com.rishirajput.weather.presentation.ui.theme.textStyleLocationName

/**
 * Composable function to display a card with weather information for a location.
 *
 * @param weatherData The weather data to display.
 * @param onClick The action to perform when the card is clicked.
 */
@Composable
fun LocationResultCard(weatherData: WeatherData, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .highLightedBackGround()
            .clickable(onClick = onClick)
            .padding(start = 30.dp, top = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = weatherData.locationName,
                style = textStyleLocationName
            )
            CurrentTemperature(
                temperature = weatherData.temperature,
                modifier = Modifier.align(Alignment.Start),
                textStyle = textStyleCurrentTemperature
            )
        }
        WeatherStatusIcon(
            iconUrl = weatherData.icon,
            modifier = Modifier
                .height(110.dp)
                .width(110.dp)
                .padding(bottom = 8.dp)
        )
    }
}

/**
 * Preview function for LocationResultCard composable.
 */
@Preview(showBackground = true)
@Composable
fun PreviewLocationResult() {
    val dummyWeatherData = WeatherData(
        locationName = "YoriCostio (La Villita)",
        temperature = 27.3,
        condition = "Patchy rain nearby",
        icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
        humidity = 76,
        uvIndex = 0.1,
        feelsLike = 30.4
    )
    LocationResultCard(weatherData = dummyWeatherData) {}
}