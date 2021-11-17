package com.olimpio.whattoweather.presentation.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olimpio.whattoweather.databinding.FragmentWeeklyWeatherBinding
import com.olimpio.whattoweather.databinding.LayoutWeekDayBinding
import com.olimpio.whattoweather.presentation.weather.model.Weather

class WeeklyWeatherFragment(val weeklyWeather: List<Weather>) : Fragment() {
    private lateinit var binding: FragmentWeeklyWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeeklyWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(weeklyWeather)
    }

    private fun initViews(weeklyWeather: List<Weather>) {
        Log.d("olimpio", "initViews: $weeklyWeather")
        binding.apply {
            setUpView(day1, weeklyWeather[0])
            setUpView(day2, weeklyWeather[1])
            setUpView(day3, weeklyWeather[2])
            setUpView(day4, weeklyWeather[3])
            setUpView(day5, weeklyWeather[4])
            setUpView(day6, weeklyWeather[5])
            setUpView(day7, weeklyWeather[6])
        }
    }

    private fun setUpView(layout: LayoutWeekDayBinding, weather: Weather) {
        layout.apply {
            textWeekDay.text = weather.date.substring(0, 3).toUpperCase()
            textWeekTemperature.text = weather.temperature + "º"
        }
    }
}