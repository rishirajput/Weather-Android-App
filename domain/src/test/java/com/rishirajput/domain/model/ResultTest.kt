package com.rishirajput.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultTest {

    @Test
    fun `Success should return correct data`() {
        // Given
        val data = "Test Data"
        val result = Result.Success(data)

        // Then
        assertTrue(result is Result.Success)
        assertEquals(data, (result as Result.Success).data)
    }

    @Test
    fun `Error should return correct exception`() {
        // Given
        val exception = RuntimeException("Test Exception")
        val result = Result.Error(exception)

        // Then
        assertTrue(result is Result.Error)
        assertEquals(exception, (result as Result.Error).exception)
    }
}