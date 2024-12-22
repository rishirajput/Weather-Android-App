package com.rishirajput.weather.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishirajput.domain.model.Result
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.usecase.FetchWeatherDataUseCase
import com.rishirajput.domain.usecase.GetCurrentWeatherDataUseCase
import com.rishirajput.domain.usecase.GetSelectedWeatherDataUseCase
import com.rishirajput.domain.usecase.StoreWeatherDataUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> = _errorFlow

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var fetchJob: Job? = null

    init {
        viewModelScope.launch {
            _selectedWeatherData.value = getSelectedWeatherDataUseCase()
            when (val result = getCurrentWeatherDataUseCase(_selectedWeatherData.value)) {
                is Result.Success -> _selectedWeatherData.value = result.data
                is Result.Error -> _errorFlow.emit(result.exception)
                Result.Loading -> { /* Handle loading state if needed */
                }

                null -> {
                    _errorFlow.emit(NullPointerException())
                }
            }
        }
    }

    fun fetchWeatherData(query: String) {
        _searchQuery.value = query
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = fetchWeatherDataUseCase(query)
                if (result is Result.Success) {
                    _searchResults.value = result.data
                } else if (result is Result.Error) {
                    _errorFlow.emit(result.exception)
                }
            } catch (e: Exception) {
                _errorFlow.emit(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectWeatherData(data: WeatherData) {
        viewModelScope.launch {
            storeWeatherDataUseCase(data)
            _selectedWeatherData.value = data
            _searchQuery.value = ""
        }
    }
}