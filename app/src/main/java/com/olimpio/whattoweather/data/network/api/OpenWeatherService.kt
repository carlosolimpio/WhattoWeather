package com.olimpio.whattoweather.data.network.api

import com.olimpio.whattoweather.data.network.api.Constants.OPEN_WEATHER_KEY
import com.olimpio.whattoweather.data.network.dto.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("onecall")
    fun getCurrentWeatherForLocationCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") measureUnit: String = "metric",
        @Query("lang") queryLanguage: String = "pt_br",
        @Query("exclude") exclude: String = "current,minutely",
        @Query("appid") apiKey: String = OPEN_WEATHER_KEY,
    ): Call<WeatherForecast>
}