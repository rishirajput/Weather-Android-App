package com.rishirajput.weather.presentation

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rishirajput.data.di.dataModule
import com.rishirajput.domain.repository.WeatherRepository
import com.rishirajput.weather.presentation.di.appModule
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class WeatherApplicationTest : KoinTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(context)
                modules(appModule, dataModule)
            }
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testKoinModulesAreLoaded() {
        val weatherRepository: WeatherRepository by inject()
        assertNotNull(weatherRepository)
    }
}