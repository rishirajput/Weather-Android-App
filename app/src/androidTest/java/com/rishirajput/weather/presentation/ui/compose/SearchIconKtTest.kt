import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.assertIsDisplayed
import com.rishirajput.weather.presentation.ui.compose.SearchIcon
import org.junit.Rule
import org.junit.Test

class SearchIconTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchIcon_isDisplayed() {
        composeTestRule.setContent {
            SearchIcon()
        }

        composeTestRule.onNodeWithContentDescription("Search Icon")
            .assertIsDisplayed()
    }
}