package com.rishirajput.weather.data.repository

import android.util.Log
import com.rishirajput.weather.data.api.WeatherApiService
import com.rishirajput.weather.domain.model.WeatherData
import com.rishirajput.weather.domain.repository.WeatherRepository
import com.rishirajput.weather.BuildConfig
import com.rishirajput.weather.data.api.LocationResponse
import com.rishirajput.weather.data.api.WeatherResponse
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class RetrofitWeatherRepository(private val apiService: WeatherApiService) : WeatherRepository {

    override suspend fun getWeatherData(query: String): List<WeatherData> {
        val locations = getLocations(query)
        return locations.map { getWeatherDataForLocation(it.name) }
    }

    override suspend fun getLocations(query: String): List<LocationResponse> {
        val locationResponse: Response<List<LocationResponse>> = apiService.searchLocation(BuildConfig.WEATHER_API_KEY, query)
        if (!locationResponse.isSuccessful || locationResponse.body().isNullOrEmpty()) {
            Log.e("RetrofitWeatherRepository", "No matching location found for query: $query")
            return emptyList()
        }
        return locationResponse.body()!!
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