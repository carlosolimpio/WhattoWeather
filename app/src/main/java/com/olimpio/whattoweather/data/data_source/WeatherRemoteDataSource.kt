package com.olimpio.whattoweather.data.data_source

import android.util.Log
import com.olimpio.whattoweather.data.network.api.RetrofitManager
import com.olimpio.whattoweather.data.network.model.Weather
import com.olimpio.whattoweather.data.network.response.WeatherForecast
import com.olimpio.whattoweather.data.network.response.WeatherResult
import com.olimpio.whattoweather.presentation.weather.data_source.WeatherDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRemoteDataSource : WeatherDataSource {
    override fun getWeeklyWeather(lat: Double, lng: Double, weatherCallback: (result: WeatherResult) -> Unit) {
        lateinit var currentWeather: Weather
        var weeklyWeather: List<Weather>

        RetrofitManager.getOpenWeatherService()
            .getCurrentWeatherForLocationCoordinates(lat, lng)
            .enqueue(object : Callback<WeatherForecast> {
                override fun onResponse(
                    call: Call<WeatherForecast>, response: Response<WeatherForecast>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            currentWeather = buildCurrentWeather(it)
                            weeklyWeather = buildWeeklyWeather(it, currentWeather)
                            weatherCallback(WeatherResult.Success(weeklyWeather))
                        }
                    } else weatherCallback(WeatherResult.ApiError(response.code()))
                }

                override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                    Log.d("olimpio", "onFailure: $t")
                    weatherCallback(WeatherResult.ServerError)
                }
            })
    }

    private fun buildCurrentWeather(response: WeatherForecast): Weather {
        return Weather(
            response.hourly[0].temp.toDouble(),
            response.weekly[0].temp.maxTemp.toDouble(),
            response.weekly[0].temp.minTemp.toDouble(),
            response.hourly[0].feelsLike.toDouble(),
            response.hourly[0].windSpeed.toDouble(),
            response.hourly[0].probPrecipitation.toDouble(),
            response.hourly[0].weatherDescription[0].description
        )
    }

    private fun buildWeeklyWeather(
        response: WeatherForecast,
        currentWeather: Weather
    ): List<Weather> {
        val weekly = arrayListOf<Weather>(currentWeather)
        for (i in 1..6) {
            weekly.add(
                i, Weather(
                    response.weekly[i].temp.tempDay.toDouble(),
                    response.weekly[i].temp.maxTemp.toDouble(),
                    response.weekly[i].temp.minTemp.toDouble(),
                    response.weekly[i].feelsLike.feelsLike.toDouble(),
                    response.weekly[i].windSpeed.toDouble(),
                    response.weekly[i].probPrecipitation.toDouble(),
                    response.weekly[i].weatherDescription[0].description
                )
            )
        }
        return weekly
    }
}