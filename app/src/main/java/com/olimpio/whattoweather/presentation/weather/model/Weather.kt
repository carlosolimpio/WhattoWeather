package com.olimpio.whattoweather.presentation.weather.model

data class Weather(
    var date: String,
    var city: String,
    var description: String,
    var temperature: String,
    var feelsLike: String,
    var precipitation: String,
    var tempMax: String,
    var tempMin: String,
    var windSpeed: String,
    var icon: Int
)