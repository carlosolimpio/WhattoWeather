package com.olimpio.whattoweather.data.network.response

import com.olimpio.whattoweather.data.network.model.Weather

sealed class WeatherResult {
    class Success(val weeklyWeather: List<Weather>) : WeatherResult()
    class ApiError(val statusCode: Int) : WeatherResult()
    object ServerError : WeatherResult()
}