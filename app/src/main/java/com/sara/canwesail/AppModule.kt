package com.sara.canwesail

import android.app.Application
import com.sara.canwesail.model.api.weatherApi.WeatherHourApi
import com.sara.canwesail.model.api.openweatherApi.OpenWeatherApi
import com.sara.canwesail.view.util.RequestConstants
import com.sara.canwesail.model.api.openweatherApi.clientadapter.RestAdapter.getUnsafeOkHttpClient
import com.sara.canwesail.viewModel.AppSharedPreferences
import com.sara.canwesail.viewModel.SailingViewModel
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
    fun getOpenWeatherAPI() : OpenWeatherApi {
        return Retrofit.Builder()
            .baseUrl(RequestConstants.OPEN_WEATHER_BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun getWeatherHourApi(): WeatherHourApi {
        return Retrofit.Builder()
            .baseUrl(RequestConstants.HOUR_WEATHER_BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherHourApi::class.java)

    }

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): AppSharedPreferences {
        return AppSharedPreferences(application)
    }

    @Provides
    fun providesSailingViewModel() : SailingViewModel {
        return SailingViewModel()
    }
}