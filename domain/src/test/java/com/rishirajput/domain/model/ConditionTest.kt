package com.rishirajput.domain.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class ConditionTest {

    @Test
    fun `serialize Condition to JSON`() {
        // Given
        val condition = Condition(
            text = "Patchy rain nearby",
            icon = "//cdn.weatherapi.com/weather/64x64/night/176.png"
        )

        // When
        val json = Json.encodeToString(condition)

        // Then
        val expectedJson = """{"text":"Patchy rain nearby","icon":"//cdn.weatherapi.com/weather/64x64/night/176.png"}"""
        assertEquals(expectedJson, json)
    }

    @Test
    fun `deserialize JSON to Condition`() {
        // Given
        val json = """{"text":"Patchy rain nearby","icon":"//cdn.weatherapi.com/weather/64x64/night/176.png"}"""

        // When
        val condition = Json.decodeFromString<Condition>(json)

        // Then
        val expectedCondition = Condition(
            text = "Patchy rain nearby",
            icon = "//cdn.weatherapi.com/weather/64x64/night/176.png"
        )
        assertEquals(expectedCondition, condition)
    }
}