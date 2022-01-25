package com.olimpio.whattoweather.data.db.weather

import androidx.room.*

@Dao
interface WeatherDao {
    @Query("SELECT * FROM $WEATHER_TABLE_NAME WHERE cityName = :cityName")
    fun getWeather(cityName: String): Weather

    @Query("SELECT * FROM $WEATHER_TABLE_NAME")
    fun getAllWeathers(): List<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: Weather)

    @Delete
    fun deleteWeather(cityName: String)

    @Query("DELETE FROM $WEATHER_TABLE_NAME")
    fun deleteAllWeathers()
}