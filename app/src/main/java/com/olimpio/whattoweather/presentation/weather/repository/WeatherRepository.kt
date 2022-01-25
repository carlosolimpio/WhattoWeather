package com.olimpio.whattoweather.presentation.weather.repository

import com.olimpio.whattoweather.presentation.weather.response.WeatherCallbackResult
import com.olimpio.whattoweather.util.City

interface WeatherRepository {
    fun getWeeklyWeather(city: City, weatherCallback: (result: WeatherCallbackResult) -> Unit)
}