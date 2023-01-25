package com.sara.canwesail.model.api.weatherApi.dto


data class WeatherObjectDTO(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)