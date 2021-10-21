package com.olimpio.whattoweather.data.network.model

data class WeatherResponse(
    var temperature: Double,
    var maxTemp: Double,
    var minTemp: Double,
    var feelsLike: Double,
    var windSpeed: Double,
    var probPrecipitation: Double,
    var description: String,
    var iconCode: String
)