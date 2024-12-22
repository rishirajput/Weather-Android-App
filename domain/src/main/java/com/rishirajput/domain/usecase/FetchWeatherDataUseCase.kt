package com.rishirajput.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.WeatherRepository
import com.rishirajput.domain.model.Result

class FetchWeatherDataUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(query: String): Result<List<WeatherData>> {
        return repository.getWeatherData(query)
    }
}