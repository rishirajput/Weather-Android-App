package com.rishirajput.domain.repository

import com.rishirajput.domain.model.Location
import com.rishirajput.domain.model.WeatherData

interface WeatherRepository {
    suspend fun getWeatherData(query: String): List<WeatherData>
    suspend fun getLocations(query: String): List<Location>
    suspend fun getWeatherDataForLocation(locationName: String): WeatherData
}