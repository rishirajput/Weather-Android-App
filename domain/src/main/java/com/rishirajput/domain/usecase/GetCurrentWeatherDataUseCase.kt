package com.rishirajput.weather.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.WeatherRepository

class GetCurrentWeatherDataUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(weatherData: WeatherData?): WeatherData? {
        return weatherData?.let {
            weatherRepository.getWeatherDataForLocation(it.locationName)
        }
    }
}