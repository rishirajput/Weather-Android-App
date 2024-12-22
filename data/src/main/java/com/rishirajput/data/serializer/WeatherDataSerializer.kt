package com.rishirajput.data.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.rishirajput.domain.model.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

/**
 * Serializer for WeatherData using Kotlinx Serialization.
 */
object WeatherDataSerializer : Serializer<WeatherData?> {
    override val defaultValue: WeatherData? = null

    override suspend fun readFrom(input: InputStream): WeatherData? {
        return try {
            val json = input.readBytes().decodeToString()
            Json.decodeFromString<WeatherData>(json)
        } catch (exception: Exception) {
            throw CorruptionException("Cannot read JSON.", exception)
        }
    }

    override suspend fun writeTo(t: WeatherData?, output: OutputStream) {
        val json = Json.encodeToString(t)
        withContext(Dispatchers.IO) {
            output.write(json.encodeToByteArray())
        }
    }
}