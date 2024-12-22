package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.ui.theme.textStyleTemperatureLarge

/**
 * Composable function to display the current temperature with a degree icon.
 *
 * @param temperature The current temperature to display.
 * @param modifier The modifier to be applied to the Row layout.
 * @param textStyle The text style to be applied to the temperature text.
 */
@Composable
fun CurrentTemperature(
    temperature: Double,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = textStyleTemperatureLarge
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "$temperature",
            style = textStyle,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(11.dp))
        Image(
            painter = painterResource(id = R.drawable.degree_icon),
            contentDescription = "Degree Icon",
            modifier = Modifier.align(Alignment.Top).offset(y = 16.dp)
        )
    }
}