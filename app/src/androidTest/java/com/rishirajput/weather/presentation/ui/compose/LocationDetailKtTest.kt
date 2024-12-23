import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.weather.presentation.ui.compose.LocationDetail
import org.junit.Rule
import org.junit.Test

class LocationDetailTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun locationDetail_displaysWeatherData() {
        val dummyWeatherData = WeatherData(
            locationName = "Hyderabad",
            temperature = 31.0,
            condition = "Patchy rain nearby",
            icon = "//cdn.weatherapi.com/weather/64x64/night/176.png",
            humidity = 76,
            uvIndex = 4.0,
            feelsLike = 30.4
        )

        composeTestRule.setContent {
            LocationDetail(weatherData = dummyWeatherData)
        }

        composeTestRule.onNodeWithText("Hyderabad").assertIsDisplayed()
        composeTestRule.onNodeWithText("31.0").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Weather Icon").assertIsDisplayed()
    }
}