package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rishirajput.weather.presentation.ui.theme.textStyleMedium
import com.rishirajput.weather.presentation.ui.theme.textStyleSmall

@Composable
fun WeatherFieldInfo(label: String, value: String) {
    Column {
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

@Preview(showBackground = true)
@Composable
fun PreviewWeatherFieldInfo() {
    WeatherFieldInfo(label = "Humidity", value = "76%")
}