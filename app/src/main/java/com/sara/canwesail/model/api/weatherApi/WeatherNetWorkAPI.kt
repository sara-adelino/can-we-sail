package com.sara.canwesail.model.api.weatherApi

import com.sara.canwesail.model.api.weatherApi.dto.WeatherObjectDTO
import com.sara.canwesail.view.util.RequestConstants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherHourApi {
    @GET (value = RequestConstants.HOUR_WEATHER_ROUTE)
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("key") appId: String = RequestConstants.HOUR_WEATHER_API_KEY
    ): WeatherObjectDTO
}