import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import com.rishirajput.weather.presentation.ui.compose.LocationName
import org.junit.Rule
import org.junit.Test

class LocationNameTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun locationName_displaysLocationName() {
        val locationName = "Hyderabad"

        composeTestRule.setContent {
            LocationName(locationName = locationName)
        }

        composeTestRule.onNodeWithText(locationName).assertIsDisplayed()
    }

    @Test
    fun locationName_displaysLocationIcon() {
        composeTestRule.setContent {
            LocationName(locationName = "Hyderabad")
        }

        composeTestRule.onNodeWithContentDescription("Location Icon").assertIsDisplayed()
    }
}