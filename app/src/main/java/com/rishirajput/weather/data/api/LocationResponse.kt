package com.rishirajput.weather.data.api

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val url: String
)