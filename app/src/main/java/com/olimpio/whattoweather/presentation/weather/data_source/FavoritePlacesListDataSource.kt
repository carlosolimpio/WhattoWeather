package com.olimpio.whattoweather.presentation.weather.data_source

import com.olimpio.whattoweather.presentation.weather.model.Weather

interface FavoritePlacesListDataSource {
    fun getFavoritePlacesList(): List<Weather>
}