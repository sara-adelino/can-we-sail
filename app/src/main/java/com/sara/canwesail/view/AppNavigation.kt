package com.sara.canwesail.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sara.canwesail.model.WeatherRepository
import com.sara.canwesail.view.screens.goToAnimatedSplashScreen
import com.sara.canwesail.view.screens.goToHomeScreen
import com.sara.canwesail.viewModel.WeatherViewModel

@Composable
fun appNavigation () {

    val navController = rememberNavController()
    val weatherViewModel = hiltViewModel<WeatherViewModel>()

    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.name
    ) {
        // Splash Screen
        composable(
            route = AppScreens.SplashScreen.name
        ) {
            goToAnimatedSplashScreen(navController)
        }

        // Home Screen:
        composable(
            route = AppScreens.HomeScreen.name
        ) {
           goToHomeScreen(navController, weatherViewModel)
        }

        // Details Screen
        composable(
            route = AppScreens.DetailsScreen.name + "/{weekDay}",
        ) {
            //
        }

    }
}