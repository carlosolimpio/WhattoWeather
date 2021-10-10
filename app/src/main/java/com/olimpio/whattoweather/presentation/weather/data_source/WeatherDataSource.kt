package com.olimpio.whattoweather.presentation.weather.data_source

import com.olimpio.whattoweather.data.network.response.WeatherResult

interface WeatherDataSource {
    fun getWeeklyWeather(lat: Double, lng: Double, weatherCallback: (result: WeatherResult) -> Unit)
}