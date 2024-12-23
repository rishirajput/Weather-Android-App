package com.rishirajput.weather.presentation.ui.viewmodel

import com.rishirajput.domain.model.Result
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.usecase.FetchWeatherDataUseCase
import com.rishirajput.domain.usecase.GetCurrentWeatherDataUseCase
import com.rishirajput.domain.usecase.GetStoredWeatherDataUseCase
import com.rishirajput.domain.usecase.StoreWeatherDataUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private val fetchWeatherDataUseCase: FetchWeatherDataUseCase = mockk()
    private val storeWeatherDataUseCase: StoreWeatherDataUseCase = mockk()
    private val getStoredWeatherDataUseCase: GetStoredWeatherDataUseCase = mockk()
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase = mockk()
    private lateinit var viewModel: WeatherViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getStoredWeatherDataUseCase.invoke() } returns null
        coEvery { fetchWeatherDataUseCase.invoke(any()) } returns Result.Success(listOf(WeatherData("Sunny", 25.0, "icon", "60", 5, 27.0, 28.0)))
        coEvery { getCurrentWeatherDataUseCase.invoke(any()) } returns null
        coEvery { storeWeatherDataUseCase.invoke(any()) } returns Unit
        viewModel = WeatherViewModel(
            fetchWeatherDataUseCase,
            storeWeatherDataUseCase,
            getStoredWeatherDataUseCase,
            getCurrentWeatherDataUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initial state`() = runTest(testDispatcher) {
        coEvery { getStoredWeatherDataUseCase.invoke() } returns null
        assertNull(viewModel.storedWeatherData.first())
        assertTrue(viewModel.searchResults.first().isEmpty())
        assertFalse(viewModel.isLoading.first())
    }

    @Test
    fun `test fetchWeatherData success`() = runTest(testDispatcher) {
        val weatherData = listOf(WeatherData("Sunny", 25.0, "icon", "60", 5, 27.0, 28.0))
        coEvery { fetchWeatherDataUseCase("query") } returns Result.Success(weatherData)

        viewModel.fetchWeatherData("query")

        assertEquals(weatherData, viewModel.searchResults.first())
        assertFalse(viewModel.isLoading.first())
    }


    @Test
    fun `test selectWeatherData`() = runTest(testDispatcher) {
        val weatherData = WeatherData("Sunny", 25.0, "icon", "60", 5, 27.0, 28.0)
        coEvery { storeWeatherDataUseCase(weatherData) } returns Unit

        viewModel.selectWeatherData(weatherData)

        assertEquals(weatherData, viewModel.storedWeatherData.first())
        assertEquals("", viewModel.searchQuery.first())
        coVerify { storeWeatherDataUseCase(weatherData) }
    }
}