package com.rishirajput.weather.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishirajput.domain.model.Result
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.usecase.FetchWeatherDataUseCase
import com.rishirajput.domain.usecase.GetSelectedWeatherDataUseCase
import com.rishirajput.domain.usecase.StoreWeatherDataUseCase
import com.rishirajput.weather.domain.usecase.GetCurrentWeatherDataUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val fetchWeatherDataUseCase: FetchWeatherDataUseCase,
    private val storeWeatherDataUseCase: StoreWeatherDataUseCase,
    private val getSelectedWeatherDataUseCase: GetSelectedWeatherDataUseCase,
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase
) : ViewModel() {

    private val _searchResults = MutableStateFlow<Result<List<WeatherData>>>(Result.Success(emptyList()))
    val searchResults: StateFlow<Result<List<WeatherData>>> = _searchResults

    private val _selectedWeatherData = MutableStateFlow<WeatherData?>(null)
    val selectedWeatherData: StateFlow<WeatherData?> = _selectedWeatherData

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private var fetchJob: Job? = null

    init {
        viewModelScope.launch {
            _selectedWeatherData.value = getSelectedWeatherDataUseCase()
            _selectedWeatherData.value = getCurrentWeatherDataUseCase(_selectedWeatherData.value)
        }
    }

    fun fetchWeatherData(query: String) {
        _query.value = query
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _searchResults.value = Result.Loading
            try {
                val result = fetchWeatherDataUseCase(query)
                _searchResults.value = result
            } catch (e: Exception) {
                _searchResults.value = Result.Error(e)
            }
        }
    }

    fun selectWeatherData(data: WeatherData) {
        viewModelScope.launch {
            storeWeatherDataUseCase(data)
            _selectedWeatherData.value = data
            _query.value = ""
        }
    }
}