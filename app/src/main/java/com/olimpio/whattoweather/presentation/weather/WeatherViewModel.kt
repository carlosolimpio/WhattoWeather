package com.olimpio.whattoweather.presentation.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olimpio.whattoweather.R
import com.olimpio.whattoweather.data.network.response.WeatherResult
import com.olimpio.whattoweather.data.network.response.WeatherResult.*
import com.olimpio.whattoweather.presentation.weather.model.Weather
import com.olimpio.whattoweather.presentation.weather.repository.WeatherRepository
import com.olimpio.whattoweather.util.LocationConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

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
                    weeklyWeatherLiveData.value = parseWeatherResponse(city, result.weeklyWeather)
                }
                is ApiError -> {
                    // show error dialog
                }
                is ServerError -> {
                    // show error dialog
                }
            }
        }
    }

    private fun parseWeatherResponse(
        city: String,
        weeklyWeather: List<com.olimpio.whattoweather.data.network.model.Weather>
    ): List<Weather> {
        val weatherList = arrayListOf<Weather>()
        weeklyWeather.forEach { weather ->
            weatherList.add(
                Weather(
                    date = getCurrentDate(), // need improvement for the next 7 days
                    city = city.capitalize(),
                    description = weather.description.capitalize(),
                    temperature = weather.temperature.roundToInt().toString() + "º",
                    feelsLike = "Sensação Térmica " + weather.feelsLike.roundToInt().toString() + "º",
                    precipitation = "${(weather.probPrecipitation * 100).roundToInt()}%",
                    tempMax = weather.maxTemp.roundToInt().toString() + "º",
                    tempMin = weather.minTemp.roundToInt().toString() + "º",
                    windSpeed = weather.windSpeed.roundToInt().toString() + " km/h",
                    icon = getWeatherIcon()
                )
            )
        }
        return weatherList
    }

    //TODO: need to calculate the weather icon
    private fun getWeatherIcon(): Int {
        return R.drawable.icon_bikini
    }

    //TODO: need to implement the dates for the next 7 days
    private fun getCurrentDate(): String {
        return ZonedDateTime.now().format(
                DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM")
                    .withLocale(Locale("pt", "BR"))
        ).capitalize()
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