package com.olimpio.whattoweather.presentation.weather.model

import com.olimpio.whattoweather.util.LatLng

data class Weather(
    var date: String,
    var cityName: String,
    var description: String,
    var temperature: String,
    var feelsLike: String,
    var precipitation: String,
    var tempMax: String,
    var tempMin: String,
    var windSpeed: String,
    var icon: Int,
    var color: Int
)