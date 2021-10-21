package com.olimpio.whattoweather.data.network.response

import com.olimpio.whattoweather.data.network.model.WeatherResponse


sealed class APIResult {
    class Success(val weeklyWeatherResponse: List<WeatherResponse>) : APIResult()
    class ApiError(val statusCode: Int) : APIResult()
    object ServerError : APIResult()
}