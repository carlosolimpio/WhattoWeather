package com.olimpio.whattoweather.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeeklyWeather(
    @SerializedName("temp") val temp: Temperature,
    @SerializedName("feels_like") val feelsLike: FeelsLike,
    @SerializedName("wind_speed") val windSpeed: String,
    @SerializedName("weather") val weatherDescription: List<WeatherDescription>,
    @SerializedName("pop") val probPrecipitation: String
)

data class Temperature(
    @SerializedName("day") val tempDay: String,
    @SerializedName("min") val minTemp: String,
    @SerializedName("max") val maxTemp: String
)

data class FeelsLike(
    @SerializedName("day") val feelsLike: String
)