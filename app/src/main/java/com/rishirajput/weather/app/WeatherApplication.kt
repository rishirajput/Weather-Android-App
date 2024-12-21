package com.rishirajput.weather.app

import android.app.Application
import com.rishirajput.weather.di.appModule
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}