package com.rishirajput.weather.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.theme.appBackgroundColor
import com.rishirajput.weather.presentation.ui.NoCitySelected
import com.rishirajput.weather.presentation.ui.SearchBar
import com.rishirajput.weather.presentation.ui.LocationResultCard
import com.rishirajput.weather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

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
        if (weatherData.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(weatherData) { data ->
                    LocationResultCard(data)
                }
            }
        } else {
            NoCitySelected()
        }
    }
}