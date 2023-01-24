package com.sara.canwesail.view.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sara.canwesail.R
import com.sara.canwesail.model.WeatherModel
import com.sara.canwesail.view.util.getWeatherIcon
import com.sara.canwesail.view.util.integerToDayOfMonth
import com.sara.canwesail.view.util.integerToDayOfWeek

@Composable
fun getWeatherRowComponent(weatherModel: WeatherModel) {
    // Bottom elements:
    Column (
        Modifier
            .padding(start = 40.dp, end = 40.dp, bottom = 80.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ){
        // 1st row
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            // big temperature:
            Text(
                modifier = Modifier.background(color = Color.Transparent),
                text = "${weatherModel.list[0].temp.day.toInt()}º",
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Left,
                fontSize = 80.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            // weather icon:
            Image(
                modifier = Modifier
                    .size(100.dp),
                painter = rememberAsyncImagePainter(model = getWeatherIcon(weatherModel.list[0])),
                contentDescription = stringResource(R.string.splash_icon_description),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.White)
            )

            // day of month:
            Text(
                modifier = Modifier.background(color = Color.Transparent),
                text = integerToDayOfMonth(weatherModel.list[0].dt),
                style = MaterialTheme.typography.subtitle1,
                fontSize = 50.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )

        }

        // 2nd row
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                horizontalAlignment = Alignment.Start
            ) {
                // weather description:
                Text(
                    modifier = Modifier.background(color = Color.Transparent),
                    text = weatherModel.list[0].weather[0].main,
                    style = MaterialTheme.typography.caption,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
                // City / country field:
                Text(
                    modifier = Modifier.background(color = Color.Transparent),
                    text = "${weatherModel.city.name}, ${weatherModel.city.country}",
                    style = MaterialTheme.typography.overline,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )

            }

            // day of week:
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