package com.sara.canwesail.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sara.canwesail.R
import com.sara.canwesail.view.AppScreens
import com.sara.canwesail.viewModel.WeatherViewModel
import java.util.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun goToHomeScreen(navController: NavController, weatherViewModel: WeatherViewModel) {

    weatherViewModel.getWeatherForCurrentCity()

    showSuccessView(navController, weatherViewModel)



}

@Composable
private fun showSuccessView(
    navController: NavController,
    weatherViewModel: WeatherViewModel
) {
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.ic_launcher_sail_foreground),
            contentDescription = "background_image",
            contentScale = ContentScale.FillBounds
        )
    }
    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = { getToolbar(navController) },
        content = { getMainContent(navController, weatherViewModel) },
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
                fontSize = 22.sp,
                color = Color.Gray
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
                    tint = Color.Gray
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
    )
}

@Composable
private fun getMainContent(
    navController: NavController,
    weatherViewModel: WeatherViewModel
) {

    Surface(color = Color.Transparent ) {

        Column (Modifier.padding(all = 20.dp)) {
            // City name
            Text(
                modifier = Modifier.background(color = Color.Transparent),
                text = "London",
                style = MaterialTheme.typography.subtitle1,
                fontSize = 22.sp,
                color = Color.Gray
            )

            Row {
                Text(
                    modifier = Modifier.background(color = Color.Transparent),
                    text = "23º",
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 22.sp,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier.background(color = Color.Transparent),
                    text = "23º",
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 22.sp,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier.background(color = Color.Transparent),
                    text = "23º",
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 22.sp,
                    color = Color.Gray
                )

            }

        }
    }
}

