package com.rishirajput.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishirajput.weather.domain.model.WeatherData
import com.rishirajput.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.datastore.core.DataStore
import com.rishirajput.weather.presentation.utils.Constants
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val dataStore: DataStore<WeatherData?>
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<WeatherData>>(emptyList())
    val searchResults: StateFlow<List<WeatherData>> = _searchResults

    private val _selectedWeatherData = MutableStateFlow<WeatherData?>(null)
    val selectedWeatherData: StateFlow<WeatherData?> = _selectedWeatherData

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private var fetchJob: Job? = null

    init {
        viewModelScope.launch {
            _selectedWeatherData.value = dataStore.data.first()
        }
    }

    fun fetchWeatherData(query: String) {
        _query.value = query
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            delay(Constants.DEBOUNCE_DELAY)
            if (query.isEmpty()) {
                _searchResults.value = emptyList()
                return@launch
            } else {
                val data = repository.getWeatherData(query)
                _searchResults.value = data
            }
        }
    }

    fun selectWeatherData(data: WeatherData) {
        viewModelScope.launch {
            dataStore.updateData { data }
            _selectedWeatherData.value = data
            _query.value = ""
        }
    }
}