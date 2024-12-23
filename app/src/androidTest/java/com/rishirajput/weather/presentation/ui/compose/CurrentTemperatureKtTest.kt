import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import com.rishirajput.weather.presentation.ui.compose.CurrentTemperature
import org.junit.Rule
import org.junit.Test

class CurrentTemperatureTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun currentTemperature_displaysTemperature() {
        val temperature = 25.0

        composeTestRule.setContent {
            CurrentTemperature(temperature = temperature)
        }

        composeTestRule.onNodeWithText("25.0").assertIsDisplayed()
    }

    @Test
    fun currentTemperature_displaysDegreeIcon() {
        val temperature = 25.0

        composeTestRule.setContent {
            CurrentTemperature(temperature = temperature)
        }

        composeTestRule.onNodeWithContentDescription("Degree Icon").assertIsDisplayed()
    }
}