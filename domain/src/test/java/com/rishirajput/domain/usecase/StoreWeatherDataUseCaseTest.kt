package com.rishirajput.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.LocalStorageRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Test

class StoreWeatherDataUseCaseTest {

    private val localStorageRepository: LocalStorageRepository = mockk(relaxed = true)
    private val storeWeatherDataUseCase = StoreWeatherDataUseCase(localStorageRepository)

    @Test
    fun `invoke should call storeWeatherData on repository`() = runBlocking<Unit> {
        // Given
        val weatherData = WeatherData(
            locationName = "Hyderabad",
            temperature = 31.0,
            condition = "Patchy rain nearby",
            icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
            humidity = 76,
            uvIndex = 4.0,
            feelsLike = 30.4
        )

        // When
        storeWeatherDataUseCase(weatherData)

        // Then
        coVerify { localStorageRepository.storeWeatherData(weatherData) }
    }

    @Test
    fun `invoke should handle repository exception`() = runBlocking<Unit> {
        // Given
        val weatherData = WeatherData(
            locationName = "Hyderabad",
            temperature = 31.0,
            condition = "Patchy rain nearby",
            icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
            humidity = 76,
            uvIndex = 4.0,
            feelsLike = 30.4
        )
        coEvery { localStorageRepository.storeWeatherData(any()) } throws RuntimeException("Repository error")

        // When & Then
        assertThrows(RuntimeException::class.java) {
            runBlocking {
                storeWeatherDataUseCase(weatherData)
            }
        }
    }

    @Test
    fun `invoke should handle different weather data`() = runBlocking<Unit> {
        // Given
        val weatherData = WeatherData(
            locationName = "New York",
            temperature = 22.0,
            condition = "Sunny",
            icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
            humidity = 50,
            uvIndex = 5.0,
            feelsLike = 21.0
        )

        // When
        storeWeatherDataUseCase(weatherData)

        // Then
        coVerify { localStorageRepository.storeWeatherData(weatherData) }
    }
}