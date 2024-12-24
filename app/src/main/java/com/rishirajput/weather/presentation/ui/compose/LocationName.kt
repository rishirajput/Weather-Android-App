package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.ui.theme.textStyleCityName

/**
 * Composable function to display the location name with an icon.
 *
 * @param locationName The name of the location to display.
 * @param modifier The modifier to be applied to the composable.
 */
@Composable
fun LocationName(locationName: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = locationName,
            style = textStyleCityName,
            modifier = Modifier.widthIn(max = 268.dp)
        )
        Spacer(modifier = Modifier.width(11.dp))
        Image(
            painter = painterResource(id = R.drawable.location_icon),
            contentDescription = stringResource(R.string.location_icon),
            modifier = Modifier
                .width(21.dp)
                .height(21.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

/**
 * Preview function for LocationName composable.
 */
@Preview(showBackground = true)
@Composable
fun PreviewLocationName() {
    LocationName(locationName = "YoriCostio (La Villita)")
}