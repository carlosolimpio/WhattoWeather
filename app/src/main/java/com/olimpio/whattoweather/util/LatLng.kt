package com.olimpio.whattoweather.util

data class LatLng(val latitude: Double = 0.0, val longitude: Double = 0.0) {

    companion object {
        fun isValidCoord(lat: Double, lng: Double) = lat != 0.0 && lng != 0.0
    }
}
