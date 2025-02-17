package com.rishirajput.weather.presentation.ui.compose

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.ui.theme.onSurfaceColor

/**
 * Composable function to display a search icon.
 */
@Composable
fun SearchIcon(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.icon_action_search),
        contentDescription = stringResource(R.string.search_icon),
        tint = onSurfaceColor,
        modifier = modifier.size(17.49.dp)
    )
}

/**
 * Preview function for SearchIcon composable.
 */
@Preview(showBackground = true)
@Composable
fun SearchIconPreview() {
    SearchIcon()
}