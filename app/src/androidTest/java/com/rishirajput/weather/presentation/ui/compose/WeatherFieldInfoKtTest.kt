import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import com.rishirajput.weather.presentation.ui.compose.WeatherFieldInfo
import org.junit.Rule
import org.junit.Test

class WeatherFieldInfoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun weatherFieldInfo_displaysCorrectLabelAndValue() {
        val label = "Humidity"
        val value = "76%"

        composeTestRule.setContent {
            WeatherFieldInfo(label = label, value = value)
        }

        composeTestRule.onNodeWithText(label).assertIsDisplayed()
        composeTestRule.onNodeWithText(value).assertIsDisplayed()
    }
}