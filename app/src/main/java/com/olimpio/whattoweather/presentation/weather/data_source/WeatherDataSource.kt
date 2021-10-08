package com.olimpio.whattoweather.presentation.weather.data_source

import com.olimpio.whattoweather.data.network.model.Weather

interface WeatherDataSource {
    fun getWeeklyWeather(lat: Double, lng: Double): List<Weather>
}