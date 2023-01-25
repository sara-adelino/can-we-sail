package com.sara.canwesail

import android.app.Application
import com.sara.canwesail.model.api.WeatherNetWorkAPI2
import com.sara.canwesail.model.api.WeatherNetworkAPI
import com.sara.canwesail.view.util.RequestConstants
import com.sara.canwesail.model.clientadapter.RestAdapter.getUnsafeOkHttpClient
import com.sara.canwesail.viewModel.AppSharedPreferences
import com.sara.canwesail.viewModel.SailingViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
class AppModule {

    @Provides
    @Singleton
    fun getWeatherAPI() : WeatherNetworkAPI {
        return Retrofit.Builder()
            .baseUrl(RequestConstants.BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherNetworkAPI::class.java)
    }

    @Provides
    @Singleton
    fun getWeatherAPIAlt(): WeatherNetWorkAPI2 {
        return Retrofit.Builder()
            .baseUrl(RequestConstants.BASE_URL_ALT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherNetWorkAPI2::class.java)

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