package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.ui.theme.appBackgroundColor
import com.rishirajput.weather.presentation.ui.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val viewModel: WeatherViewModel = getViewModel()
    val query by viewModel.query.collectAsState()
    val weatherData by viewModel.searchResults.collectAsState()
    val selectedWeatherData by viewModel.selectedWeatherData.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundColor)
            .padding(innerPadding)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        SearchBar(
            query,
            placeholderText = stringResource(id = R.string.search_location),
            modifier = Modifier.padding(4.dp),
            onSearch = { viewModel.fetchWeatherData(it) }
        )
        Spacer(modifier = Modifier.height(32.dp))
        if (selectedWeatherData != null && query.isEmpty()) {
            LocationDetail(weatherData = selectedWeatherData!!)
        } else if (weatherData.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(weatherData) { data ->
                    LocationResultCard(data, onClick = {
                        viewModel.selectWeatherData(data)
                        focusManager.clearFocus()
                    })
                }
            }
        } else if(query.isEmpty()) {
            NoCitySelected()
        }
    }
}