package com.olimpio.whattoweather.data.repository

import com.olimpio.whattoweather.R
import com.olimpio.whattoweather.data.data_source.WeatherResponse
import com.olimpio.whattoweather.data.util.DataResult
import com.olimpio.whattoweather.presentation.weather.data_source.WeatherDataSource
import com.olimpio.whattoweather.presentation.weather.model.Weather
import com.olimpio.whattoweather.presentation.weather.repository.WeatherRepository
import com.olimpio.whattoweather.presentation.weather.response.WeatherCallbackResult
import com.olimpio.whattoweather.presentation.weather.response.WeatherCallbackResult.*
import com.olimpio.whattoweather.util.City
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

class WeatherRepositoryImpl(private val remoteDataSource: WeatherDataSource) : WeatherRepository {
    override fun getWeeklyWeather(city: City, weatherCallback: (result: WeatherCallbackResult) -> Unit) =
        remoteDataSource.getWeeklyWeather(city) { response ->
            when (response) {
                is DataResult.Success -> {
                    weatherCallback(
                        Success(parseWeatherResponse(response.weeklyWeatherResponse)))
                }
                is DataResult.Error -> {
                    weatherCallback(Failure(response.statusCode))
                }
                is DataResult.OtherError -> {
                    weatherCallback(OtherError)
                }
            }
        }

    private fun parseWeatherResponse(
        weeklyWeatherResponse: List<WeatherResponse>
    ): List<Weather> {
        val weatherList = arrayListOf<Weather>()
        weeklyWeatherResponse.forEachIndexed { index, weather ->
            weatherList.add(
                Weather(
                    date = getCurrentDate(index.toLong()),
                    cityName = weather.city.name.capitalize(),
                    description = weather.description.capitalize(),
                    temperature = weather.temperature.roundToInt().toString(),
                    feelsLike = weather.feelsLike.roundToInt().toString(),
                    precipitation = (weather.probPrecipitation * 100).roundToInt().toString(),
                    tempMax = weather.maxTemp.roundToInt().toString(),
                    tempMin = weather.minTemp.roundToInt().toString(),
                    windSpeed = weather.windSpeed.roundToInt().toString(),
                    icon = getWeatherIcon(weather.description),
                    color = getIconColor(weather.iconCode, weather.description)
                )
            )
        }
        return weatherList
    }

    private fun getCurrentDate(dayCount: Long): String {
        return ZonedDateTime.now().plusDays(dayCount).format(
            DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM")
                .withLocale(Locale("pt", "BR"))
        ).capitalize()
    }

    //TODO: need to calculate the weather icon
    private fun getWeatherIcon(description: String): Int {
        val temp = arrayOf(R.drawable.ic_cone_biquini_1, R.drawable.ic_cachecol, R.drawable.ic_galocha, R.drawable.ic_moletom)

        return temp.random()
    }

    private fun getIconColor(code: String, description: String): Int {
        return if (code.contains("d")) {
            if (rainy.contains(description)) R.color.light_grey_blue
            else R.color.pale_gold
        } else {
            return R.color.perry_winkle
        }
    }

    companion object {
        private val rainy = arrayListOf("chuva leve", "chuva moderada")
//        private val sunny = arrayListOf("céu limpo", "")
    }
}