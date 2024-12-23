package com.rishirajput.data.repository

import androidx.datastore.core.DataStore
import com.rishirajput.domain.model.WeatherData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DataStoreRepositoryTest {

    private lateinit var dataStore: DataStore<WeatherData?>
    private lateinit var repository: DataStoreRepository

    @Before
    fun setUp() {
        dataStore = mockk()
        repository = DataStoreRepository(dataStore)
    }

    @Test
    fun `storeWeatherData stores the weather data`() = runTest {
        val weatherData = WeatherData("Test City", 25.0, "Clear", "icon_url", 60, 5.0, 25.0)
        val slot = slot<suspend (WeatherData?) -> WeatherData?>()
        coEvery { dataStore.updateData(capture(slot)) } coAnswers { slot.captured.invoke(weatherData) }

        repository.storeWeatherData(weatherData)

        coVerify { dataStore.updateData(any()) }
    }

    @Test
    fun `getWeatherData returns the stored weather data`() = runTest {
        val weatherData = WeatherData("Test City", 25.0, "Clear", "icon_url", 60, 5.0, 25.0)
        coEvery { dataStore.data } returns flowOf(weatherData)

        val result = repository.getWeatherData()

        assertEquals(weatherData, result)
    }

    @Test
    fun `getWeatherData returns null when no data is stored`() = runTest {
        coEvery { dataStore.data } returns flowOf(null)

        val result = repository.getWeatherData()

        assertEquals(null, result)
    }
}