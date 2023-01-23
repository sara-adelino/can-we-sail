package com.sara.canwesail.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sara.canwesail.view.screens.goToAnimatedSplashScreen

@Composable
fun appNavigation () {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.name
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
           //
        }

        // Details Screen
        composable(
            route = AppScreens.DetailsScreen.name + "/{weekDay}",
        ) {
            //
        }

    }
}