package com.rishirajput.data.api

import com.rishirajput.domain.model.Location
import com.rishirajput.domain.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service interface for accessing weather-related API endpoints.
 */
interface WeatherApiService {

    /**
     * Fetches the current weather for a given location.
     *
     * @param apiKey The API key for authentication.
     * @param query The location query (e.g., city name or coordinates).
     * @return A [Response] containing the current weather data.
     */
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Response<WeatherResponse>

    /**
     * Searches for locations matching the given query.
     *
     * @param apiKey The API key for authentication.
     * @param query The location query (e.g., city name or coordinates).
     * @return A [Response] containing a list of matching locations.
     */
    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Response<List<Location>>
}