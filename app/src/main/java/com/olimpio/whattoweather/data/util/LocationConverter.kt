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
            LatLng(parseFloatPointer(addr[0].latitude), parseFloatPointer(addr[0].longitude))
        } else LatLng(0.0, 0.0) //error
    }

    fun convertLatLngToCityName(context: Context, coord: LatLng): String {
        return if (Geocoder.isPresent()) {
            val geo = Geocoder(context, Locale.getDefault())
            val addr = geo.getFromLocation(
                parseFloatPointer(coord.latitude), parseFloatPointer(coord.longitude), 1)
            Log.d("olimpio", "convertLatLngToCityName: $addr")
            if (addr[0].subAdminArea != null) addr[0].subAdminArea else addr[0].adminArea
        } else ""
    }

    private fun parseFloatPointer(value: Double) = "%.5f".format(value).toDouble()
}
