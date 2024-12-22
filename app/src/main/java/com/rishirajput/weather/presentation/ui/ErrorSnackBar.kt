package com.rishirajput.weather.presentation.ui

import android.util.Log
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
                is NoNetworkException -> context.getString(R.string.network_error)
                is InvalidCityException -> context.getString(R.string.invalid_city_name)
                else -> "".also { Log.e("Exception", "Error: $error") }
            }
            if (errorMessage.isNotEmpty())
            snackBarHostState.showSnackbar(errorMessage)
        }
    }
}