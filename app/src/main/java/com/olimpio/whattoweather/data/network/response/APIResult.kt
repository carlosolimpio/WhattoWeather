package com.olimpio.whattoweather.data.network.response

import com.olimpio.whattoweather.data.network.model.WeatherResponse


sealed class WeatherResult {
    class Success(val weeklyWeatherResponse: List<WeatherResponse>) : WeatherResult()
    class ApiError(val statusCode: Int) : WeatherResult()
    object ServerError : WeatherResult()
}