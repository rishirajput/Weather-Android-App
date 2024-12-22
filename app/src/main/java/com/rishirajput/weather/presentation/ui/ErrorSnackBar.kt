package com.rishirajput.weather.presentation.ui

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ErrorSnackBar(snackBarHostState: SnackbarHostState, errorFlow: Flow<String>) {
    LaunchedEffect(Unit) {
        errorFlow.collectLatest { errorMessage ->
            snackBarHostState.showSnackbar(errorMessage)
        }
    }
    SnackbarHost(hostState = snackBarHostState)
}