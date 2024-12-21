package com.rishirajput.weather.presentation.viewmodel

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishirajput.weather.domain.model.WeatherData
import com.rishirajput.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val dataStore: DataStore<WeatherData?>
) : ViewModel() {

    private val _weatherData = MutableStateFlow<List<WeatherData>>(emptyList())
    val weatherData: StateFlow<List<WeatherData>> = _weatherData

    private val _selectedWeatherData = MutableStateFlow<WeatherData?>(null)
    val selectedWeatherData: StateFlow<WeatherData?> = _selectedWeatherData

    init {
        viewModelScope.launch {
            _selectedWeatherData.value = dataStore.data.first()
        }
    }

    fun fetchWeatherData(query: String) {
        viewModelScope.launch {
            val data = repository.getWeatherData(query)
            _weatherData.value = data
        }
    }

    fun selectWeatherData(data: WeatherData) {
        viewModelScope.launch {
            dataStore.updateData { data }
            _selectedWeatherData.value = data
        }
    }
}