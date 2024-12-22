package com.rishirajput.weather.presentation.ui

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.rishirajput.domain.errors.InvalidCityException
import com.rishirajput.domain.errors.NoNetworkException
import com.rishirajput.weather.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ErrorSnackBar(snackBarHostState: SnackbarHostState, errorFlow: Flow<Throwable>) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        errorFlow.collectLatest { error ->
            val errorMessage = when (error) {
                is NullPointerException -> context.getString(R.string.not_found_error)
                is NoNetworkException -> context.getString(R.string.network_error)
                is InvalidCityException -> context.getString(R.string.invalid_city_name)
                else -> error.localizedMessage ?: "An unexpected error occurred"
            }
            snackBarHostState.showSnackbar(errorMessage)
        }
    }
}