package com.rishirajput.weather.data.repository

import com.rishirajput.weather.data.api.WeatherApiService
import com.rishirajput.weather.domain.model.WeatherData
import com.rishirajput.weather.domain.repository.WeatherRepository

class RetrofitWeatherRepository(private val apiService: WeatherApiService) : WeatherRepository {
    override suspend fun getWeatherData(query: String): WeatherData {
        val response = apiService.getCurrentWeather("YOUR_API_KEY", query)
        val current = response.current
        return WeatherData(
            temperature = current.temp_c,
            condition = current.condition.text,
            iconUrl = current.condition.icon,
            humidity = current.humidity,
            uvIndex = current.uv,
            feelsLike = current.feelslike_c
        )
    }
}