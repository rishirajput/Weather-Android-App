package com.rishirajput.weather.domain.model

data class WeatherData(
    val locationName: String,
    val temperature: Double,
    val condition: String,
    val icon: String,
    val humidity: Int,
    val uvIndex: Double,
    val feelsLike: Double
)