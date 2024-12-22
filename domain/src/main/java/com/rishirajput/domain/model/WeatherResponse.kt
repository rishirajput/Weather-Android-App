package com.rishirajput.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val current: CurrentWeather
)