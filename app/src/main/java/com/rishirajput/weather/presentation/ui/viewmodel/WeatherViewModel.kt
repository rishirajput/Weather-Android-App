package com.rishirajput.weather.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.usecase.FetchWeatherDataUseCase
import com.rishirajput.domain.usecase.GetSelectedWeatherDataUseCase
import com.rishirajput.domain.usecase.StoreWeatherDataUseCase
import com.rishirajput.domain.usecase.GetCurrentWeatherDataUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class WeatherViewModel(
    private val fetchWeatherDataUseCase: FetchWeatherDataUseCase,
    private val storeWeatherDataUseCase: StoreWeatherDataUseCase,
    private val getSelectedWeatherDataUseCase: GetSelectedWeatherDataUseCase,
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<WeatherData>>(emptyList())
    val searchResults: StateFlow<List<WeatherData>> = _searchResults

    private val _selectedWeatherData = MutableStateFlow<WeatherData?>(null)
    val selectedWeatherData: StateFlow<WeatherData?> = _selectedWeatherData

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow: SharedFlow<String> = _errorFlow

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var fetchJob: Job? = null

    init {
        viewModelScope.launch {
            _selectedWeatherData.value = getSelectedWeatherDataUseCase()
            when (val result = getCurrentWeatherDataUseCase(_selectedWeatherData.value)) {
                is com.rishirajput.domain.model.Result.Success -> _selectedWeatherData.value = result.data
                is com.rishirajput.domain.model.Result.Error -> _errorFlow.emit(result.exception.message ?: "Unknown error")
                com.rishirajput.domain.model.Result.Loading -> { /* Handle loading state if needed */ }
                null -> { /* Handle null case if needed */ }
            }
        }
    }

    fun fetchWeatherData(query: String) {
        _query.value = query
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = fetchWeatherDataUseCase(query)
                if (result is com.rishirajput.domain.model.Result.Success) {
                    _searchResults.value = result.data
                } else if (result is com.rishirajput.domain.model.Result.Error) {
                    _errorFlow.emit(result.exception.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                _errorFlow.emit(e.message ?: "Unknown error")
            } finally {
                _isLoading.value = false
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