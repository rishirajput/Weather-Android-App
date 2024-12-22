package com.rishirajput.data.repository

import androidx.datastore.core.DataStore
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.first

class DataStoreRepository(
    private val dataStore: DataStore<WeatherData?>
) : LocalStorageRepository {

    override suspend fun storeWeatherData(weatherData: WeatherData) {
        dataStore.updateData { weatherData }
    }

    override suspend fun getWeatherData(): WeatherData? {
        return dataStore.data.first()
    }
}