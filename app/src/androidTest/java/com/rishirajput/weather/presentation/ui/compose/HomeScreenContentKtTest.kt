import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.weather.presentation.ui.compose.HomeScreenContent
import org.junit.Rule
import org.junit.Test

class HomeScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenContent_displaysStoredWeatherData() {
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
            HomeScreenContent(
                searchQuery = "",
                searchResults = emptyList(),
                storedWeatherData = dummyWeatherData,
                isLoading = false,
                onSearch = {},
                onLocationClick = {},
                focusManager = LocalFocusManager.current
            )
        }

        composeTestRule.onNodeWithText("Hyderabad").assertIsDisplayed()
        composeTestRule.onNodeWithText("31.0").assertIsDisplayed()
    }

    @Test
    fun homeScreenContent_displaysSearchResults() {
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
            HomeScreenContent(
                searchQuery = "Hyderabad",
                searchResults = dummyWeatherDataList,
                storedWeatherData = null,
                isLoading = false,
                onSearch = {},
                onLocationClick = {},
                focusManager = LocalFocusManager.current
            )
        }

        composeTestRule.onAllNodesWithText("Hyderabad")[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Mumbai").assertIsDisplayed()
    }
}