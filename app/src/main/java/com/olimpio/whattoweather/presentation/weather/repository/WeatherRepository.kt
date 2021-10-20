package com.olimpio.whattoweather.presentation.weather.repository

import com.olimpio.whattoweather.presentation.weather.response.WeatherResult

interface WeatherRepository {
    fun getWeeklyWeather(city: String, weatherCallback: (result: WeatherResult) -> Unit)
}