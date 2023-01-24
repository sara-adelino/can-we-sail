package com.sara.canwesail.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.canwesail.model.ResponseObject
import com.sara.canwesail.model.WeatherModel
import com.sara.canwesail.model.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val data: MutableState<ResponseObject<WeatherModel, Boolean>> =
        mutableStateOf(ResponseObject(null, true))

    init {
        getWeatherForCurrentCity()
    }

    fun getCurrentCity() : String {
       return repository.getStoredCity()
    }

    fun setCitySelected(string: String) {
        repository.saveCity(string)
    }

    private fun getWeatherForCurrentCity() {

        val currentCity = getCurrentCity()

        viewModelScope.launch {
            if (currentCity.isEmpty()) return@launch
            data.value.loading = true
            data.value = repository.getWeather(currentCity)

            if (data.value.data.toString().isNotEmpty()) data.value.loading = false
        }

    }

}