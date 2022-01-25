package com.olimpio.whattoweather.data.network.dto

import com.google.gson.annotations.SerializedName

data class HourlyWeather(
    @SerializedName("temp") val temp: String,
    @SerializedName("feels_like") val feelsLike: String,
    @SerializedName("wind_speed") val windSpeed: String,
    @SerializedName("weather") val weatherDescription: List<WeatherDescription>,
    @SerializedName("pop") val probPrecipitation: String
)