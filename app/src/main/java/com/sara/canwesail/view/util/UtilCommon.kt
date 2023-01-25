package com.sara.canwesail.view.util

import androidx.compose.ui.graphics.Color
import com.sara.canwesail.model.WeatherDetails
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


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
    return isWindOnLimitForSail(weatherDetails) &&
            isGustOnLimit(weatherDetails) &&
            isAtmosphereConvenientForSail(weatherDetails)
}

private fun isWindOnLimitForSail(weatherDetails: WeatherDetails): Boolean {
    return weatherDetails.speed in 4.toDouble()..15.toDouble()
}

fun getWindIndicatorColor (weatherDetails: WeatherDetails): Color {
    return getIndicatorColor(isWindOnLimitForSail(weatherDetails))
}

private fun isGustOnLimit(weatherDetails: WeatherDetails): Boolean {
    return weatherDetails.gust < 20.toDouble()
}

fun getGustIndicatorColor (weatherDetails: WeatherDetails): Color {
    return getIndicatorColor(isGustOnLimit(weatherDetails))
}

private fun isAtmosphereConvenientForSail(weatherDetails: WeatherDetails): Boolean {
    return !listOf("09d","10d","11d","13d","50d").contains(weatherDetails.weather[0].icon)
}

fun getWeatherRowColor(weatherDetails: WeatherDetails): Color {
    return getIndicatorColor(isAtmosphereConvenientForSail(weatherDetails))
}
fun getWindInKnots(speedInMeters: Double): String {
    // meter per second to knots:
    val conversionFactor = 1.94

    return speedInMeters.times(conversionFactor).roundToInt().toString()
}

private fun getIndicatorColor (isGood: Boolean): Color {
    return if (isGood) Color.White else Color.Red
}
