import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.weather.presentation.ui.compose.LocationResultCard
import org.junit.Rule
import org.junit.Test

class LocationResultCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun locationResultCard_displaysWeatherData() {
        val dummyWeatherData = WeatherData(
            locationName = "LakeFront Airport",
            temperature = 27.3,
            condition = "Patchy rain nearby",
            icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
            humidity = 76,
            uvIndex = 0.1,
            feelsLike = 30.4
        )

        composeTestRule.setContent {
            LocationResultCard(weatherData = dummyWeatherData, onClick = {})
        }

        composeTestRule.onNodeWithText("LakeFront Airport").assertIsDisplayed()
        composeTestRule.onNodeWithText("27.3").assertIsDisplayed()
    }

    @Test
    fun locationResultCard_performsClickAction() {
        val dummyWeatherData = WeatherData(
            locationName = "LakeFront Airport",
            temperature = 27.3,
            condition = "Patchy rain nearby",
            icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
            humidity = 76,
            uvIndex = 0.1,
            feelsLike = 30.4
        )
        var clicked = false

        composeTestRule.setContent {
            LocationResultCard(weatherData = dummyWeatherData, onClick = { clicked = true })
        }

        composeTestRule.onNodeWithText("LakeFront Airport").performClick()
        assert(clicked)
    }
}