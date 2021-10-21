package com.olimpio.whattoweather.data.data_source

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.olimpio.whattoweather.presentation.location.data_source.LocationDataSource
import com.olimpio.whattoweather.util.LatLng

class LocationDataSourceImpl(private val fusedLocationClient: FusedLocationProviderClient) : LocationDataSource {

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): LatLng {
        lateinit var coord: LatLng

        fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, null)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            coord = if (location != null) {
                Log.d("olimpio", "LocationDataSourceImpl: lat=${location.latitude} + lng=${location.longitude}")
                LatLng(location.latitude, location.longitude)
            } else {
                // null = location is off or device has never recorded a location or google play services restarted
                Log.d("olimpio", "onCreate: location = null")
                LatLng(0.0,0.0)
            }
        }

        return coord
    }
}