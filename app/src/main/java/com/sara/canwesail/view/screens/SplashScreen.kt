package com.sara.canwesail.view.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sara.canwesail.view.AppScreens
import kotlinx.coroutines.delay
import com.sara.canwesail.R



@Composable
fun goToAnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 4000)
    )

    LaunchedEffect(key1 = true ) {
        startAnimation = true
        delay(2000)
    }

    splash(navController, alphaAnimation.value)

}

@Composable
private fun splash(navController: NavHostController, alpha: Float) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .alpha(alpha)
                .clickable {
                    navController.popBackStack()
                    navController.navigate(route = AppScreens.HomeScreen.name)
                },
            painter = painterResource(id = R.drawable.ic_baseline_sailing_24),
            contentDescription = stringResource(R.string.splash_icon_description),
            contentScale = ContentScale.Crop
        )

    }
}



