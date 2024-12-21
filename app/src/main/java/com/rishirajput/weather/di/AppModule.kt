package com.rishirajput.weather.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rishirajput.weather.data.api.WeatherApiService
import com.rishirajput.weather.data.repository.RetrofitWeatherRepository
import com.rishirajput.weather.domain.repository.WeatherRepository
import com.rishirajput.weather.presentation.viewmodel.WeatherViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

fun provideRetrofit(): Retrofit {
    val contentType = "application/json".toMediaType()
    val json = Json { ignoreUnknownKeys = true }

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .client(client)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}

val appModule = module {
    single { provideRetrofit().create(WeatherApiService::class.java) }
    single<WeatherRepository> { RetrofitWeatherRepository(get()) }
    viewModel { WeatherViewModel(get()) }
}