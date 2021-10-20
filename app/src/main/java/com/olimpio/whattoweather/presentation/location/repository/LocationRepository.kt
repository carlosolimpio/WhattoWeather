package com.olimpio.whattoweather.presentation.location.repository

import com.olimpio.whattoweather.util.LatLng

interface LocationRepository {
    fun getDeviceLocation(): LatLng
}