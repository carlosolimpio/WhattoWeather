package com.olimpio.whattoweather.data.repository

import com.olimpio.whattoweather.presentation.location.data_source.LocationDataSource
import com.olimpio.whattoweather.presentation.location.repository.LocationRepository
import com.olimpio.whattoweather.util.LatLng

class LocationRepositoryImpl(private val locDataSource: LocationDataSource) : LocationRepository {
    override fun getDeviceLocation(locationCallback: (coord: LatLng) -> Unit) {
        locDataSource.getCurrentLocation { coord ->
            locationCallback(coord)
        }
    }
}