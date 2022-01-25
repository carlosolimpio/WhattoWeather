package com.olimpio.whattoweather.data.db.weather

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        fun getDatabase(context: Context): WeatherRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    WEATHER_TABLE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}