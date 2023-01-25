package com.sara.canwesail.model.api.weatherApi.mapper

import com.sara.canwesail.model.api.weatherApi.dto.WeatherObjectDTO
import com.sara.canwesail.model.HourForecast
import com.sara.canwesail.model.WeatherModelObject
import kotlin.math.roundToInt

fun WeatherObjectDTO.toModelObjet() : WeatherModelObject {
    return WeatherModelObject(
        city = this.location.name,
        country = this.location.country,
        temperatureCelsius = this.current.temp_c.roundToInt().toString(),
        weatherDescription = this.current.condition.text,
        currentDayInt = this.current.last_updated_epoch,
        weatherIcon = this.current.condition.icon,
        windKnots = this.current.wind_mph,
        gustKnots = this.current.gust_mph,
        listHourForecast = getHourForecastLis(this)
    )

}

fun getHourForecastLis(weatherObjectDTO1: WeatherObjectDTO): List<HourForecast> {

    val hourForecastList: ArrayList<HourForecast> = arrayListOf()
    weatherObjectDTO1.forecast.forecastday[0].hour.subList(10, 20).forEach{
        hourForecastList.add(
            HourForecast(
                hour = it.time,
                weatherDescription = it.condition.text,
                weatherIcon =  it.condition.icon,
                temperatureCelsius = it.temp_c.roundToInt().toString()
            )
        )
    }
    return hourForecastList
}
