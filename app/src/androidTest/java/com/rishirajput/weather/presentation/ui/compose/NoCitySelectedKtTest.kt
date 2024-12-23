import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.rishirajput.weather.R
import com.rishirajput.weather.presentation.ui.compose.NoCitySelected
import org.junit.Rule
import org.junit.Test

class NoCitySelectedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun noCitySelected_displaysNoCitySelectedText() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val noCitySelectedText = context.getString(R.string.no_city_selected)

        composeTestRule.setContent {
            NoCitySelected()
        }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(noCitySelectedText).assertIsDisplayed()
    }

    @Test
    fun noCitySelected_displaysPleaseSearchForCityText() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val pleaseSearchForCityText = context.getString(R.string.please_search_for_a_city)

        composeTestRule.setContent {
            NoCitySelected()
        }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(pleaseSearchForCityText).assertIsDisplayed()
    }
}