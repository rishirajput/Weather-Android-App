import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.weather.presentation.ui.compose.LocationResults
import org.junit.Rule
import org.junit.Test

class LocationResultsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun locationResults_displaysWeatherData() {
        val dummyWeatherDataList = listOf(
            WeatherData(
                locationName = "Hyderabad",
                temperature = 31.0,
                condition = "Patchy rain nearby",
                icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
                humidity = 76,
                uvIndex = 4.0,
                feelsLike = 30.4
            ),
            WeatherData(
                locationName = "Mumbai",
                temperature = 29.0,
                condition = "Clear",
                icon = "//cdn.weatherapi.com/weather/64x64/night/113.png",
                humidity = 80,
                uvIndex = 5.0,
                feelsLike = 32.0
            )
        )

        composeTestRule.setContent {
            LocationResults(
                searchResults = dummyWeatherDataList,
                onLocationClick = {},
                focusManager = LocalFocusManager.current
            )
        }

        composeTestRule.onNodeWithText("Hyderabad").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mumbai").assertIsDisplayed()
    }

    @Test
    fun locationResults_performsClickAction() {
        val dummyWeatherDataList = listOf(
            WeatherData(
                locationName = "Hyderabad",
                temperature = 31.0,
                condition = "Patchy rain nearby",
                icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
                humidity = 76,
                uvIndex = 4.0,
                feelsLike = 30.4
            )
        )
        var clickedWeatherData: WeatherData? = null

        composeTestRule.setContent {
            LocationResults(
                searchResults = dummyWeatherDataList,
                onLocationClick = { weatherData ->
                    clickedWeatherData = weatherData
                },
                focusManager = LocalFocusManager.current
            )
        }

        composeTestRule.onNodeWithText("Hyderabad").performClick()
        assert(clickedWeatherData == dummyWeatherDataList[0])
    }
}