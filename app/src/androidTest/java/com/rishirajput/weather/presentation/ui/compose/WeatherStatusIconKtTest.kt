import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.assertIsDisplayed
import com.rishirajput.weather.presentation.ui.compose.WeatherStatusIcon
import org.junit.Rule
import org.junit.Test

class WeatherStatusIconTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun weatherStatusIcon_isDisplayed() {
        val iconUrl = "//cdn.weatherapi.com/weather/64x64/night/176.png"

        composeTestRule.setContent {
            WeatherStatusIcon(iconUrl = iconUrl)
        }

        composeTestRule.onNodeWithContentDescription("Weather Icon")
            .assertIsDisplayed()
    }
}