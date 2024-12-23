package com.rishirajput.domain.errors

import org.junit.Assert.assertEquals
import org.junit.Test

class InvalidCityExceptionTest {

    @Test
    fun `InvalidCityException should have default message`() {
        // When
        val exception = InvalidCityException()

        // Then
        assertEquals("Invalid city name provided.", exception.message)
    }

    @Test
    fun `InvalidCityException should have custom message`() {
        // Given
        val customMessage = "Custom invalid city error message"

        // When
        val exception = InvalidCityException(customMessage)

        // Then
        assertEquals(customMessage, exception.message)
    }
}