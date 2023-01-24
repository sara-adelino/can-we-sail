package com.sara.canwesail.view.util

import com.sara.canwesail.model.WeatherDetails
import java.text.SimpleDateFormat
import java.util.*


fun integerToDayOfMonth(date: Int): String {
    val date = Date(date.toLong() * 1000)
    val sdf = SimpleDateFormat("dd")
    return sdf.format(date)
}

fun integerToDayOfWeek(date: Int): String {
    val date = Date(date.toLong() * 1000)
    val sdf = SimpleDateFormat("EEE")
    return sdf.format(date)
}

fun getWeatherIcon(weatherDetails: WeatherDetails): String {
    val iconId = weatherDetails.weather[0].icon

    return if (iconId.isNotEmpty()) {
        RequestConstants.ICON_BASE_URL + iconId + RequestConstants.ICON_END_POINT
    } else {
        RequestConstants.ERROR_ICON_URL
    }
}

fun isGoodForSailing(weatherDetails: WeatherDetails): Boolean {
    return weatherDetails.speed in 5.toDouble()..15.toDouble() &&
            weatherDetails.gust < 20.toDouble() &&
            !listOf("09d","10d","11d","13d","50d").contains(weatherDetails.weather[0].icon)
}
