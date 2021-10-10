package com.olimpio.whattoweather.presentation.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.olimpio.whattoweather.data.data_source.WeatherRemoteDataSource
import com.olimpio.whattoweather.data.repository.WeatherRepositoryImpl
import com.olimpio.whattoweather.databinding.ActivityMainBinding
import com.olimpio.whattoweather.presentation.weather.data_source.WeatherDataSource

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = WeatherRepositoryImpl(WeatherRemoteDataSource())

        val viewModel = ViewModelProvider(
                this,
                WeatherViewModel.WeatherViewModelFactory(application, repo))
            .get(WeatherViewModel::class.java)

        viewModel.weeklyWeatherLiveData.observe(this) { weeklyWeather ->
            Log.d("olimpio", "onCreate: $weeklyWeather")
        }

        viewModel.getWeeklyWeather("recife")
    }


}