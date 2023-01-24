package com.sara.canwesail.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sara.canwesail.R
import com.sara.canwesail.model.WeatherModel
import com.sara.canwesail.view.AppScreens
import com.sara.canwesail.view.util.getCityBackgroundUrl
import com.sara.canwesail.view.widget.getGenericToolbar
import com.sara.canwesail.view.widget.getWeatherRowComponent
import com.sara.canwesail.view.widget.hourWeatherRow
import com.sara.canwesail.viewModel.WeatherViewModel


@Composable
fun gotToDetailsScreen (
    navController: NavController,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {

    val currentWeather = weatherViewModel.getCurrentWeatherForecast()

    if (currentWeather != null) {
        loadScreen(navController = navController, weatherModel = currentWeather )
    }

}

@Composable
fun loadScreen(
    navController: NavController,
    weatherModel: WeatherModel
) {
    Box {
        // Full screen background image:
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberAsyncImagePainter(
                model = getCityBackgroundUrl(weatherModel.city.name)
            ),
            contentDescription = stringResource(R.string.background_image_description),
            contentScale = ContentScale.FillBounds
        )
    }
    Scaffold(
        modifier = Modifier.clickable {
            navController.navigate(AppScreens.HomeScreen.name)
        },
        // Toolbar:
        topBar = {
            getGenericToolbar(
                title = stringResource(R.string.forecast_menu_title),
                navController = navController)
        },
        backgroundColor = Color.Transparent

    ) {
        val weatherForecast = weatherModel.list
        var hourIncrement = 0

        Column (
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Top row with weather information:
            getWeatherRowComponent(weatherModel = weatherModel)

            // Center box with hour detailed temperatures
            Box(
                modifier = Modifier
                    .background(color = Color.Black.copy(0.5f))
                    .fillMaxWidth()
                    .padding(all = 20.dp)

            ) {
                LazyColumn {
                    items (items = weatherForecast) {
                        hourWeatherRow(weatherDetails = it, hourIncrement)
                        hourIncrement++
                    }
                }
            }
        }
    }
}
