package com.sara.canwesail.view.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
        animationSpec = tween(durationMillis = 2000)
    )

    LaunchedEffect(key1 = true ) {
        startAnimation = true
        delay(1000)
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

        Box(modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(Color.Green)
            .border(BorderStroke(10.dp, Color.Green), CircleShape)
            .clickable {
                navController.popBackStack()
                navController.navigate(route = AppScreens.HomeScreen.name)
            },
            contentAlignment = Alignment.Center
        ) {
            // Splash icon
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .alpha(alpha),
                painter = painterResource(id = R.drawable.ic_sail_icon),
                contentDescription = stringResource(R.string.splash_icon_description),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }


    }
}



