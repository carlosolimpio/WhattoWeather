package com.olimpio.whattoweather.presentation.weather.response

import com.olimpio.whattoweather.presentation.weather.model.Weather

sealed class WeatherResult {
    class Success(val weeklyWeatherResponse: List<Weather>) : WeatherResult()
    class ApiError(val statusCode: Int) : WeatherResult()
    object ServerError : WeatherResult()
}