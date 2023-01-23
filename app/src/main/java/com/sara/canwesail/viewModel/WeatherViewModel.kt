package com.sara.canwesail.viewModel

import androidx.lifecycle.ViewModel
import com.sara.canwesail.model.WeatherRepository

class WeatherViewModel (private val repository: WeatherRepository) : ViewModel() {

    private fun getCurrentCity() : String {
       return repository.getStoredCity()
    }

    fun getWeatherForCurrentCity() {
        val currentCity = getCurrentCity()

    }

}