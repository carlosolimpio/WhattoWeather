package com.olimpio.whattoweather.presentation.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.olimpio.whattoweather.R
import com.olimpio.whattoweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.navHostFragment)
        drawerLayout = binding.drawerLayout

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.setNavigationItemSelectedListener(getNavigationItemSelectedListener())

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    private fun getNavigationItemSelectedListener(): NavigationView.OnNavigationItemSelectedListener {
        return NavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
//                R.id. -> {
//                    Log.d(TAG, "getNavigationItemSelectedListener: item1")
//                    true
//                }
//                R.id.item_edituser_profile -> {
//                    Log.d(TAG, "getNavigationItemSelectedListener: item2")
//                    true
//                }
                else -> false
            }
        }
    }
}