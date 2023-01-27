package com.sara.canwesail.model

object ModelConstants {
    const val DEFAULT_CITY = "lisbon"

}


fun getFakeWeatherModelObject() : WeatherModelObject {
    return WeatherModelObject(
        city = "Lisbon",
        country = "Portugal",
        temperatureCelsius = "20",
        weatherDescription = "sunny",
        currentDayInt = 123456789,
        weatherIcon = "https://cdn.weatherapi.com/weather/64x64/night/143.png",
        windKnots = 20.0,
        gustKnots = 25.0,
        listHourForecast = getHourList()

    )
}

fun getHourList(): List<HourForecast> {
    return listOf(
        HourForecast("10:00","sunny", "https://cdn.weatherapi.com/weather/64x64/night/143.png", "25º"),
        HourForecast("11:00","sunny", "https://cdn.weatherapi.com/weather/64x64/night/143.png", "25º"),
        HourForecast("12:00","sunny", "https://cdn.weatherapi.com/weather/64x64/night/143.png", "25º"),
        HourForecast("13:00","sunny", "https://cdn.weatherapi.com/weather/64x64/night/143.png", "25º"),
        HourForecast("14:00","sunny", "https://cdn.weatherapi.com/weather/64x64/night/143.png", "25º"),
        HourForecast("15:00","sunny", "https://cdn.weatherapi.com/weather/64x64/night/143.png", "25º"),
        HourForecast("16:00","sunny", "https://cdn.weatherapi.com/weather/64x64/night/143.png", "25º")
    )

}