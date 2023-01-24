package com.sara.canwesail.model

import com.sara.canwesail.model.api.WeatherNetworkAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherNetworkAPI) {

    suspend fun getWeather(currentCity: String) : ResponseObject <WeatherModel,Boolean> {
        val response =
            try {
                weatherApi.getWeather(currentCity)
            }
            catch (e: java.lang.Exception) {
                return ResponseObject(data = null, loading = false)
            }

        return ResponseObject(data = response, loading = false)
    }

    fun getStoredCity(): String {
        return "paris"
    }

}