package com.olimpio.whattoweather.presentation.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olimpio.whattoweather.data.network.model.Weather
import com.olimpio.whattoweather.data.network.response.WeatherResult
import com.olimpio.whattoweather.data.network.response.WeatherResult.Success
import com.olimpio.whattoweather.data.repository.WeatherRepositoryImpl
import com.olimpio.whattoweather.presentation.weather.repository.WeatherRepository
import com.olimpio.whattoweather.util.LocationConverter

class WeatherViewModel(
    application: Application,
    private val weatherRepository: WeatherRepository
) : AndroidViewModel(application) {
    val weeklyWeatherLiveData: MutableLiveData<List<Weather>> = MutableLiveData()

    fun getWeeklyWeather(city: String) {
        val coord = parseCoordinateFromCityName(city)
        weatherRepository.getWeeklyWeather(coord) { result: WeatherResult ->
            when (result) {
                is Success -> {
                    weeklyWeatherLiveData.value = result.weeklyWeather
                }
            }
        }
    }

    private fun parseCoordinateFromCityName(city: String) =
        LocationConverter.convertCityNameToLatLng(getApplication(), city)

    class WeatherViewModelFactory(
        private val application: Application,
        private val weatherRepository: WeatherRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(application, weatherRepository) as T
        }
    }
}