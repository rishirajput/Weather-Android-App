package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rishirajput.weather.presentation.ui.theme.onSurfaceColor

/**
 * Composable function to display placeholder text.
 *
 * @param placeholderText The text to display as a placeholder.
 */
@Composable
fun PlaceHolderText(placeholderText: String) {
    Text(
        text = placeholderText,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = onSurfaceColor
        )
    )
}

/**
 * Preview function for PlaceHolderText composable.
 */
@Preview(showBackground = true)
@Composable
fun PlaceHolderTextPreview() {
    PlaceHolderText(placeholderText = "Search Location")
}