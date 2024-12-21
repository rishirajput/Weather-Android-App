// WeatherData.kt
package com.rishirajput.weather.domain.model

data class WeatherData(
    val temperature: Double,
    val condition: String,
    val iconUrl: String,
    val humidity: Int,
    val uvIndex: Double,
    val feelsLike: Double
)