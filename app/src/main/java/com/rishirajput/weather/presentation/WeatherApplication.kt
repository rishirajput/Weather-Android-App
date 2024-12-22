package com.rishirajput.weather.presentation

import android.app.Application
import com.rishirajput.data.di.dataModule
import com.rishirajput.weather.presentation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(appModule, dataModule)
        }
    }
}