package com.olimpio.whattoweather.data.repository

import com.olimpio.whattoweather.data.data_source.WeatherRemoteDataSource
import com.olimpio.whattoweather.presentation.weather.repository.WeatherRepository
import com.olimpio.whattoweather.util.LatLng

class WeatherRepositoryImpl : WeatherRepository {
    private val remoteDataSource = WeatherRemoteDataSource()

    override fun getCurrentWeather(coordinate: LatLng) =
        remoteDataSource.getWeeklyWeather(coordinate.latitude, coordinate.longitude)[0]

    override fun getWeeklyWeather(coordinate: LatLng) =
        remoteDataSource.getWeeklyWeather(coordinate.latitude, coordinate.longitude)
}