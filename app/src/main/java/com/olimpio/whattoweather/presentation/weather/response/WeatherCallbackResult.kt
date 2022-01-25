package com.olimpio.whattoweather.presentation.weather.response

import com.olimpio.whattoweather.presentation.weather.model.Weather

sealed class WeatherCallbackResult {
    class Success(val weeklyWeatherResponse: List<Weather>) : WeatherCallbackResult()
    class Failure(val statusCode: Int) : WeatherCallbackResult()
    object OtherError : WeatherCallbackResult()
}