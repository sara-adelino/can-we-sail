package com.sara.canwesail.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.sara.canwesail.view.util.CityEnum
import com.sara.canwesail.view.util.SharedConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppSharedPreferences @Inject constructor(context: Context) : ViewModel() {
    private val sharedPref: SharedPreferences
    private val edit: SharedPreferences.Editor

    init {
        sharedPref =
            context.getSharedPreferences(SharedConstants.SHARED_PREF_REPO, Context.MODE_PRIVATE)
        edit = sharedPref.edit()
    }

    fun getStoredCityOrDefault(): String {
        var currentCity = sharedPref.getString(SharedConstants.SHARED_PREF_CURRENT_CITY_KEY, null)
        if (currentCity.isNullOrEmpty()) {
            currentCity = CityEnum.LISBON.cityId
        }
        return currentCity
    }

    fun saveCityId(cityId: String?) {
        if (cityId.isNullOrEmpty()) return
        edit.putString(SharedConstants.SHARED_PREF_CURRENT_CITY_KEY, cityId).apply()
        commitPreference()
    }

    private fun commitPreference() {
        edit.commit()
    }
}