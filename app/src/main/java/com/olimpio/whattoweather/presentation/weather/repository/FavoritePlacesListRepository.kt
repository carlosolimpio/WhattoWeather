package com.olimpio.whattoweather.presentation.weather.repository

import com.olimpio.whattoweather.presentation.weather.model.Weather

interface FavoritePlacesListRepository {
    fun getFavoritePlacesList(): List<Weather>
}