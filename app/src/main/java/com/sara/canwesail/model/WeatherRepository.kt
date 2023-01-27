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
    ) : WeatherRepositoryInterface {

    private var currentWeather: WeatherModelObject? = null

    // Change to TRUE, to use OpenWeatherApi:
    private val useOpenWeatherApi = false

    override suspend fun getCurrentWeather(currentCity: String) : ResponseObject <WeatherModelObject, Boolean> {
       return if (useOpenWeatherApi) {
           getWeatherOpenWeatherApi(currentCity)
       } else {
           getWeatherHourApi(currentCity)
       }
   }

    override suspend fun getWeatherOpenWeatherApi(currentCity: String): ResponseObject <WeatherModelObject, Boolean> {
        val response =
            try {
                openWeatherApi.getWeather(currentCity)
            }
            catch (e: java.lang.Exception) {
                return ResponseObject(data = null, loading = false)
            }

        return ResponseObject(data = response.toModelObjet(), loading = false)
    }

    override suspend fun getWeatherHourApi(currentCity: String): ResponseObject <WeatherModelObject, Boolean> {
        val response =
            try {
                weatherHourApi.getWeather(currentCity)
            }
            catch (e: java.lang.Exception) {
                return ResponseObject(data = null, loading = false)
            }

        return ResponseObject(data = response.toModelObjet(), loading = false)
    }

    override fun getStoredCity(): String {
        return sharedPreferences.getStoredCityOrDefault()
    }

    override fun saveCity(city: String) {
        sharedPreferences.saveCityId(city)
    }

    override fun saveCurrentWeatherForecast(currentWeatherModel: WeatherModelObject?) {
        currentWeather = currentWeatherModel
    }

    override fun getCurrentWeatherForecast(): WeatherModelObject? {
        return currentWeather
    }

}