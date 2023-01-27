package com.sara.canwesail.model

class FakeWeatherRepository : WeatherRepositoryInterface {


    private var currentCity = ModelConstants.DEFAULT_CITY

    private var fakeCurrentWeatherObject: WeatherModelObject = getFakeWeatherModelObject()

    override suspend fun getCurrentWeather(currentCity: String): ResponseObject<WeatherModelObject, Boolean> {
        return ResponseObject(fakeCurrentWeatherObject,false)
    }

    override suspend fun getWeatherOpenWeatherApi(currentCity: String): ResponseObject<WeatherModelObject, Boolean> {
        return ResponseObject(fakeCurrentWeatherObject, false)
    }

    override suspend fun getWeatherHourApi(currentCity: String): ResponseObject<WeatherModelObject, Boolean> {
        return ResponseObject(fakeCurrentWeatherObject, false)

    }

    override fun getStoredCity(): String {
        return currentCity
    }

    override fun saveCity(city: String) {
        currentCity = city
    }

    override fun saveCurrentWeatherForecast(currentWeatherModel: WeatherModelObject?) {
        if (currentWeatherModel != null) {
            fakeCurrentWeatherObject = currentWeatherModel
        }
    }

    override fun getCurrentWeatherForecast(): WeatherModelObject? {
        return fakeCurrentWeatherObject
    }
}
