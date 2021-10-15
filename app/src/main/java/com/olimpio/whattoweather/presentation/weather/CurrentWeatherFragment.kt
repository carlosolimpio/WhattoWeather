package com.olimpio.whattoweather.presentation.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.olimpio.whattoweather.data.data_source.WeatherRemoteDataSource
import com.olimpio.whattoweather.data.repository.WeatherRepositoryImpl
import com.olimpio.whattoweather.databinding.FragmentCurrentWeatherBinding
import com.olimpio.whattoweather.presentation.weather.model.Weather
import kotlin.math.roundToInt

class CurrentWeatherFragment : Fragment() {
    private lateinit var binding: FragmentCurrentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = WeatherRepositoryImpl(WeatherRemoteDataSource())

        val viewModel = ViewModelProvider(
            this,
            WeatherViewModel.WeatherViewModelFactory(requireActivity().application, repo))
            .get(WeatherViewModel::class.java)

        val city = "chicago"

        viewModel.weeklyWeatherLiveData.observe(viewLifecycleOwner) { weeklyWeather ->
            Log.d("olimpio", "onCreate: $weeklyWeather")
            setUpCurrentWeatherViews(city, weeklyWeather[0])
        }

        viewModel.getWeeklyWeather(city)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpCurrentWeatherViews(city: String, weather: Weather) {
        binding.apply {
            textDate.text = weather.date
            textCityName.text = weather.city
            textWeatherDescription.text = weather.description
            textTemperature.text = weather.temperature
            textPrecipitation.text = weather.precipitation
            textFeelsLike.text = weather.feelsLike
            textTempMaxMin.text = weather.tempMax + "/" + weather.tempMin
            textWindSpeed.text = weather.windSpeed
            iconClothe.setImageResource(weather.icon)
        }
    }
}