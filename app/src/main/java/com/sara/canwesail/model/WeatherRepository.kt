package com.sara.canwesail.model

import com.sara.canwesail.model.api.weatherApi.WeatherHourApi
import com.sara.canwesail.model.api.openweatherApi.OpenWeatherApi
import com.sara.canwesail.model.api.openweatherApi.mapper.toModelObjet
import com.sara.canwesail.model.api.weatherApi.mapper.toModelObjet
import com.sara.canwesail.viewModel.AppSharedPreferences
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val openWeatherApi: OpenWeatherApi,
    private val weatherHourApi: WeatherHourApi,
    private val sharedPreferences: AppSharedPreferences
    ) {

    private var currentWeather: WeatherModelObject? = null

    // Change to TRUE, to use OpenWeatherApi:
    private val useOpenWeatherApi = false

   suspend fun getCurrentWeather(currentCity: String) : ResponseObject <WeatherModelObject, Boolean> {
       return if (useOpenWeatherApi) {
           getWeatherOpenWeatherApi(currentCity)
       } else {
           getWeatherHourApi(currentCity)
       }
   }
    private suspend fun getWeatherOpenWeatherApi(currentCity: String): ResponseObject <WeatherModelObject, Boolean> {
        val response =
            try {
                openWeatherApi.getWeather(currentCity)
            }
            catch (e: java.lang.Exception) {
                return ResponseObject(data = null, loading = false)
            }

        return ResponseObject(data = response.toModelObjet(), loading = false)
    }

    private suspend fun getWeatherHourApi(currentCity: String): ResponseObject <WeatherModelObject, Boolean> {
        val response =
            try {
                weatherHourApi.getWeather(currentCity)
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