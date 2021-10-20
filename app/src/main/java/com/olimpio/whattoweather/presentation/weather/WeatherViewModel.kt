package com.olimpio.whattoweather.presentation.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olimpio.whattoweather.presentation.weather.model.Weather
import com.olimpio.whattoweather.presentation.weather.repository.WeatherRepository
import com.olimpio.whattoweather.presentation.weather.response.WeatherResult.*
import com.olimpio.whattoweather.util.City

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    val weeklyWeatherLiveData: MutableLiveData<List<Weather>> = MutableLiveData()

    fun getWeeklyWeather(city: City) {
        weatherRepository.getWeeklyWeather(city) { result ->
            when (result) {
                is Success -> {
                    weeklyWeatherLiveData.value = result.weeklyWeatherResponse
                }
                is ApiError -> {

                }
                is ServerError -> {

                }
            }
        }
    }

    class WeatherViewModelFactory(
        private val weatherRepository: WeatherRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(weatherRepository) as T
        }
    }
}