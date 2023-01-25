package com.sara.canwesail.model.api.openweatherApi.mapper

import com.sara.canwesail.model.WeatherModelObject
import com.sara.canwesail.model.api.openweatherApi.dto.OpenWeatherDTO
import kotlin.math.roundToInt

fun OpenWeatherDTO.toModelObjet() : WeatherModelObject {
    return WeatherModelObject(
        city = this.city.name,
        country = this.city.country,
        temperatureCelsius = this.list[0].temp.day.roundToInt().toString(),
        weatherDescription = this.list[0].weather[0].description,
        currentDayInt = this.cnt,
        weatherIcon = this.list[0].weather[0].icon,
        windKnots = this.list[0].speed,
        gustKnots = this.list[0].gust,
        //open weather doesn't support hour forecast in the free package:
        listHourForecast = arrayListOf()
    )

}