package com.olimpio.whattoweather.data.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val OPEN_WEATHER_URL =  "https://api.openweathermap.org/data/2.5/"

object RetrofitManager {
    private fun getRetrofitInstance(path: String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getOpenWeatherService(): OpenWeatherService =
        getRetrofitInstance(OPEN_WEATHER_URL).create(OpenWeatherService::class.java)
}