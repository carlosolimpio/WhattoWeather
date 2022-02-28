package com.olimpio.whattoweather.presentation.weather.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.olimpio.whattoweather.presentation.weather.model.Weather
import com.olimpio.whattoweather.presentation.weather.repository.WeatherRepository
import com.olimpio.whattoweather.presentation.weather.response.WeatherCallbackResult.*
import com.olimpio.whattoweather.util.City
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    val remoteWeeklyWeatherLiveData: MutableLiveData<List<Weather>> by lazy {
        MutableLiveData()
    }

    val localWeeklyWeatherLiveData: MutableLiveData<Weather> by lazy { MutableLiveData() }

    fun getWeeklyWeather() {
        viewModelScope.launch {
            val weatherList = weatherRepository.getWeeklyWeather(City())
        }
    }


    fun getWeeklyWeather(city: City) {
        weatherRepository.getWeeklyWeather(city) { result ->
            when (result) {
                is Success -> {
                    remoteWeeklyWeatherLiveData.value = result.weeklyWeatherResponse
                }
                is Failure -> {

                }
                is OtherError -> {

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