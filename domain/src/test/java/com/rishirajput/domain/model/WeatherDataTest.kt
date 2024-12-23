package com.rishirajput.domain.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherDataTest {

    @Test
    fun `serialize WeatherData to JSON`() {
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
        val json = Json.encodeToString(weatherData)

        // Then
        val expectedJson = """{"locationName":"Hyderabad","temperature":31.0,"condition":"Patchy rain nearby","icon":"//cdn.weatherapi.com/weather/64x64/night/176.png","humidity":76,"uvIndex":4.0,"feelsLike":30.4}"""
        assertEquals(expectedJson, json)
    }

    @Test
    fun `deserialize JSON to WeatherData`() {
        // Given
        val json = """{"locationName":"Hyderabad","temperature":31.0,"condition":"Patchy rain nearby","icon":"//cdn.weatherapi.com/weather/64x64/night/176.png","humidity":76,"uvIndex":4.0,"feelsLike":30.4}"""

        // When
        val weatherData = Json.decodeFromString<WeatherData>(json)

        // Then
        val expectedWeatherData = WeatherData(
            locationName = "Hyderabad",
            temperature = 31.0,
            condition = "Patchy rain nearby",
            icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
            humidity = 76,
            uvIndex = 4.0,
            feelsLike = 30.4
        )
        assertEquals(expectedWeatherData, weatherData)
    }
}