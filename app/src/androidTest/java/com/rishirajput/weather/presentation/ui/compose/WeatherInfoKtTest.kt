import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.weather.presentation.ui.compose.WeatherInfo
import org.junit.Rule
import org.junit.Test

class WeatherInfoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun weatherInfo_displaysCorrectData() {
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
            WeatherInfo(weatherData = dummyWeatherData)
        }

        composeTestRule.onNodeWithText("Humidity").assertIsDisplayed()
        composeTestRule.onNodeWithText("76%").assertIsDisplayed()
        composeTestRule.onNodeWithText("UV").assertIsDisplayed()
        composeTestRule.onNodeWithText("4.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("Feels Like").assertIsDisplayed()
        composeTestRule.onNodeWithText("30.4°").assertIsDisplayed()
    }
}