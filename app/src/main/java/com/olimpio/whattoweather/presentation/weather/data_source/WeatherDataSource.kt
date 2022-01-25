package com.olimpio.whattoweather.presentation.weather.data_source

import com.olimpio.whattoweather.data.util.DataResult
import com.olimpio.whattoweather.util.City

interface WeatherDataSource {
    fun getWeeklyWeather(city: City, weatherCallback: (result: DataResult) -> Unit)
}