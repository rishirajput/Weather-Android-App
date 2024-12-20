package com.rishirajput.weather.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.theme.onSurfaceColor

@Composable
fun SearchIcon() {
    Icon(
        painter = painterResource(id = R.drawable.icon_action_search),
        contentDescription = "Search Icon",
        tint = onSurfaceColor,
        modifier = Modifier.size(17.49.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SearchIconPreview() {
    SearchIcon()
}