package com.sara.canwesail.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sara.canwesail.R
import com.sara.canwesail.model.ResponseObject
import com.sara.canwesail.model.WeatherModel
import com.sara.canwesail.view.util.getCityBackgroundUrl
import com.sara.canwesail.view.widget.getGenericToolbar
import com.sara.canwesail.view.widget.getWeatherRowComponent
import com.sara.canwesail.viewModel.WeatherViewModel


@Composable
fun gotToDetailsScreen (
    navController: NavController,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherObject =
        produceState<ResponseObject<WeatherModel, Boolean>>(
            initialValue = ResponseObject(null, true)
        ){
            value = weatherViewModel.getWeatherForCurrentCity()
        }.value

    Box {
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
                title = stringResource(R.string.forecast_menu_title),
                navController = navController)
        },
        backgroundColor = Color.Transparent

    ) {
        weatherObject.data?.let { data -> getWeatherRowComponent(data) }
    }
}