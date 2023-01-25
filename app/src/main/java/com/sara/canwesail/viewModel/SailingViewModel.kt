package com.sara.canwesail.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.sara.canwesail.model.WeatherDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class SailingViewModel @Inject constructor() : ViewModel() {

    private var currentWeatherDetails: WeatherDetails? = null
    // meter per second to knots:
    private val conversionFactor = 1.94

    fun setWeatherModel(weatherModel: WeatherDetails) {
        currentWeatherDetails = weatherModel
    }

    fun getWindInKnots(): String {
        return currentWeatherDetails!!.speed.times(conversionFactor).roundToInt().toString()
    }

    fun getGustInKnots(): String {
        return currentWeatherDetails!!.gust.times(conversionFactor).roundToInt().toString()
    }

    fun getWindIndicatorColor () : Color {
        return getIndicatorColor(isWindOnLimitForSail())
    }

    fun getGustIndicatorColor (): Color {
        return getIndicatorColor(isGustOnLimit())
    }

    fun getWeatherRowColor(): Color {
        return getIndicatorColor(isAtmosphereConvenientForSail())
    }

    fun getSailingIndicatorColor(): Color {
        return getIndicatorColor(isGoodForSailing())
    }

    private fun isGoodForSailing(): Boolean {
        if (currentWeatherDetails == null) return false
        return isWindOnLimitForSail() &&
                isGustOnLimit() &&
                isAtmosphereConvenientForSail()
    }

    private fun isWindOnLimitForSail (): Boolean {
        if (currentWeatherDetails == null) return false
        return currentWeatherDetails!!.speed in 4.toDouble()..15.toDouble()
    }

    private fun isGustOnLimit(): Boolean {
        if (currentWeatherDetails == null) return false
        return currentWeatherDetails!!.gust < 20.toDouble()
    }

    private fun isAtmosphereConvenientForSail(): Boolean {
        if (currentWeatherDetails == null) return false
        return !listOf("09d","10d","11d","13d","50d").contains(currentWeatherDetails!!.weather[0].icon)
    }

    private fun getIndicatorColor (isGood: Boolean): Color {
        return if (isGood) Color.White else Color.Red
    }
}