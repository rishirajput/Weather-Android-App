package com.rishirajput.data.serializer

import androidx.datastore.core.CorruptionException
import com.rishirajput.domain.model.WeatherData
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class WeatherDataSerializerTest {

    @Test
    fun `readFrom should deserialize valid JSON`() = runTest {
        // Given
        val json = """{"locationName":"New York","temperature":25.0,"condition":"Sunny","icon":"//cdn.weatherapi.com/weather/64x64/day/113.png","humidity":50,"uvIndex":5.0,"feelsLike":27.0}"""
        val inputStream = ByteArrayInputStream(json.toByteArray())

        // When
        val weatherData = WeatherDataSerializer.readFrom(inputStream)

        // Then
        val expectedWeatherData = WeatherData(
            locationName = "New York",
            temperature = 25.0,
            condition = "Sunny",
            icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
            humidity = 50,
            uvIndex = 5.0,
            feelsLike = 27.0
        )
        assertEquals(expectedWeatherData, weatherData)
    }

    @Test
    fun `readFrom should throw CorruptionException for invalid JSON`() = runTest {
        // Given
        val invalidJson = """{"locationName":"New York","temperature":25.0,"condition":"Sunny","icon":"//cdn.weatherapi.com/weather/64x64/day/113.png","humidity":50,"uvIndex":5.0,"feelsLike":27.0"""
        val inputStream = ByteArrayInputStream(invalidJson.toByteArray())

        // Then
        assertThrows(CorruptionException::class.java) {
            runBlocking {
                // When
                WeatherDataSerializer.readFrom(inputStream)
            }
        }
    }

    @Test
    fun `writeTo should serialize WeatherData to JSON`() = runTest {
        // Given
        val weatherData = WeatherData(
            locationName = "New York",
            temperature = 25.0,
            condition = "Sunny",
            icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
            humidity = 50,
            uvIndex = 5.0,
            feelsLike = 27.0
        )
        val outputStream = ByteArrayOutputStream()

        // When
        WeatherDataSerializer.writeTo(weatherData, outputStream)

        // Then
        val expectedJson = """{"locationName":"New York","temperature":25.0,"condition":"Sunny","icon":"//cdn.weatherapi.com/weather/64x64/day/113.png","humidity":50,"uvIndex":5.0,"feelsLike":27.0}"""
        assertEquals(expectedJson, outputStream.toString())
    }
}