package com.olimpio.whattoweather.presentation.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.LocationServices
import com.olimpio.whattoweather.R
import com.olimpio.whattoweather.data.data_source.LocationDataSourceImpl
import com.olimpio.whattoweather.data.data_source.WeatherRemoteDataSourceImpl
import com.olimpio.whattoweather.data.repository.LocationRepositoryImpl
import com.olimpio.whattoweather.data.repository.WeatherRepositoryImpl
import com.olimpio.whattoweather.databinding.FragmentCurrentWeatherBinding
import com.olimpio.whattoweather.presentation.location.LocationViewModel
import com.olimpio.whattoweather.presentation.weather.model.Weather
import com.olimpio.whattoweather.util.City

class CurrentWeatherFragment : Fragment() {
    private lateinit var binding: FragmentCurrentWeatherBinding

    private lateinit var locationViewModel: LocationViewModel
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissions()
        initViewModels()
        observeViewModels()
        locationViewModel.updateCurrentLocation()
    }

    private fun initViewModels() {
        // TODO: create a Repository and DataSource providers
        val locRepository = LocationRepositoryImpl(
            LocationDataSourceImpl(LocationServices.getFusedLocationProviderClient(requireActivity()))
        )
        val weatherRepository = WeatherRepositoryImpl(WeatherRemoteDataSourceImpl(requireContext()))

        locationViewModel = ViewModelProvider(
            this,
            LocationViewModel.LocationViewModelFactory(locRepository))
            .get(LocationViewModel::class.java)

        weatherViewModel = ViewModelProvider(
            this,
            WeatherViewModel.WeatherViewModelFactory(weatherRepository))
            .get(WeatherViewModel::class.java)
    }

    private fun observeViewModels() {
        // Observe
        locationViewModel.locationLiveData.observe(viewLifecycleOwner) { coord ->
            Log.d("olimpio", "onViewCreated: coordenadas=${coord}")
            weatherViewModel.getWeeklyWeather(City("", coord))
        }

        weatherViewModel.weeklyWeatherLiveData.observe(viewLifecycleOwner) { weeklyWeather ->
            Log.d("olimpio", "onCreate: $weeklyWeather")
            setUpCurrentWeatherViews(weeklyWeather[0])

            val weeklyFragment = WeeklyWeatherFragment(weeklyWeather)
            setUpWeeklyWeatherViews(weeklyWeather, weeklyFragment)
        }
    }

    private fun setUpCurrentWeatherViews(weather: Weather) {
        binding.apply {
            textDate.text = weather.date
            textCityName.text = weather.cityName
            textWeatherDescription.text = weather.description
            textTemperature.text = weather.temperature
            textPrecipitation.text = weather.precipitation
            textFeelsLikeValue.text = weather.feelsLike
            textTempMax.text = weather.tempMax
            textTempMin.text = weather.tempMin
            textWindSpeed.text = weather.windSpeed
            iconClothe.setImageResource(weather.icon)

//            textCityName.setOnClickListener {  }
        }
    }

    private fun setUpWeeklyWeatherViews(weatherList: List<Weather>, weeklyFragment: WeeklyWeatherFragment) {
        binding.switchWeeklyWeather.setOnCheckedChangeListener { _, isChecked ->
            Log.d("olimpio", "setUpWeeklyWeatherViews: checked = $isChecked")

            if (isChecked) showWeeklyWeatherFragment(weeklyFragment)
            else hideWeeklyWeatherFragment(weeklyFragment)
        }
    }

    private fun showWeeklyWeatherFragment(fragment: WeeklyWeatherFragment) {
        Log.d("olimpio", "showWeeklyWeatherFragment")
        if (!fragment.isAdded) {
            childFragmentManager.beginTransaction()
                .add(R.id.week_fragment_container, fragment)
                .commit()
        } else {
            childFragmentManager.beginTransaction().show(fragment).commit()
        }
    }

    private fun hideWeeklyWeatherFragment(fragment: WeeklyWeatherFragment) {
        Log.d("olimpio", "hideWeeklyWeatherFragment")
        childFragmentManager.beginTransaction().hide(fragment).commit()
    }

    private fun checkPermissions() {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.d("olimpio", "onViewCreated: permission granted")

            } else {
                Log.d("olimpio", "onViewCreated: NOT permission granted")
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("olimpio", "onCreate: nao deu permissão")
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            return
        }
    }
}