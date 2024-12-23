package com.rishirajput.domain.errors

import org.junit.Assert.assertEquals
import org.junit.Test

class NoNetworkExceptionTest {

    @Test
    fun `NoNetworkException should have default message`() {
        // When
        val exception = NoNetworkException()

        // Then
        assertEquals("No network connection available.", exception.message)
    }

    @Test
    fun `NoNetworkException should have custom message`() {
        // Given
        val customMessage = "Custom network error message"

        // When
        val exception = NoNetworkException(customMessage)

        // Then
        assertEquals(customMessage, exception.message)
    }
}