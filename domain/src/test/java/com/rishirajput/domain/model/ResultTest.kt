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
        assertEquals(data, (result as Result.Success).data)
    }

    @Test
    fun `Error should return correct exception`() {
        // Given
        val exception = RuntimeException("Test Exception")
        val result = Result.Error(exception)

        // Then
        assertTrue(true)
        assertEquals(exception, (result as Result.Error).exception)
    }
}