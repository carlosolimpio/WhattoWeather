package com.olimpio.whattoweather.util

import android.content.Context
import android.location.Geocoder
import java.util.*

data class LatLng(val latitude: Double, val longitude: Double)

object LocationConverter {
    fun convertCityNameToLatLng(context: Context, cityName: String): LatLng {
        return if (Geocoder.isPresent()) {
            val geo = Geocoder(context, Locale.getDefault())
            val addr = geo.getFromLocationName(cityName, 1)
            LatLng(addr[0].latitude, addr[0].longitude)
        } else LatLng(0.0, 0.0) //error
    }
}
