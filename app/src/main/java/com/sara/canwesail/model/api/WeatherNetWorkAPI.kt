package com.sara.canwesail.model.api

import com.sara.canwesail.model.WeatherModel
import com.sara.canwesail.model.dto.WeatherObjectDTO1
import com.sara.canwesail.view.util.RequestConstants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherNetworkAPI {
    @GET (value = RequestConstants.ROUTE)
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String= "metric",
        @Query ("appid") appId: String = RequestConstants.API_KEY
    ) : WeatherModel

}

@Singleton
interface WeatherNetWorkAPI2 {
    @GET (value = RequestConstants.ROUTE_ALT)
    suspend fun getWeatherAlternative(
        @Query("q") city: String,
        @Query("key") appId: String = RequestConstants.API_KEY_ALTERNATIVE
    ): WeatherObjectDTO1
}