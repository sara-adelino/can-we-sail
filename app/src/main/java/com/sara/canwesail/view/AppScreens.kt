package com.sara.canwesail.view

enum class AppScreens {
    SplashScreen,
    HomeScreen,
    DetailsScreen;

    companion object {
        fun fromRoute (route: String?) : AppScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                HomeScreen.name -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw java.lang.IllegalArgumentException ("Route name $route is not recognized ")
            }
    }
}