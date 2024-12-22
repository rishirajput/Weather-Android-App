package com.rishirajput.weather.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.presentation.ui.theme.WeatherTheme
import com.rishirajput.weather.presentation.ui.compose.HomeScreen

/**
 * Main activity that hosts the home screen of the weather app.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(innerPadding)
                }
            }
        }
    }
}

/**
 * Preview function for HomeActivity.
 */
@Preview(showBackground = true)
@Composable
fun HomeActivityPreview() {
    WeatherTheme {
        HomeScreen(innerPadding = PaddingValues(0.dp))
    }
}