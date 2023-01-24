package com.sara.canwesail.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sara.canwesail.R
import com.sara.canwesail.model.ResponseObject
import com.sara.canwesail.model.WeatherModel
import com.sara.canwesail.view.AppScreens
import com.sara.canwesail.view.util.*
import com.sara.canwesail.viewModel.WeatherViewModel
import java.util.*

@Composable
fun goToHomeScreen (
    navController: NavController,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {

    // State containing weather data:
    val weatherObject =
        produceState<ResponseObject<WeatherModel, Boolean>>(
            initialValue = ResponseObject(null, true)
        ){
            value = weatherViewModel.data.value
        }.value

      // State handling
    if (weatherObject.loading == true) {
        showLoading()
    } else if (weatherObject.data != null){
        showSuccessView(navController, weatherViewModel, weatherObject.data!!)
    }

}

@Composable
private fun showLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun showSuccessView(
    navController: NavController,
    weatherViewModel: WeatherViewModel,
    weatherModel: WeatherModel
) {
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(getCityBackgroundImage(weatherModel.city.name)),
            contentDescription = stringResource(R.string.background_image_description),
            contentScale = ContentScale.FillBounds
        )
    }
    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = { getToolbar(navController) },
        content = { getMainContent(navController, weatherViewModel, weatherModel) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun getToolbar(navController: NavController) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .background(color = Color.Transparent),
        title = {
            Text(
                text = stringResource(R.string.toolbar_title),
                style = MaterialTheme.typography.overline,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(route = AppScreens.CitySelection.name)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = stringResource(R.string.menu_selection_description),
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
private fun getMainContent(
    navController: NavController,
    weatherViewModel: WeatherViewModel,
    weatherModel: WeatherModel
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent ) {

        // Top elements:
        Column (
            Modifier.padding(all = 40.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // City name
            Text(
                modifier = Modifier.background(color = Color.Transparent),
                text = weatherModel.city.name,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            // Country nickname
            Text(
                modifier = Modifier.background(color = Color.Transparent),
                text = weatherModel.city.country,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Bottom elements:
        Column (
            Modifier
                .padding(start = 40.dp, end = 40.dp, bottom = 80.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left column:
                Column() {
                    Text(
                        modifier = Modifier.background(color = Color.Transparent),
                        text = "${weatherModel.list[0].temp.day.toInt()}º",
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = 80.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.background(color = Color.Transparent),
                        text = weatherModel.list[0].weather[0].main,
                        style = MaterialTheme.typography.caption,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.background(color = Color.Transparent),
                        text = "${weatherModel.city.name}, ${weatherModel.city.country}",
                        style = MaterialTheme.typography.overline,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White
                    )

                }
                // Center image:
                Image(
                    modifier = Modifier
                        .size(120.dp),
                    painter = rememberAsyncImagePainter(model = getWeatherIcon(weatherModel.list[0])),
                    contentDescription = stringResource(R.string.splash_icon_description),
                    contentScale = ContentScale.Fit
                )
                // Right column
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.background(color = Color.Transparent),
                        text = integerToDayOfMonth(weatherModel.list[0].dt),
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.background(color = Color.Transparent),
                        text = integerToDayOfWeek(weatherModel.list[0].dt),
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraLight,
                        color = Color.White
                    )

                }

            }
        }

    }
}

