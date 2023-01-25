package com.sara.canwesail.view.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sara.canwesail.R
import com.sara.canwesail.model.WeatherDetails
import com.sara.canwesail.view.util.getWeatherIcon
import kotlin.math.roundToInt

@Composable
fun hourWeatherRow(weatherDetails: WeatherDetails, hourIncrement: Int) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left element - hour:
        Row(horizontalArrangement = Arrangement.Start) {
            Text(
                text = "1$hourIncrement:00",
                color = Color.White
            )
        }
        
        // Right elements
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            // Weather description
            Text(
                text = weatherDetails.weather[0].description.capitalize(),
                color = Color.White
            )
            // Weather image:
            Image(
                modifier = Modifier
                    .size(30.dp),
                painter = rememberAsyncImagePainter(model = getWeatherIcon(weatherDetails)),
                contentDescription = stringResource(R.string.splash_icon_description),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.White)
            )
            // Weather temperature
            Text(
                text = "${weatherDetails.temp.day.roundToInt()}º",
                color = Color.White
            )
        }
    }
}