package com.sara.canwesail.view.util

object RequestConstants {

    //OpenWeather:
    const val BASE_URL = "https://api.openweathermap.org/"
    const val ROUTE = "data/2.5/forecast/daily"
    const val API_KEY = "ed60fcfbd110ee65c7150605ea8aceea"
    const val ICON_BASE_URL = "https://openweathermap.org/img/wn/"
    const val ICON_END_POINT = "@2x.png"

    //WeatherApi
    const val BASE_URL_ALT = "https://api.weatherapi.com"
    const val ROUTE_ALT = "/v1/forecast.json"
    const val API_KEY_ALTERNATIVE = "3ebbd900ee0b4149bcb160210232501"
    const val ICON_BASE_URL_ALTERNATIVE = "HTTPS://"

    const val ERROR_ICON_URL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZq9qJsUkNCcKIYwtrmPuAPX5s6eXN85kPk6fE52UhifkdUSxy98DCwYV5pFh-TSXkes8&usqp=CAU"

}

object SharedConstants {
    const val SHARED_PREF_REPO = "Can we sail"
    const val SHARED_PREF_CURRENT_CITY_KEY = "current city selection"
}