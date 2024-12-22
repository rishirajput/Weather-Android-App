package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.rishirajput.domain.model.WeatherData

@Composable
fun LocationResults(
    searchResults: List<WeatherData>,
    onLocationClick: (WeatherData) -> Unit,
    focusManager: FocusManager
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(searchResults) { data ->
            LocationResultCard(data, onClick = {
                onLocationClick(data)
                focusManager.clearFocus()
            })
        }
    }
}