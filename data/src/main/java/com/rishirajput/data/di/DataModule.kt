package com.rishirajput.data.di


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rishirajput.data.serializer.WeatherDataSerializer
import com.rishirajput.data.api.WeatherApiService
import com.rishirajput.data.repository.DataStoreRepository
import com.rishirajput.data.repository.RetrofitWeatherRepository
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.repository.LocalStorageRepository
import com.rishirajput.domain.repository.WeatherRepository
import com.rishirajput.domain.usecase.FetchWeatherDataUseCase
import com.rishirajput.domain.usecase.GetSelectedWeatherDataUseCase
import com.rishirajput.domain.usecase.StoreWeatherDataUseCase
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit


val dataModule = module {
    single { provideRetrofit().create(WeatherApiService::class.java) }
    single<WeatherRepository> { RetrofitWeatherRepository(get()) }
    single<DataStore<WeatherData?>> { provideDataStore(get()) }
    single<LocalStorageRepository> { DataStoreRepository(get()) }
    single { FetchWeatherDataUseCase(get()) }
    single { StoreWeatherDataUseCase(get()) }
    single { GetSelectedWeatherDataUseCase(get()) }
}

@OptIn(ExperimentalSerializationApi::class)
fun provideRetrofit(): Retrofit {
    val contentType = "application/json".toMediaType()
    val json = Json { ignoreUnknownKeys = true }

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .client(client)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}

fun provideDataStore(context: Context): DataStore<WeatherData?> {
    return DataStoreFactory.create(
        serializer = WeatherDataSerializer,
        produceFile = { context.dataStoreFile("weather_data.pb") }
    )
}

