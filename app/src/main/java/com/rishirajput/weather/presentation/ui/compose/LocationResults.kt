package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.rishirajput.domain.model.WeatherData

/**
 * Composable function to display a list of location results.
 *
 * @param searchResults The list of weather data to display.
 * @param onLocationClick The action to perform when a location is clicked.
 * @param focusManager The focus manager to handle focus actions.
 * @param modifier The modifier to be applied to the composable.
 */
@Composable
fun LocationResults(
    searchResults: List<WeatherData>,
    onLocationClick: (WeatherData) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(searchResults) { weatherData ->
            LocationResultCard(
                weatherData = weatherData,
                onClick = {
                    onLocationClick(weatherData)
                    focusManager.clearFocus()
                }
            )
        }
    }
}