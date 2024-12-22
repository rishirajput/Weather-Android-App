package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.ui.theme.textStyleCityName
import com.rishirajput.weather.presentation.ui.theme.textStyleSearchCityLabel

/**
 * Composable function to display a message when no city is selected.
 */
@Composable
fun NoCitySelected(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.no_city_selected),
                style = textStyleCityName,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.please_search_for_a_city),
                style = textStyleSearchCityLabel,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Preview function for NoCitySelected composable.
 */
@Preview(showBackground = true)
@Composable
fun NoCitySelectedPreview() {
    NoCitySelected()
}