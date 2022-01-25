package com.olimpio.whattoweather.data.data_source

import com.olimpio.whattoweather.data.db.weather.Weather
import com.olimpio.whattoweather.data.db.weather.WeatherDao
import com.olimpio.whattoweather.data.util.DataResult
import com.olimpio.whattoweather.presentation.weather.data_source.WeatherDataSource
import com.olimpio.whattoweather.util.City

class WeatherLocalDataSource(private val weatherDao: WeatherDao) : WeatherDataSource {
    private lateinit var response: WeatherResponse

    override fun getWeeklyWeather(city: City, weatherCallback: (result: DataResult) -> Unit) {
        response = parseWeatherResponse(weatherDao.getWeather(city.name))
        weatherCallback(DataResult.Success(listOf(response)))
    }

    private fun parseWeatherResponse(weather: Weather): WeatherResponse {
        return WeatherResponse(
            City(weather.cityName),
            weather.temperature,
            weather.maxTemp,
            weather.minTemp,
            weather.feelsLike,
            weather.windSpeed,
            weather.probPrecipitation,
            weather.description,
            weather.iconCode
        )
    }
}