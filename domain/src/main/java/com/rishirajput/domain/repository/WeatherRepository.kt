package com.rishirajput.domain.repository

import com.rishirajput.domain.model.Location
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.model.Result

interface WeatherRepository {
    suspend fun getWeatherData(query: String): Result<List<WeatherData>>
    suspend fun getLocations(query: String): Result<List<Location>>
    suspend fun getWeatherDataForLocation(locationName: String): Result<WeatherData>
}