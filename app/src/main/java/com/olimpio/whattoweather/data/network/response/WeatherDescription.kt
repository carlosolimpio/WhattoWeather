package com.olimpio.whattoweather.data.network.response

import com.google.gson.annotations.SerializedName

data class WeatherDescription(
    @SerializedName("description") val description: String
)