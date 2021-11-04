package com.olimpio.whattoweather.data.util

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.olimpio.whattoweather.util.LatLng
import java.util.*

object LocationConverter {
    fun convertCityNameToLatLng(context: Context, cityName: String): LatLng {
        return if (Geocoder.isPresent()) {
            val geo = Geocoder(context, Locale.getDefault())
            val addr = geo.getFromLocationName(cityName, 1)
            LatLng(addr[0].latitude, addr[0].longitude)
        } else LatLng(0.0, 0.0) //error
    }

    fun convertLatLngToCityName(context: Context, coord: LatLng): String {
        return if (Geocoder.isPresent()) {
            val geo = Geocoder(context, Locale.getDefault())
            val addr = geo.getFromLocation(coord.latitude, coord.longitude, 1)
            addr[0].subAdminArea
        } else ""
    }
}
