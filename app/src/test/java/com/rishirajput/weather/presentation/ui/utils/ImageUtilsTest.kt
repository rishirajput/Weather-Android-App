import com.rishirajput.weather.presentation.ui.utils.updateImageSizeInUrl
import org.junit.Assert.assertEquals
import org.junit.Test

class ImageUtilsTest {

    @Test
    fun `test updateImageSizeInUrl with valid URL`() {
        val originalUrl = "https://example.com/images/100x100/sample.jpg"
        val newSize = 200
        val expectedUrl = "https://example.com/images/200x200/sample.jpg"

        val result = updateImageSizeInUrl(originalUrl, newSize)

        assertEquals(expectedUrl, result)
    }

    @Test
    fun `test updateImageSizeInUrl with URL without size`() {
        val originalUrl = "https://example.com/images/sample.jpg"
        val newSize = 200
        val expectedUrl = "https://example.com/images/sample.jpg" // No change expected

        val result = updateImageSizeInUrl(originalUrl, newSize)

        assertEquals(expectedUrl, result)
    }

    @Test
    fun `test updateImageSizeInUrl with different size pattern`() {
        val originalUrl = "https://example.com/images/300x300/sample.jpg"
        val newSize = 150
        val expectedUrl = "https://example.com/images/150x150/sample.jpg"

        val result = updateImageSizeInUrl(originalUrl, newSize)

        assertEquals(expectedUrl, result)
    }
}