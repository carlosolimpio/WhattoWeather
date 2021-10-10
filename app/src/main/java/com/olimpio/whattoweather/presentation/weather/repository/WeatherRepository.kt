package com.olimpio.whattoweather.presentation.weather.repository

import com.olimpio.whattoweather.data.network.model.Weather
import com.olimpio.whattoweather.data.network.response.WeatherResult
import com.olimpio.whattoweather.util.LatLng

interface WeatherRepository {
    fun getWeeklyWeather(coordinate: LatLng, weatherCallback: (result: WeatherResult) -> Unit)
}