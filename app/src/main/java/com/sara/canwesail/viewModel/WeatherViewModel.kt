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

    private val data: MutableState<ResponseObject<WeatherModel, Boolean>> =
        mutableStateOf(ResponseObject(null, true))

    private var currentCity: MutableState<String> = mutableStateOf( getCurrentCity())

    fun getCurrentCity() : String {
       return repository.getStoredCity()
    }

    fun setCitySelected(string: String) {
        repository.saveCity(string)
        currentCity.value = string
    }

    fun getCurrentWeatherForecast() : WeatherModel? {
        return repository.getCurrentWeatherForecast()
    }

    suspend fun getWeatherForCurrentCity(): ResponseObject <WeatherModel,Boolean> {

        val job = viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getWeather(currentCity.value)

            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
        job.join()
        repository.saveCurrentWeatherForecast(data.value.data)
        return data.value
    }

}