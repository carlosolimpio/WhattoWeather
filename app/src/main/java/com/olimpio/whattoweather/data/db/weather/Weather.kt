package com.olimpio.whattoweather.data.db.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

const val WEATHER_TABLE_NAME = "weather_table"

@Entity(tableName = WEATHER_TABLE_NAME)
data class Weather(
    @PrimaryKey
    val cityName: String,
    var temperature: Double,
    var maxTemp: Double,
    var minTemp: Double,
    var feelsLike: Double,
    var windSpeed: Double,
    var probPrecipitation: Double,
    var description: String,
    var iconCode: String
)