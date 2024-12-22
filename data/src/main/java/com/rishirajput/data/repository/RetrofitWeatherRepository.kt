package com.rishirajput.data.repository

import android.util.Log
import com.rishirajput.data.BuildConfig
import com.rishirajput.data.api.WeatherApiService
import com.rishirajput.data.api.WeatherResponse
import com.rishirajput.data.utils.Constants
import com.rishirajput.domain.model.Location
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import com.rishirajput.domain.model.Result

class RetrofitWeatherRepository(private val apiService: WeatherApiService) : WeatherRepository {

    override suspend fun getWeatherData(query: String): Result<List<WeatherData>> {
        if (query.isEmpty()) {
            return Result.Success(emptyList())
        }

        delay(Constants.DEBOUNCE_DELAY)

        return withContext(Dispatchers.IO) {
            try {
                val locations = getLocations(query)
                val weatherData = locations.map { getWeatherDataForLocation(it.name) }
                Result.Success(weatherData)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun getLocations(query: String): List<Location> {
        val location: Response<List<Location>> = apiService.searchLocation(BuildConfig.WEATHER_API_KEY, query)
        if (!location.isSuccessful || location.body().isNullOrEmpty()) {
            Log.e("RetrofitWeatherRepository", "No matching location found for query: $query")
            return emptyList()
        }
        return location.body()!!
    }

    override suspend fun getWeatherDataForLocation(locationName: String): WeatherData {
        var weatherData = WeatherData("", 0.0, "", "", 0, 0.0, 0.0)
        try {
            val weatherResponse: Response<WeatherResponse> = apiService.getCurrentWeather(BuildConfig.WEATHER_API_KEY, locationName)
            if (weatherResponse.isSuccessful) {
                val current = weatherResponse.body()?.current
                weatherData = weatherData.copy(
                        locationName = locationName,
                        temperature = current?.temp_c ?: 0.0,
                        condition = current?.condition?.text ?: "Unknown",
                        icon = current?.condition?.icon ?: "",
                        humidity = current?.humidity ?: 0,
                        uvIndex = current?.uv ?: 0.0,
                        feelsLike = current?.feelslike_c ?: 0.0
                )
            } else {
                Log.e("RetrofitWeatherRepository", "HTTP error: ${weatherResponse.code()} ${weatherResponse.message()}")
            }
        } catch (e: HttpException) {
            Log.e("RetrofitWeatherRepository", "HTTP error: ${e.code()} ${e.message()}")
        } catch (e: Exception) {
            Log.e("RetrofitWeatherRepository", "Error fetching weather data", e)
        }
        return weatherData
    }
}