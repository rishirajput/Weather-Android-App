package com.rishirajput.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    val temp_c: Double,
    val condition: Condition,
    val humidity: Int,
    val uv: Double,
    val feelslike_c: Double
)
