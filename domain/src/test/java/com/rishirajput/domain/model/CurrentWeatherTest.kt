package com.rishirajput.domain.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrentWeatherTest {

    @Test
    fun `serialize CurrentWeather to JSON`() {
        // Given
        val currentWeather = CurrentWeather(
            tempC = 31.0,
            condition = Condition("Patchy rain nearby", "//cdn.weatherapi.com/weather/64x64/night/176.png"),
            humidity = 76,
            uv = 4.0,
            feelsLikeC = 30.4
        )

        // When
        val json = Json.encodeToString(currentWeather)

        // Then
        val expectedJson = """{"temp_c":31.0,"condition":{"text":"Patchy rain nearby","icon":"//cdn.weatherapi.com/weather/64x64/night/176.png"},"humidity":76,"uv":4.0,"feelslike_c":30.4}"""
        assertEquals(expectedJson, json)
    }

    @Test
    fun `deserialize JSON to CurrentWeather`() {
        // Given
        val json = """{"temp_c":31.0,"condition":{"text":"Patchy rain nearby","icon":"//cdn.weatherapi.com/weather/64x64/night/176.png"},"humidity":76,"uv":4.0,"feelslike_c":30.4}"""

        // When
        val currentWeather = Json.decodeFromString<CurrentWeather>(json)

        // Then
        val expectedCurrentWeather = CurrentWeather(
            tempC = 31.0,
            condition = Condition("Patchy rain nearby", "//cdn.weatherapi.com/weather/64x64/night/176.png"),
            humidity = 76,
            uv = 4.0,
            feelsLikeC = 30.4
        )
        assertEquals(expectedCurrentWeather, currentWeather)
    }
}