package com.sara.canwesail.model.api.weatherApi

import com.sara.canwesail.model.api.weatherApi.dto.WeatherObjectDTO
import com.sara.canwesail.view.util.RequestConstants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherHourApi {
    @GET (value = RequestConstants.ROUTE_ALT)
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("key") appId: String = RequestConstants.API_KEY_ALTERNATIVE
    ): WeatherObjectDTO
}