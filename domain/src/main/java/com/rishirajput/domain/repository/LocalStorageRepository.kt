package com.rishirajput.domain.repository

import com.rishirajput.domain.model.WeatherData

interface LocalStorageRepository {
    suspend fun storeWeatherData(weatherData: WeatherData)
}