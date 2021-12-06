package com.olimpio.whattoweather.presentation.weather.ui.favoriteplaces

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.olimpio.whattoweather.databinding.FavoritePlaceListItemBinding
import com.olimpio.whattoweather.presentation.weather.model.Weather
import com.olimpio.whattoweather.presentation.weather.ui.CurrentWeatherFragmentDirections

class FavoritePlacesListAdapter : RecyclerView.Adapter<FavoritePlacesListAdapter.ViewHolder>() {
    private lateinit var favoriteWeatherList: List<List<Weather>>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritePlacesListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavoritePlaceListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritePlacesListAdapter.ViewHolder, position: Int) {
        holder.bindToView(favoriteWeatherList[position])
    }

    override fun getItemCount() = favoriteWeatherList.size

    fun setData(list: List<List<Weather>>) {
        favoriteWeatherList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        binding: FavoritePlaceListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val cityName: TextView = binding.textFavoriteCityName
        private val cityTemperature: TextView = binding.textFavoriteCityTemperature

        fun bindToView(weather: List<Weather>) {
            cityName.text = weather[0].cityName
            cityTemperature.text = weather[0].temperature + "º"

            //CurrentWeatherFragmentDirections.
            //itemView.setOnClickListener {}
        }
    }

}