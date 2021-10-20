package com.olimpio.whattoweather.presentation.location.data_source

import com.olimpio.whattoweather.util.LatLng

interface LocationDataSource {
    fun getCurrentLocation(): LatLng
}