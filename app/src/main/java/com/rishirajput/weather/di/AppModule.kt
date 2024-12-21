package com.rishirajput.weather.di

import com.rishirajput.weather.data.api.WeatherApiService
import com.rishirajput.weather.data.api.provideRetrofit
import com.rishirajput.weather.data.repository.RetrofitWeatherRepository
import com.rishirajput.weather.domain.repository.WeatherRepository
import com.rishirajput.weather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideRetrofit().create(WeatherApiService::class.java) }
    single<WeatherRepository> { RetrofitWeatherRepository(get()) }
    viewModel { WeatherViewModel(get()) }
}