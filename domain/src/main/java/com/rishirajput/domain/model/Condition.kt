package com.rishirajput.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val text: String,
    val icon: String
)