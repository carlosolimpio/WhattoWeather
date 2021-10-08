package com.olimpio.whattoweather.data.network.response

import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("hourly") val hourly: List<HourlyWeather>,
    @SerializedName("daily") val weekly: List<WeeklyWeather>
)
