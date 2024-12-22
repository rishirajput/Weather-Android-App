package com.rishirajput.weather.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.WeatherRepository

class FetchWeatherDataUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(query: String): List<WeatherData> {
        return repository.getWeatherData(query)
    }
}