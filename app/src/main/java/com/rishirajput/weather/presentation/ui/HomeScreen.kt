// HomeScreen.kt
package com.rishirajput.weather.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.domain.model.WeatherData
import com.rishirajput.weather.presentation.theme.appBackgroundColor
import com.rishirajput.weather.presentation.ui.NoCitySelected
import com.rishirajput.weather.presentation.ui.SearchBar
import com.rishirajput.weather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val viewModel: WeatherViewModel = getViewModel()
    var query by remember { mutableStateOf("") }
    val weatherData by viewModel.weatherData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundColor)
            .padding(innerPadding)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        SearchBar(
            placeholderText = stringResource(id = R.string.search_location),
            modifier = Modifier.padding(4.dp),
            onSearch = { query = it; viewModel.fetchWeatherData(it) }
        )
        Spacer(modifier = Modifier.height(32.dp))
        weatherData?.let { data ->
            WeatherCard(data)
        } ?: NoCitySelected()
    }
}

@Composable
fun WeatherCard(weatherData: WeatherData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Temperature: ${weatherData.temperature}°C")
        Text(text = "Condition: ${weatherData.condition}")
        Text(text = "Humidity: ${weatherData.humidity}%")
        Text(text = "UV Index: ${weatherData.uvIndex}")
        Text(text = "Feels Like: ${weatherData.feelsLike}°C")
    }
}