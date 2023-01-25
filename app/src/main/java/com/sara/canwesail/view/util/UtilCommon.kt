package com.sara.canwesail.view.util

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

fun getWeatherIcon(iconReference: String): String {

    return if (iconReference.isNotEmpty()) {
        // Depending on length of icon reference, identify API and decide icon URL
        // OpenWeather API has very short icon reference
        if (iconReference.length > 10) {
            // Weather hour API scenario:
            RequestConstants.HOUR_WEATHER_ICON_BASE_URL + iconReference
        } else {
            // Open weather Api scenario
            RequestConstants.OPEN_WEATHER_ICON_BASE_URL + iconReference + RequestConstants.OPEN_WEATHER_ICON_END_POINT
        }

    } else {
        RequestConstants.ERROR_ICON_URL
    }
}