package com.rishirajput.weather.domain.repository

import com.rishirajput.weather.domain.model.WeatherData

interface WeatherRepository {
    suspend fun getWeatherData(query: String): WeatherData
}