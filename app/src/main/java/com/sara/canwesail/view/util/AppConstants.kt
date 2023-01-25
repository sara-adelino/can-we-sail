package com.sara.canwesail.view.util

object RequestConstants {

    //OpenWeather Api:
    const val OPEN_WEATHER_BASE_URL = "https://api.openweathermap.org/"
    const val OPEN_WEATHER_ROUTE = "data/2.5/forecast/daily"
    const val OPEN_WEATHER_API_KEY = "ed60fcfbd110ee65c7150605ea8aceea"
    const val OPEN_WEATHER_ICON_BASE_URL = "https://openweathermap.org/img/wn/"
    const val OPEN_WEATHER_ICON_END_POINT = "@2x.png"

    //Weather hour Api:
    const val HOUR_WEATHER_BASE_URL = "https://api.weatherapi.com"
    const val HOUR_WEATHER_ROUTE = "/v1/forecast.json"
    const val HOUR_WEATHER_API_KEY = "3ebbd900ee0b4149bcb160210232501"
    const val HOUR_WEATHER_ICON_BASE_URL = "https://"

    const val ERROR_ICON_URL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZq9qJsUkNCcKIYwtrmPuAPX5s6eXN85kPk6fE52UhifkdUSxy98DCwYV5pFh-TSXkes8&usqp=CAU"

}

object SharedConstants {
    const val SHARED_PREF_REPO = "Can we sail"
    const val SHARED_PREF_CURRENT_CITY_KEY = "current city selection"
}