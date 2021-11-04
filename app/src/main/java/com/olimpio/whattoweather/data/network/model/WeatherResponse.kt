package com.olimpio.whattoweather.data.network.model

import com.olimpio.whattoweather.util.City

data class WeatherResponse(
    var city: City,
    var temperature: Double,
    var maxTemp: Double,
    var minTemp: Double,
    var feelsLike: Double,
    var windSpeed: Double,
    var probPrecipitation: Double,
    var description: String,
    var iconCode: String
)