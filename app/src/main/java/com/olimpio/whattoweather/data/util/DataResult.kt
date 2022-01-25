package com.olimpio.whattoweather.data.util

import com.olimpio.whattoweather.data.data_source.WeatherResponse

sealed class DataResult {
    class Success(val weeklyWeatherResponse: List<WeatherResponse>) : DataResult()
    class Error(val statusCode: Int) : DataResult()
    object OtherError : DataResult()
}