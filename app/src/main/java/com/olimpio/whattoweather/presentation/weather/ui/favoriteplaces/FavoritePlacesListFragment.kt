package com.olimpio.whattoweather.presentation.weather.ui.favoriteplaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.olimpio.whattoweather.databinding.FragmentFavoritePlacesListBinding
import com.olimpio.whattoweather.presentation.weather.model.Weather

class FavoritePlacesListFragment(private val favoritePlacesList: List<Weather>) : Fragment() {
    private lateinit var binding: FragmentFavoritePlacesListBinding
    private val favoritePlacesListAdapter: FavoritePlacesListAdapter by lazy {
        FavoritePlacesListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritePlacesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //favoritePlacesListAdapter.setData(favoritePlacesList)

        initFavoritePlacesList()
    }

    private fun initFavoritePlacesList() {
        // retrieve favorite places from db
        // query them on retrofit API
        // display them

        with (binding.recyclerViewFavoritePlaces) {
            layoutManager = LinearLayoutManager(activity)
            adapter = favoritePlacesListAdapter
        }
    }
}