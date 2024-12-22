package com.rishirajput.weather.app

import android.app.Application
import com.rishirajput.data.di.dataModule
import com.rishirajput.weather.di.appModule
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