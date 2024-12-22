package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.ui.ErrorSnackBar
import com.rishirajput.weather.presentation.ui.theme.appBackgroundColor
import com.rishirajput.weather.presentation.ui.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel

/**
 * Composable function to display the home screen of the weather app.
 *
 * @param innerPadding The padding values to be applied to the screen.
 */
@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val viewModel: WeatherViewModel = getViewModel()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val storedWeatherData by viewModel.storedWeatherData.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val focusManager = LocalFocusManager.current
    val snackBarHostState = remember { SnackbarHostState() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundColor)
            .padding(innerPadding)
    ) {
        HomeScreenContent(
            searchQuery = searchQuery,
            searchResults = searchResults,
            storedWeatherData = storedWeatherData,
            isLoading = isLoading,
            onSearch = { viewModel.fetchWeatherData(it) },
            onLocationClick = { data -> viewModel.selectWeatherData(data) },
            focusManager = focusManager
        )
        SnackbarHost(
            hostState = snackBarHostState, modifier = Modifier.align(Alignment.BottomCenter)
        )
        ErrorSnackBar(snackBarHostState = snackBarHostState, errorFlow = viewModel.errorFlow)
    }
}

/**
 * Composable function to display the content of the home screen.
 *
 * @param searchQuery The current search query.
 * @param searchResults The list of search results.
 * @param storedWeatherData The stored weather data.
 * @param isLoading A boolean indicating if the data is loading.
 * @param onSearch A lambda function to handle search actions.
 * @param onLocationClick A lambda function to handle location click actions.
 * @param focusManager The focus manager to handle focus actions.
 */
@Composable
fun HomeScreenContent(
    searchQuery: String,
    searchResults: List<WeatherData>,
    storedWeatherData: WeatherData?,
    isLoading: Boolean,
    onSearch: (String) -> Unit,
    onLocationClick: (WeatherData) -> Unit,
    focusManager: FocusManager
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        SearchBar(
            query = searchQuery,
            placeholderText = stringResource(id = R.string.search_location),
            modifier = Modifier.padding(4.dp),
            onSearch = onSearch
        )
        Spacer(modifier = Modifier.height(32.dp))
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            when {
                storedWeatherData != null && searchQuery.isEmpty() -> {
                    LocationDetail(weatherData = storedWeatherData)
                }

                searchResults.isNotEmpty() -> {
                    LocationResults(
                        searchResults = searchResults,
                        onLocationClick = onLocationClick,
                        focusManager = focusManager
                    )
                }

                searchQuery.isEmpty() -> {
                    NoCitySelected()
                }
            }
        }
    }
}