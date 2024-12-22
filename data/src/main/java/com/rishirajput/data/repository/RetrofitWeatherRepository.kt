package com.rishirajput.data.repository

import android.util.Log
import com.rishirajput.data.BuildConfig
import com.rishirajput.data.api.WeatherApiService
import com.rishirajput.data.utils.Constants
import com.rishirajput.domain.errors.InvalidCityException
import com.rishirajput.domain.errors.NoNetworkException
import com.rishirajput.domain.model.Location
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import com.rishirajput.domain.model.Result
import com.rishirajput.domain.model.WeatherResponse
import java.io.IOException

class RetrofitWeatherRepository(private val apiService: WeatherApiService) : WeatherRepository {

    override suspend fun getWeatherData(query: String): Result<List<WeatherData>> {
        if (query.isEmpty()) {
            return Result.Success(emptyList())
        }

        delay(Constants.DEBOUNCE_DELAY)

        return withContext(Dispatchers.IO) {
            try {
                val locationsResult = getLocations(query)
                if (locationsResult is Result.Success) {
                    if (locationsResult.data.isEmpty()) {
                        return@withContext Result.Error(InvalidCityException())
                    }
                    val weatherData = locationsResult.data.map { location ->
                        val weatherResult = getWeatherDataForLocation(location.name)
                        if (weatherResult is Result.Success) {
                            weatherResult.data
                        } else {
                            return@withContext Result.Error((weatherResult as Result.Error).exception)
                        }
                    }
                    Result.Success(weatherData)
                } else {
                    Result.Error((locationsResult as Result.Error).exception)
                }
            } catch (e: IOException) {
                Result.Error(NoNetworkException())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun getLocations(query: String): Result<List<Location>> {
        return try {
            val location: Response<List<Location>> = apiService.searchLocation(BuildConfig.WEATHER_API_KEY, query)
            if (!location.isSuccessful || location.body().isNullOrEmpty()) {
                Log.e("RetrofitWeatherRepository", "No matching location found for query: $query")
                Result.Success(emptyList())
            } else {
                Result.Success(location.body()!!)
            }
        } catch (e: IOException) {
            Result.Error(NoNetworkException())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getWeatherDataForLocation(locationName: String): Result<WeatherData> {
        return try {
            val weatherResponse: Response<WeatherResponse> = apiService.getCurrentWeather(BuildConfig.WEATHER_API_KEY, locationName)
            if (weatherResponse.isSuccessful) {
                val current = weatherResponse.body()?.current
                val weatherData = WeatherData(
                    locationName = locationName,
                    temperature = current?.temp_c ?: 0.0,
                    condition = current?.condition?.text ?: "Unknown",
                    icon = current?.condition?.icon ?: "",
                    humidity = current?.humidity ?: 0,
                    uvIndex = current?.uv ?: 0.0,
                    feelsLike = current?.feelslike_c ?: 0.0
                )
                Result.Success(weatherData)
            } else {
                Log.e("RetrofitWeatherRepository", "HTTP error: ${weatherResponse.code()} ${weatherResponse.message()}")
                Result.Error(HttpException(weatherResponse))
            }
        } catch (e: IOException) {
            Result.Error(NoNetworkException())
        } catch (e: Exception) {
            Log.e("RetrofitWeatherRepository", "Error fetching weather data", e)
            Result.Error(e)
        }
    }
}