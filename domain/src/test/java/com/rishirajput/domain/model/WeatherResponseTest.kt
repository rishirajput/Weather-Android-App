package com.rishirajput.domain.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherResponseTest {

    @Test
    fun `serialize WeatherResponse to JSON`() {
        // Given
        val currentWeather = CurrentWeather(
            tempC = 31.0,
            condition = Condition("Patchy rain nearby", "//cdn.weatherapi.com/weather/64x64/night/176.png"),
            humidity = 76,
            uv = 4.0,
            feelsLikeC = 30.4
        )
        val weatherResponse = WeatherResponse(current = currentWeather)

        // When
        val json = Json.encodeToString(weatherResponse)

        // Then
        val expectedJson = """{"current":{"temp_c":31.0,"condition":{"text":"Patchy rain nearby","icon":"//cdn.weatherapi.com/weather/64x64/night/176.png"},"humidity":76,"uv":4.0,"feelslike_c":30.4}}"""
        assertEquals(expectedJson, json)
    }

    @Test
    fun `deserialize JSON to WeatherResponse`() {
        // Given
        val json = """{"current":{"temp_c":31.0,"condition":{"text":"Patchy rain nearby","icon":"//cdn.weatherapi.com/weather/64x64/night/176.png"},"humidity":76,"uv":4.0,"feelslike_c":30.4}}"""

        // When
        val weatherResponse = Json.decodeFromString<WeatherResponse>(json)

        // Then
        val expectedCurrentWeather = CurrentWeather(
            tempC = 31.0,
            condition = Condition("Patchy rain nearby", "//cdn.weatherapi.com/weather/64x64/night/176.png"),
            humidity = 76,
            uv = 4.0,
            feelsLikeC = 30.4
        )
        val expectedWeatherResponse = WeatherResponse(current = expectedCurrentWeather)
        assertEquals(expectedWeatherResponse, weatherResponse)
    }
}