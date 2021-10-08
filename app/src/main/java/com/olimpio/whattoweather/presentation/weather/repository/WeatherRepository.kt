package com.olimpio.whattoweather.presentation.weather.repository

import com.olimpio.whattoweather.data.network.model.Weather
import com.olimpio.whattoweather.util.LatLng

interface WeatherRepository {
    fun getCurrentWeather(coordinate: LatLng): Weather
    fun getWeeklyWeather(coordinate: LatLng): List<Weather>
}