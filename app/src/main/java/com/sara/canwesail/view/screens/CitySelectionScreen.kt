package com.sara.canwesail.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sara.canwesail.R
import com.sara.canwesail.view.util.CityEnum
import com.sara.canwesail.view.util.getCityBackgroundUrl
import com.sara.canwesail.view.widget.getGenericToolbar
import com.sara.canwesail.view.widget.getRadioButtonList
import com.sara.canwesail.viewModel.WeatherViewModel

@Composable
fun goToCitySelectionScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    Box {
        // Full screen background image:
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberAsyncImagePainter(
                model = getCityBackgroundUrl(weatherViewModel.getCurrentCity())
            ),
            contentDescription = stringResource(R.string.background_image_description),
            contentScale = ContentScale.FillBounds
        )
    }
    Scaffold(
        topBar = {
            getGenericToolbar(
                title = stringResource(R.string.select_city_menu_title),
                navController = navController,
                showBackIcon = true)
        },
        backgroundColor = Color.Transparent

    ) {
        // List containing city names to be selected:
        Column {
            getRadioButtonList(
                options = CityEnum.values().map { it.cityId }.toList() ,
                selectedOption = weatherViewModel.getCurrentCity(),
                onOptionSelected = {cityName ->
                    weatherViewModel.setCitySelected(cityName)
                    navController.popBackStack()
                }
            )

        }
    }

}