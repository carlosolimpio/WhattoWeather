package com.olimpio.whattoweather.presentation.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olimpio.whattoweather.presentation.location.repository.LocationRepository
import com.olimpio.whattoweather.util.LatLng

class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {
    val locationLiveData: MutableLiveData<LatLng> = MutableLiveData()

    fun updateCurrentLocation() {
        locationLiveData.value = locationRepository.getDeviceLocation()
    }

    class LocationViewModelFactory(
        private val locationRepository: LocationRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LocationViewModel(locationRepository) as T
        }
    }
}