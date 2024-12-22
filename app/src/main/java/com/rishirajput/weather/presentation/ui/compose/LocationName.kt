package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.ui.theme.textStyleCityName

@Composable
fun LocationName(locationName: String, modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = locationName,
            style = textStyleCityName
        )
        Spacer(modifier = Modifier.width(11.dp))
        Image(
            painter = painterResource(id = R.drawable.location_icon),
            contentDescription = "Location Icon",
            modifier = Modifier
                .width(21.dp)
                .height(21.dp)
                .align(Alignment.CenterVertically)
        )
    }
}