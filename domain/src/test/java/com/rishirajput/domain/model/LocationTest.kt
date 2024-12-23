package com.rishirajput.domain.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationTest {

    @Test
    fun `serialize Location to JSON`() {
        // Given
        val location = Location(
            id = 1,
            name = "Hyderabad",
            region = "Telangana",
            country = "India",
            lat = 17.3850,
            lon = 78.4867,
            url = "http://example.com/hyderabad"
        )

        // When
        val json = Json.encodeToString(location)

        // Then
        val expectedJson = """{"id":1,"name":"Hyderabad","region":"Telangana","country":"India","lat":17.385,"lon":78.4867,"url":"http://example.com/hyderabad"}"""
        assertEquals(expectedJson, json)
    }

    @Test
    fun `deserialize JSON to Location`() {
        // Given
        val json = """{"id":1,"name":"Hyderabad","region":"Telangana","country":"India","lat":17.385,"lon":78.4867,"url":"http://example.com/hyderabad"}"""

        // When
        val location = Json.decodeFromString<Location>(json)

        // Then
        val expectedLocation = Location(
            id = 1,
            name = "Hyderabad",
            region = "Telangana",
            country = "India",
            lat = 17.3850,
            lon = 78.4867,
            url = "http://example.com/hyderabad"
        )
        assertEquals(expectedLocation, location)
    }
}