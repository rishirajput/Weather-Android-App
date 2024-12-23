package com.rishirajput.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.WeatherRepository
import com.rishirajput.domain.model.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GetCurrentWeatherDataUseCaseTest {

    private val weatherRepository: WeatherRepository = mockk()
    private val getCurrentWeatherDataUseCase = GetCurrentWeatherDataUseCase(weatherRepository)

    @Test
    fun `invoke should return weather data for valid location`() = runBlocking {
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
        val expectedResult = Result.Success(weatherData)
        coEvery { weatherRepository.getWeatherDataForLocation("Hyderabad") } returns expectedResult

        // When
        val result = getCurrentWeatherDataUseCase(weatherData)

        // Then
        assertEquals(expectedResult, result)
        coVerify { weatherRepository.getWeatherDataForLocation("Hyderabad") }
    }

    @Test
    fun `invoke should return null for null input`() = runBlocking {
        // When
        val result = getCurrentWeatherDataUseCase(null)

        // Then
        assertNull(result)
        coVerify(exactly = 0) { weatherRepository.getWeatherDataForLocation(any()) }
    }

    @Test
    fun `invoke should handle repository exception`() = runBlocking {
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
        coEvery { weatherRepository.getWeatherDataForLocation("Hyderabad") } throws RuntimeException("Repository error")

        // When
        val result = runCatching { getCurrentWeatherDataUseCase(weatherData) }.exceptionOrNull()

        // Then
        assert(result is RuntimeException)
        coVerify { weatherRepository.getWeatherDataForLocation("Hyderabad") }
    }
}