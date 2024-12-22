package com.rishirajput.data.api

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val current: CurrentWeather
)

@Serializable
data class CurrentWeather(
    val temp_c: Double,
    val condition: Condition,
    val humidity: Int,
    val uv: Double,
    val feelslike_c: Double
)

@Serializable
data class Condition(
    val text: String,
    val icon: String
)