package com.olimpio.whattoweather.presentation.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.olimpio.whattoweather.databinding.ActivityMainBinding

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: WeatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        viewModel.weatherLiveData.observe(this) { weather ->
//            binding.textTest.text = weather.toString()
            Log.d("olimpio", "onCreate: $weather")
        }

        viewModel.weeklyWeatherLiveData.observe(this) { weeklyWeather ->
            Log.d("olimpio", "onCreate: $weeklyWeather")

        }

        viewModel.getCurrentWeather("recife")
        viewModel.getWeeklyWeather("recife")
    }


}