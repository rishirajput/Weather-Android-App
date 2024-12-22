package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rishirajput.weather.presentation.ui.theme.onSurfaceColor

@Composable
fun PlaceHolderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = onSurfaceColor
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PlaceHolderTextPreview() {
    PlaceHolderText(text = "Search Location")
}