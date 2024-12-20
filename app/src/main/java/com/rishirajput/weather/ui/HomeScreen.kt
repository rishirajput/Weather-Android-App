package com.rishirajput.weather.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.theme.appBackgroundColor

@Composable
fun HomeScreen(innerPadding: PaddingValues)  {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundColor) // Light gray background for the screen
            .padding(innerPadding)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        SearchBar(
            placeholderText = stringResource(id = R.string.search_location),
            modifier = Modifier.padding(4.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        NoCitySelected()
    }
}



@Preview(showBackground = true)
@Composable
fun HomeEmptyStatePreview() {
    HomeScreen(innerPadding = PaddingValues(0.dp))
}