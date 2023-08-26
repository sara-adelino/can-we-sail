package com.sara.canwesail.view.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sara.canwesail.R
import java.util.*

@Composable
fun getRadioButtonList(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) = LazyColumn (
    Modifier
        .padding(top = 30.dp)
        .fillMaxWidth(),
    horizontalAlignment = Alignment.End
) {
    items(items = options) { item ->
        Row(
            Modifier
                .padding(top = 16.dp)
                //.clip(shape = RoundedCornerShape(10.dp))
                .background(Color.Black.copy(0.3f))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End

        ) {

            ClickableText(
                text = AnnotatedString(item.capitalize(Locale.ROOT)),
                modifier = Modifier.padding(end = 20.dp),
                style = TextStyle(
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                onClick = {
                    onOptionSelected(item)
                }
            )

            IconToggleButton(
                checked = selectedOption == item,
                onCheckedChange = { onOptionSelected(item) }
            ) {
                Icon(
                    painter = painterResource
                        (
                        if (selectedOption == item) R.drawable.ic_baseline_radio_button_checked_24
                        else R.drawable.ic_baseline_radio_button_unchecked_24
                    ),
                    contentDescription = stringResource(R.string.radio_button_description),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
fun RadioButtonListPreview() {
    val options = listOf("Option 1", "Option 2", "Option 3")
    val selectedOption = "Option 2" // Replace with the desired selected option
    getRadioButtonList(
        options = options,
        selectedOption = selectedOption
    ) { /* Empty lambda for onOptionSelected */ }
}