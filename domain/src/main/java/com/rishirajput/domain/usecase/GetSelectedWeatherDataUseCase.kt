package com.rishirajput.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.LocalStorageRepository

class GetSelectedWeatherDataUseCase(private val localStorageRepository: LocalStorageRepository) {
    suspend operator fun invoke(): WeatherData? {
        return localStorageRepository.getWeatherData()
    }
}