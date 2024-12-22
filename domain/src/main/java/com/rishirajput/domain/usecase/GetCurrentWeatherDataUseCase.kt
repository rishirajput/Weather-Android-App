package com.rishirajput.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.WeatherRepository
import com.rishirajput.domain.model.Result


class GetCurrentWeatherDataUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(weatherData: WeatherData?): Result<WeatherData>? {
        return weatherData?.let {
            weatherRepository.getWeatherDataForLocation(it.locationName)
        }
    }
}