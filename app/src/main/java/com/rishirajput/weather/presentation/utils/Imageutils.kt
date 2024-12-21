package com.rishirajput.weather.presentation.utils

fun updateImageSizeInUrl(url: String, newSize: Int): String {
    val regex = Regex("""/(\d+)x(\d+)/""")
    return regex.replace(url) { matchResult ->
        "/${newSize}x${newSize}/"
    }
}