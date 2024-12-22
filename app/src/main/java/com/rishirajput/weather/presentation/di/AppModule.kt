package com.rishirajput.weather.presentation.di

import com.rishirajput.weather.presentation.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { WeatherViewModel(get(), get(), get(), get()) }
}

