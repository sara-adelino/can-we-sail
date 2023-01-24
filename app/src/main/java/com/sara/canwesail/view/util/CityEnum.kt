package com.sara.canwesail.view.util

import com.sara.canwesail.R

enum class CityEnum (
    var cityId: String,
    var cityBackgroundId: Int) {
    BERLIN("berlin", R.drawable.berlin),
    COPENHAGEN("copenhagen", R.drawable.copenhagen),
    DUBLIN("dublin", R.drawable.dublin),
    LISBON("lisbon",R.drawable.lisbon),
    LONDON("london",R.drawable.london),
    MADRID("madrid",R.drawable.madrid),
    PARIS("paris",R.drawable.paris),
    PRAGUE("prague",R.drawable.prague),
    ROME("rome",R.drawable.rome),
    VIENNA("vienna",R.drawable.vienna)
}

fun getCityBackgroundImage (cityIdentification: String): Int {
    return CityEnum.values().firstOrNull{it.cityId.lowercase() == cityIdentification.lowercase()}?.cityBackgroundId
        ?: R.drawable.city
}