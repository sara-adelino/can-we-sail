package com.sara.canwesail.model

import com.sara.canwesail.model.api.WeatherNetWorkAPI2
import com.sara.canwesail.model.api.WeatherNetworkAPI
import com.sara.canwesail.model.dto.WeatherObjectDTO1
import com.sara.canwesail.model.mapper.WeatherModelObject
import com.sara.canwesail.model.mapper.toModelObjet
import com.sara.canwesail.viewModel.AppSharedPreferences
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherNetworkAPI,
    private val weatherApi2: WeatherNetWorkAPI2,
    private val sharedPreferences: AppSharedPreferences
    ) {

    private var currentWeather: WeatherModelObject? = null

    suspend fun getWeather(currentCity: String) : ResponseObject <WeatherModel,Boolean> {
        getWeather2(currentCity)
        val response =
            try {
                weatherApi.getWeather(currentCity)
            }
            catch (e: java.lang.Exception) {
                return ResponseObject(data = null, loading = false)
            }


        return ResponseObject(data = response, loading = false)
    }

    suspend fun getWeather2(currentCity: String): ResponseObject <WeatherModelObject, Boolean> {
        val response =
            try {
                weatherApi2.getWeatherAlternative(currentCity)
            }
            catch (e: java.lang.Exception) {
                return ResponseObject(data = null, loading = false)
            }


        return ResponseObject(data = response.toModelObjet(), loading = false)
    }

    fun getStoredCity(): String {
        return sharedPreferences.getStoredCityOrDefault()
    }

    fun saveCity(city: String) {
        sharedPreferences.saveCityId(city)
    }

    fun saveCurrentWeatherForecast(currentWeatherModel: WeatherModelObject?) {
        currentWeather = currentWeatherModel
    }

    fun getCurrentWeatherForecast(): WeatherModelObject? {
        return currentWeather
    }

}