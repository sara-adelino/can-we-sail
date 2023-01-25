package com.sara.canwesail.model.mapper

import com.sara.canwesail.model.ResponseObject
import com.sara.canwesail.model.dto.WeatherObjectDTO1
import kotlin.math.roundToInt

fun WeatherObjectDTO1.toModelObjet() : WeatherModelObject {
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

fun getHourForecastLis(weatherObjectDTO1: WeatherObjectDTO1): List<HourForecast> {

    val hourForecastList: List<HourForecast> = listOf()
    weatherObjectDTO1.forecast.forecastday[0].hour.subList(10, 20).forEach{
        hourForecastList.plus(
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
