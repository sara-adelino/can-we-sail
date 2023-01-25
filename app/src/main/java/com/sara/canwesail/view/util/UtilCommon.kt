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
        RequestConstants.ICON_BASE_URL_ALTERNATIVE + iconReference
    } else {
        RequestConstants.ERROR_ICON_URL
    }
}