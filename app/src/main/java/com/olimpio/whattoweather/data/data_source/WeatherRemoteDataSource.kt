package com.olimpio.whattoweather.data.data_source

import android.content.Context
import android.util.Log
import com.olimpio.whattoweather.data.network.api.RetrofitManager
import com.olimpio.whattoweather.data.network.model.WeatherResponse
import com.olimpio.whattoweather.data.network.response.WeatherForecast
import com.olimpio.whattoweather.data.network.response.APIResult
import com.olimpio.whattoweather.presentation.weather.data_source.WeatherDataSource
import com.olimpio.whattoweather.data.util.LocationConverter
import com.olimpio.whattoweather.util.City
import com.olimpio.whattoweather.util.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRemoteDataSource(private val context: Context) : WeatherDataSource {
    override fun getWeeklyWeather(city: City, responseCallback: (result: APIResult) -> Unit) {
        lateinit var currentWeatherResponse: WeatherResponse
        lateinit var weeklyWeatherResponse: List<WeatherResponse>

        val coord = parseLocationCoordinate(city, context)

        Log.d("olimpio", "getWeeklyWeather: $coord")

        RetrofitManager.getOpenWeatherService()
            .getCurrentWeatherForLocationCoordinates(coord.coord.latitude, coord.coord.longitude)
            .enqueue(object : Callback<WeatherForecast> {
                override fun onResponse(
                    call: Call<WeatherForecast>, response: Response<WeatherForecast>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            currentWeatherResponse = buildCurrentWeather(it)
                            weeklyWeatherResponse = buildWeeklyWeather(it, currentWeatherResponse)
                            responseCallback(APIResult.Success(weeklyWeatherResponse))
                        }
                    } else responseCallback(APIResult.ApiError(response.code()))
                }

                override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                    Log.d("olimpio", "onFailure: $t")
                    responseCallback(APIResult.ServerError)
                }
            })
    }

    private fun parseLocationCoordinate(city: City, context: Context) : City {
        var cityObject = City()

        when {
            city.name.isNotBlank() -> {
                cityObject.name = city.name
                cityObject.coord = LocationConverter.convertCityNameToLatLng(context, city.name)
            }
            LatLng.isValidCoord(city.coord.latitude, city.coord.longitude) -> {
                val coordinate = LatLng(city.coord.latitude, city.coord.longitude)
                cityObject.coord = coordinate
                cityObject.name = LocationConverter.convertLatLngToCityName(context, coordinate)
            }
        }

        return cityObject
    }


    private fun buildCurrentWeather(response: WeatherForecast): WeatherResponse {
        return WeatherResponse(
            response.hourly[0].temp.toDouble(),
            response.weekly[0].temp.maxTemp.toDouble(),
            response.weekly[0].temp.minTemp.toDouble(),
            response.hourly[0].feelsLike.toDouble(),
            response.hourly[0].windSpeed.toDouble(),
            response.hourly[0].probPrecipitation.toDouble(),
            response.hourly[0].weatherDescription[0].description,
            response.hourly[0].weatherDescription[0].icon
        )
    }

    private fun buildWeeklyWeather(
        response: WeatherForecast,
        currentWeatherResponse: WeatherResponse
    ): List<WeatherResponse> {
        val weekly = arrayListOf<WeatherResponse>(currentWeatherResponse)
        for (i in 1..6) {
            weekly.add(
                i, WeatherResponse(
                    response.weekly[i].temp.tempDay.toDouble(),
                    response.weekly[i].temp.maxTemp.toDouble(),
                    response.weekly[i].temp.minTemp.toDouble(),
                    response.weekly[i].feelsLike.feelsLike.toDouble(),
                    response.weekly[i].windSpeed.toDouble(),
                    response.weekly[i].probPrecipitation.toDouble(),
                    response.weekly[i].weatherDescription[0].description,
                    response.weekly[i].weatherDescription[0].icon
                )
            )
        }
        return weekly
    }
}