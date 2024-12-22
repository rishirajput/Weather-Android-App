package com.rishirajput.weather.presentation.di

import com.rishirajput.weather.presentation.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Define the app module for dependency injection
val appModule = module {
    // Provide the WeatherViewModel
    viewModel { WeatherViewModel(get(), get(), get(), get()) }
}