package com.olimpio.whattoweather.presentation.weather.data_source

import com.olimpio.whattoweather.data.network.response.APIResult

interface WeatherDataSource {
    fun getWeeklyWeather(city: String, weatherCallback: (result: APIResult) -> Unit)
}