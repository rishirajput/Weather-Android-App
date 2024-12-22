package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rishirajput.weather.presentation.ui.theme.textStyleMedium
import com.rishirajput.weather.presentation.ui.theme.textStyleSmall

/**
 * Composable function to display weather field information.
 *
 * @param label The label for the weather field.
 * @param value The value for the weather field.
 */
@Composable
fun WeatherFieldInfo(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = textStyleSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = value,
            style = textStyleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

/**
 * Preview function for WeatherFieldInfo composable.
 */
@Preview(showBackground = true)
@Composable
fun PreviewWeatherFieldInfo() {
    WeatherFieldInfo(label = "Humidity", value = "76%")
}