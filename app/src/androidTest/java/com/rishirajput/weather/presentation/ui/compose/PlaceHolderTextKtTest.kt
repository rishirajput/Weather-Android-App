import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import com.rishirajput.weather.presentation.ui.compose.PlaceHolderText
import org.junit.Rule
import org.junit.Test

class PlaceHolderTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun placeHolderText_displaysCorrectText() {
        val placeholderText = "Search Location"

        composeTestRule.setContent {
            PlaceHolderText(placeholderText = placeholderText)
        }

        // Assert that the placeholder text is displayed
        composeTestRule.onNodeWithText(placeholderText).assertIsDisplayed()
    }

    @Test
    fun placeHolderText_displaysDifferentText() {
        val placeholderText = "Enter City"

        composeTestRule.setContent {
            PlaceHolderText(placeholderText = placeholderText)
        }

        // Assert that the placeholder text is displayed
        composeTestRule.onNodeWithText(placeholderText).assertIsDisplayed()
    }
}