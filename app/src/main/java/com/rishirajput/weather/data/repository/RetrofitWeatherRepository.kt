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
        Log.d("RetrofitWeatherRepository", "Searching for location: $query")
        val locationResponse: Response<List<LocationResponse>> = apiService.searchLocation(BuildConfig.WEATHER_API_KEY, query)
        Log.d("RetrofitWeatherRepository", "Location response: ${locationResponse.raw()}")

        if (!locationResponse.isSuccessful || locationResponse.body().isNullOrEmpty()) {
            Log.e("RetrofitWeatherRepository", "No matching location found for query: $query")
            return emptyList()
        }

        val locations = locationResponse.body()!!
        val weatherDataList = mutableListOf<WeatherData>()

        for (location in locations) {
            Log.d("RetrofitWeatherRepository", "Fetching weather data for location: ${location.name}")
            try {
                val weatherResponse: Response<WeatherResponse> = apiService.getCurrentWeather(BuildConfig.WEATHER_API_KEY, location.name)
                Log.d("RetrofitWeatherRepository", "Weather response: ${weatherResponse.raw()}")

                if (weatherResponse.isSuccessful) {
                    val current = weatherResponse.body()?.current
                    Log.d("RetrofitWeatherRepository", "Received weather data: $current")
                    weatherDataList.add(
                        WeatherData(
                            locationName = location.name,
                            temperature = current?.temp_c ?: 0.0,
                            condition = current?.condition?.text ?: "Unknown",
                            icon = current?.condition?.icon ?: "",
                            humidity = current?.humidity ?: 0,
                            uvIndex = current?.uv ?: 0.0,
                            feelsLike = current?.feelslike_c ?: 0.0
                        )
                    )
                } else {
                    val errorBody: ResponseBody? = weatherResponse.errorBody()
                    Log.e("RetrofitWeatherRepository", "HTTP error: ${weatherResponse.code()} ${weatherResponse.message()}")
                    Log.e("RetrofitWeatherRepository", "Error body: ${errorBody?.string()}")
                }
            } catch (e: HttpException) {
                Log.e("RetrofitWeatherRepository", "HTTP error: ${e.code()} ${e.message()}")
            } catch (e: Exception) {
                Log.e("RetrofitWeatherRepository", "Error fetching weather data", e)
            }
        }

        return weatherDataList
    }
}