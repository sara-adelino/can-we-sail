package com.sara.canwesail

import com.sara.canwesail.model.WeatherNetworkAPI
import com.sara.canwesail.util.RequestConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
class AppModule {

    @Provides
    @Singleton
    fun getWeatherAPI() : WeatherNetworkAPI {
        return Retrofit.Builder()
            .baseUrl(RequestConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherNetworkAPI::class.java)
    }
}