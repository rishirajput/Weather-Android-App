package com.rishirajput.domain.usecase

import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.WeatherRepository
import com.rishirajput.domain.model.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FetchWeatherDataUseCaseTest {

    private val weatherRepository: WeatherRepository = mockk()
    private val fetchWeatherDataUseCase = FetchWeatherDataUseCase(weatherRepository)

    @Test
    fun `invoke should return weather data for valid query`() = runBlocking {
        // Given
        val query = "Hyderabad"
        val weatherDataList = listOf(
            WeatherData(
                locationName = "Hyderabad",
                temperature = 31.0,
                condition = "Patchy rain nearby",
                icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
                humidity = 76,
                uvIndex = 4.0,
                feelsLike = 30.4
            )
        )
        val expectedResult = Result.Success(weatherDataList)
        coEvery { weatherRepository.getWeatherData(query) } returns expectedResult

        // When
        val result = fetchWeatherDataUseCase(query)

        // Then
        assertEquals(expectedResult, result)
        coVerify { weatherRepository.getWeatherData(query) }
    }

    @Test
    fun `invoke should return empty list for no results`() = runBlocking {
        // Given
        val query = "Unknown"
        val expectedResult = Result.Success(emptyList<WeatherData>())
        coEvery { weatherRepository.getWeatherData(query) } returns expectedResult

        // When
        val result = fetchWeatherDataUseCase(query)

        // Then
        assertEquals(expectedResult, result)
        coVerify { weatherRepository.getWeatherData(query) }
    }

    @Test
    fun `invoke should handle repository exception`() = runBlocking {
        // Given
        val query = "Hyderabad"
        val expectedException = RuntimeException("Repository error")
        coEvery { weatherRepository.getWeatherData(query) } throws expectedException

        // When
        val result = runCatching { fetchWeatherDataUseCase(query) }.exceptionOrNull()

        // Then
        assertTrue(result is RuntimeException)
        coVerify { weatherRepository.getWeatherData(query) }
    }
}