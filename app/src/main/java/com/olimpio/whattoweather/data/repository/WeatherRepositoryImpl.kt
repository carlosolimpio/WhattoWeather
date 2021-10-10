package com.olimpio.whattoweather.data.repository

import com.olimpio.whattoweather.data.network.response.WeatherResult
import com.olimpio.whattoweather.presentation.weather.data_source.WeatherDataSource
import com.olimpio.whattoweather.presentation.weather.repository.WeatherRepository
import com.olimpio.whattoweather.util.LatLng

class WeatherRepositoryImpl(private val remoteDataSource: WeatherDataSource) : WeatherRepository {
    override fun getWeeklyWeather(coordinate: LatLng, weatherCallback: (result: WeatherResult) -> Unit) =
        remoteDataSource.getWeeklyWeather(coordinate.latitude, coordinate.longitude, weatherCallback)
}