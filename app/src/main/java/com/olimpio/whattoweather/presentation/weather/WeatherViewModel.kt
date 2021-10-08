package com.olimpio.whattoweather.presentation.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.olimpio.whattoweather.data.network.model.Weather
import com.olimpio.whattoweather.data.repository.WeatherRepositoryImpl
import com.olimpio.whattoweather.presentation.weather.repository.WeatherRepository
import com.olimpio.whattoweather.util.LocationConverter

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    val weatherLiveData: MutableLiveData<Weather> = MutableLiveData()
    val weeklyWeatherLiveData: MutableLiveData<List<Weather>> = MutableLiveData()

    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()

    fun getCurrentWeather(city: String) {
        val coord = parseCoordinateFromCityName(city)
        weatherLiveData.value = weatherRepository.getCurrentWeather(coord)
    }

    fun getWeeklyWeather(city: String) {
        val coord = parseCoordinateFromCityName(city)
        weeklyWeatherLiveData.value = weatherRepository.getWeeklyWeather(coord)
    }

    private fun parseCoordinateFromCityName(city: String) =
        LocationConverter.convertCityNameToLatLng(getApplication(), city)
}