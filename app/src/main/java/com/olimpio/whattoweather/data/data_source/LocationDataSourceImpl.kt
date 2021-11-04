package com.olimpio.whattoweather.data.data_source

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.olimpio.whattoweather.presentation.location.data_source.LocationDataSource
import com.olimpio.whattoweather.util.LatLng

class LocationDataSourceImpl(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationDataSource {

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(locationCallback: (coord: LatLng) -> Unit) {
        fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, null)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                locationCallback(LatLng(location.latitude, location.longitude))
            } else {
                // null = location is off
                // or device has never recorded a location
                // or google play services restarted
                Log.d("olimpio", "getCurrentLocation: NULL")
                locationCallback(LatLng(0.0,0.0))
            }
        }
    }
}