package com.rishirajput.data.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstantsTest {

    @Test
    fun `DEBOUNCE_DELAY should be 400L`() {
        assertEquals(400L, Constants.DEBOUNCE_DELAY)
    }
}