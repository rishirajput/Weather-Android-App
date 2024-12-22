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
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.ui.theme.textStyleTemperatureLarge


@Composable
fun CurrentTemperature(temperature: Double, modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "$temperature",
            style = textStyleTemperatureLarge,
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