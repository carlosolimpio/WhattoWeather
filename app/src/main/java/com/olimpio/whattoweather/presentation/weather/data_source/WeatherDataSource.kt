package com.olimpio.whattoweather.presentation.weather.data_source

import com.olimpio.whattoweather.data.network.response.APIResult
import com.olimpio.whattoweather.util.City

interface WeatherDataSource {
    fun getWeeklyWeather(city: City, weatherCallback: (result: APIResult) -> Unit)
}