package com.sara.canwesail.view.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.sara.canwesail.view.widget.getWeatherRowComponent
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
            value = weatherViewModel.getWeatherForCurrentCity()
        }.value

    // State handling
    if (weatherObject.loading == true) {
        showLoading()
    } else if (weatherObject.data != null){
        showSuccessView(navController, weatherObject.data!!)
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
    weatherModel: WeatherModel
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn()
    ) {
        // Full screen background image:
        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(model = getCityBackgroundUrl(weatherModel.city.name)),
                contentDescription = stringResource(R.string.background_image_description),
                contentScale = ContentScale.FillBounds
            )
        }
        Scaffold(
            modifier = Modifier.clickable {
                navController.navigate(AppScreens.DetailsScreen.name)
            },
            backgroundColor = Color.Transparent,
            topBar = { getToolbar(navController) },
            content = { getMainContent(weatherModel) },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun getToolbar(navController: NavController) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .background(color = Color.Transparent),
        title = {
            Text(
                text = stringResource(R.string.forecast_menu_title),
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
    weatherModel: WeatherModel
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent )
    {

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

        // Center sailing indicator
        val circleColor =
            if (isGoodForSailing(weatherModel.list[0])) Color.Green else Color.Red
        val windRowColor = getWindIndicatorColor(weatherModel.list[0])

        val gustRowColor = getGustIndicatorColor(weatherModel.list[0])

        val weatherRowColor = getWeatherRowColor(weatherModel.list[0])

       Row(
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(BorderStroke(10.dp, circleColor), CircleShape),
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier
                        .size(80.dp),
                    //alignment = Alignment.Center,
                    painter = painterResource(id = R.drawable.ic_sail_icon),
                    contentDescription = stringResource(R.string.splash_icon_description),
                    contentScale = ContentScale.Fit
                )

            }
           Column(
               Modifier
                   .padding(all = 20.dp)
                   .background(color = Color.Black.copy(0.2f))
                   .wrapContentWidth(
                       align = Alignment.Start

                   ),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.Start

           ) {
               // Wind row
               Text(
                   modifier = Modifier
                       .background(color = Color.Transparent)
                       .padding(
                           start = 20.dp,
                           end = 20.dp,
                           top = 10.dp
                       ),
                   textAlign = TextAlign.Left,
                   text = "Wind: ${getWindInKnots(weatherModel.list[0].speed)} knots",
                   style = MaterialTheme.typography.subtitle1,
                   fontSize = 15.sp,
                   fontWeight = FontWeight.Bold,
                   color = windRowColor
               )

               // Gust row:
               Text(
                   modifier = Modifier
                       .background(color = Color.Transparent)
                       .padding(
                           start = 20.dp,
                           end = 20.dp,
                           top = 10.dp
                       ),
                   textAlign = TextAlign.Left,
                   text = "Gust: ${getWindInKnots(weatherModel.list[0].gust)} knots",
                   style = MaterialTheme.typography.subtitle1,
                   fontSize = 15.sp,
                   fontWeight = FontWeight.Bold,
                   color = gustRowColor
               )

               // Weather row:
               Text(
                   modifier = Modifier
                       .background(color = Color.Transparent)
                       .padding(
                           start = 20.dp,
                           end = 20.dp,
                           top = 10.dp,
                           bottom = 10.dp
                       ),
                   textAlign = TextAlign.Left,
                   text = weatherModel.list[0].weather[0].description.capitalize(),
                   style = MaterialTheme.typography.subtitle1,
                   fontSize = 15.sp,
                   fontWeight = FontWeight.Bold,
                   color = weatherRowColor
               )
           }
        }

        // Bottom elements:
        getWeatherRowComponent(weatherModel = weatherModel)

    }
}

