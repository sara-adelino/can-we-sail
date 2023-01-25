package com.sara.canwesail.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.canwesail.model.ResponseObject
import com.sara.canwesail.model.WeatherRepository
import com.sara.canwesail.model.WeatherModelObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    private val dataModelObject: MutableState<ResponseObject<WeatherModelObject,Boolean>> =
        mutableStateOf(ResponseObject(null, null))

    private var currentCity: MutableState<String> = mutableStateOf( getCurrentCity())

    fun getCurrentCity() : String {
       return repository.getStoredCity()
    }

    fun setCitySelected(string: String) {
        repository.saveCity(string)
        currentCity.value = string
    }

    fun getCurrentWeatherForecast() : WeatherModelObject? {
        return repository.getCurrentWeatherForecast()
    }

    suspend fun getWeatherForCurrentCity(): ResponseObject <WeatherModelObject,Boolean> {

        val job = viewModelScope.launch {
            dataModelObject.value.loading = true
            dataModelObject.value = repository.getCurrentWeather(currentCity.value)

            if (dataModelObject.value.data.toString().isNotEmpty()) {
                dataModelObject.value.loading = false
            }
        }
        job.join()
        repository.saveCurrentWeatherForecast(dataModelObject.value.data)
        return dataModelObject.value
    }



}