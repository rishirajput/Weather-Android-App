package com.rishirajput.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rishirajput.data.api.WeatherApiService
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.LocalStorageRepository
import com.rishirajput.domain.repository.WeatherRepository
import com.rishirajput.domain.usecase.FetchWeatherDataUseCase
import com.rishirajput.domain.usecase.GetCurrentWeatherDataUseCase
import com.rishirajput.domain.usecase.GetStoredWeatherDataUseCase
import com.rishirajput.domain.usecase.StoreWeatherDataUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class DataModuleTest : KoinTest {

    private val weatherApiService: WeatherApiService by inject()
    private val weatherRepository: WeatherRepository by inject()
    private val dataStore: DataStore<WeatherData?> by inject()
    private val localStorageRepository: LocalStorageRepository by inject()
    private val fetchWeatherDataUseCase: FetchWeatherDataUseCase by inject()
    private val storeWeatherDataUseCase: StoreWeatherDataUseCase by inject()
    private val getStoredWeatherDataUseCase: GetStoredWeatherDataUseCase by inject()
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase by inject()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        startKoin {
            androidContext(context)
            modules(dataModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testWeatherApiServiceIsProvided() {
        assertNotNull(weatherApiService)
    }

    @Test
    fun testWeatherRepositoryIsProvided() {
        assertNotNull(weatherRepository)
    }

    @Test
    fun testDataStoreIsProvided() {
        assertNotNull(dataStore)
    }

    @Test
    fun testLocalStorageRepositoryIsProvided() {
        assertNotNull(localStorageRepository)
    }

    @Test
    fun testFetchWeatherDataUseCaseIsProvided() {
        assertNotNull(fetchWeatherDataUseCase)
    }

    @Test
    fun testStoreWeatherDataUseCaseIsProvided() {
        assertNotNull(storeWeatherDataUseCase)
    }

    @Test
    fun testGetStoredWeatherDataUseCaseIsProvided() {
        assertNotNull(getStoredWeatherDataUseCase)
    }

    @Test
    fun testGetCurrentWeatherDataUseCaseIsProvided() {
        assertNotNull(getCurrentWeatherDataUseCase)
    }
}