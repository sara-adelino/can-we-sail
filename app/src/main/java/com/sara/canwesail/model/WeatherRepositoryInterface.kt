package com.sara.canwesail.model

interface WeatherRepositoryInterface {

    suspend fun getCurrentWeather(currentCity: String) : ResponseObject <WeatherModelObject, Boolean>

    suspend fun getWeatherOpenWeatherApi(currentCity: String): ResponseObject <WeatherModelObject, Boolean>

    suspend fun getWeatherHourApi(currentCity: String): ResponseObject <WeatherModelObject, Boolean>

    fun getStoredCity(): String

    fun saveCity(city: String)

    fun saveCurrentWeatherForecast(currentWeatherModel: WeatherModelObject?)

    fun getCurrentWeatherForecast(): WeatherModelObject?
}