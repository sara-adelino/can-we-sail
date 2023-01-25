package com.sara.canwesail.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.sara.canwesail.model.WeatherModelObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class SailingViewModel @Inject constructor() : ViewModel() {

    private var currentWeatherModel: WeatherModelObject? = null
    // meter per second to knots:
    private val conversionFactor = 1.94

    fun setWeatherModel(weatherModelObject: WeatherModelObject) {
        currentWeatherModel = weatherModelObject
    }

    fun getWindInKnots(): String {
        return currentWeatherModel!!.windKnots.roundToInt().toString()
    }

    fun getGustInKnots(): String {
        return currentWeatherModel!!.gustKnots.roundToInt().toString()
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
        if (currentWeatherModel == null) return false
        return isWindOnLimitForSail() &&
                isGustOnLimit() &&
                isAtmosphereConvenientForSail()
    }

    private fun isWindOnLimitForSail (): Boolean {
        if (currentWeatherModel == null) return false
        return currentWeatherModel!!.windKnots in 8.toDouble()..30.toDouble()
    }

    private fun isGustOnLimit(): Boolean {
        if (currentWeatherModel == null) return false
        return currentWeatherModel!!.gustKnots < 35.toDouble()
    }

    private fun isAtmosphereConvenientForSail(): Boolean {
        if (currentWeatherModel == null) return false
        return !listOf("09d","10d","11d","13d","50d").contains(currentWeatherModel!!.weatherIcon)
    }

    private fun getIndicatorColor (isGood: Boolean): Color {
        return if (isGood) Color.White else Color.Red
    }
}