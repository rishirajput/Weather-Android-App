import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import com.rishirajput.weather.presentation.ui.compose.SearchBar
import org.junit.Rule
import org.junit.Test

class SearchBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchBar_displaysPlaceholderText() {
        val placeholderText = "Search location"

        composeTestRule.setContent {
            SearchBar(
                query = "",
                placeholderText = placeholderText,
                onSearch = {}
            )
        }

        composeTestRule.onNodeWithText(placeholderText).assertIsDisplayed()
    }

    @Test
    fun searchBar_displaysSearchIcon() {
        composeTestRule.setContent {
            SearchBar(
                query = "",
                placeholderText = "Search location",
                onSearch = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("Search Icon").assertIsDisplayed()
    }
}