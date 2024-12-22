package com.rishirajput.weather.domain.usecase

import androidx.datastore.core.DataStore
import com.rishirajput.weather.domain.model.WeatherData
import kotlinx.coroutines.flow.first

class SelectWeatherDataUseCase(private val dataStore: DataStore<WeatherData?>) {
    suspend operator fun invoke(data: WeatherData) {
        dataStore.updateData { data }
    }

    suspend fun getSelectedWeatherData(): WeatherData? {
        return dataStore.data.first()
    }
}