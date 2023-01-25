package com.sara.canwesail.model.api.openweatherApi

import com.sara.canwesail.model.api.openweatherApi.dto.OpenWeatherDTO
import com.sara.canwesail.view.util.RequestConstants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface OpenWeatherApi{
    @GET(value = RequestConstants.OPEN_WEATHER_ROUTE)
     suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = RequestConstants.OPEN_WEATHER_API_KEY
    ): OpenWeatherDTO
}