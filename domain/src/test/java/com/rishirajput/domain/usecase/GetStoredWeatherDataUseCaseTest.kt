package com.rishirajput.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.LocalStorageRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GetStoredWeatherDataUseCaseTest {

    private val localStorageRepository: LocalStorageRepository = mockk()
    private val getStoredWeatherDataUseCase = GetStoredWeatherDataUseCase(localStorageRepository)

    @Test
    fun `invoke should return weather data from repository`() = runBlocking {
        // Given
        val expectedWeatherData = WeatherData(
            locationName = "Hyderabad",
            temperature = 31.0,
            condition = "Patchy rain nearby",
            icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
            humidity = 76,
            uvIndex = 4.0,
            feelsLike = 30.4
        )
        coEvery { localStorageRepository.getWeatherData() } returns expectedWeatherData

        // When
        val actualWeatherData = getStoredWeatherDataUseCase()

        // Then
        assertEquals(expectedWeatherData, actualWeatherData)
        coVerify { localStorageRepository.getWeatherData() }
    }

    @Test
    fun `invoke should return null when no weather data is found`() = runBlocking {
        // Given
        coEvery { localStorageRepository.getWeatherData() } returns null

        // When
        val actualWeatherData = getStoredWeatherDataUseCase()

        // Then
        assertNull(actualWeatherData)
        coVerify { localStorageRepository.getWeatherData() }
    }

    @Test
    fun `invoke should handle repository exception`() = runBlocking {
        // Given
        coEvery { localStorageRepository.getWeatherData() } throws RuntimeException("Repository error")

        // When
        val actualWeatherData = runCatching { getStoredWeatherDataUseCase() }.exceptionOrNull()

        // Then
        assert(actualWeatherData is RuntimeException)
        coVerify { localStorageRepository.getWeatherData() }
    }
}