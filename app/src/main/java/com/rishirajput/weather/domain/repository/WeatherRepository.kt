package com.rishirajput.weather.domain.repository

import com.rishirajput.weather.data.api.LocationResponse
import com.rishirajput.weather.domain.model.WeatherData

interface WeatherRepository {
    suspend fun getWeatherData(query: String): List<WeatherData>
    suspend fun getLocations(query: String): List<LocationResponse>
    suspend fun getWeatherDataForLocation(locationName: String): WeatherData
}