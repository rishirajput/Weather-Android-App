package com.rishirajput.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.LocalStorageRepository

class StoreWeatherDataUseCase(private val localStorageRepository: LocalStorageRepository) {
    suspend operator fun invoke(data: WeatherData) {
        localStorageRepository.storeWeatherData(data)
    }

    suspend fun getSelectedWeatherData(): WeatherData? {
        return localStorageRepository.getWeatherData()
    }
}