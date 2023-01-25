package com.sara.canwesail.view

enum class AppScreens {
    SplashScreen,
    CitySelection,
    HomeScreen,
    DetailsScreen;

    companion object {
        fun fromRoute (route: String?) : AppScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                CitySelection.name -> CitySelection
                HomeScreen.name -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw java.lang.IllegalArgumentException ("Route name $route is not recognized ")
            }
    }
}