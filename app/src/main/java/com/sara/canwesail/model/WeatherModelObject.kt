package com.sara.canwesail.model

data class WeatherModelObject(
    val city: String,
    val country: String,
    val temperatureCelsius: String,
    val weatherDescription: String,
    val currentDayInt: Int,
    val weatherIcon: String,
    val windKnots: Double,
    val gustKnots: Double,
    val listHourForecast: List<HourForecast>
    )

data class HourForecast (
    val hour: String,
    val weatherDescription: String,
    val weatherIcon: String,
    val temperatureCelsius: String,
    )
