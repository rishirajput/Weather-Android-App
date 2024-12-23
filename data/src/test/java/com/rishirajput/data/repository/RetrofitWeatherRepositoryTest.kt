import com.rishirajput.data.api.WeatherApiService
import com.rishirajput.data.repository.RetrofitWeatherRepository
import com.rishirajput.domain.errors.InvalidCityException
import com.rishirajput.domain.errors.NoNetworkException
import com.rishirajput.domain.model.Condition
import com.rishirajput.domain.model.CurrentWeather
import com.rishirajput.domain.model.Location
import com.rishirajput.domain.model.Result
import com.rishirajput.domain.model.WeatherData
import com.rishirajput.domain.model.WeatherResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class RetrofitWeatherRepositoryTest {

    private lateinit var apiService: WeatherApiService
    private lateinit var repository: RetrofitWeatherRepository

    @Before
    fun setUp() {
        apiService = mockk()
        repository = RetrofitWeatherRepository(apiService, "test_api_key", 300L)
    }

    @Test
    fun `getWeatherData returns empty list for empty query`() = runTest {
        val result: Result<List<WeatherData>> = repository.getWeatherData("")
        assertEquals(Result.Success(emptyList<CurrentWeather>()), result)
    }

    @Test
    fun `getWeatherData returns weather data for valid query`() = runTest {
        val location = Location(10, "Test City", "Region", "Country", 0.0, 0.0, "url")
        val condition = Condition("Clear", "icon_url")
        val currentWeather = CurrentWeather(25.0, condition, 60, 5.0, 25.0)
        val weatherResponse = WeatherResponse(currentWeather)
        coEvery { apiService.searchLocation(any(), any()) } returns Response.success(listOf(location))
        coEvery { apiService.getCurrentWeather(any(), any()) } returns Response.success(weatherResponse)

        val result: Result<List<WeatherData>> = repository.getWeatherData("Test City")
        assert(result is Result.Success)
        result as Result.Success

        val expected = currentWeather
        val actual = result.data[0]

        assertEquals(expected.tempC, actual.temperature, 0.0)
        assertEquals(expected.condition.text, actual.condition)
        assertEquals(expected.condition.icon, actual.icon)
        assertEquals(expected.humidity, actual.humidity)
        assertEquals(expected.uv, actual.uvIndex, 0.0)
        assertEquals(expected.feelsLikeC, actual.feelsLike, 0.0)
    }

    @Test
    fun `getWeatherData returns error for invalid city`() = runTest {
        coEvery { apiService.searchLocation(any(), any()) } returns Response.success(emptyList())

        val result: Result<List<WeatherData>> = repository.getWeatherData("Invalid City")
        assert(result is Result.Error && result.exception is InvalidCityException)
    }

    @Test
    fun `getWeatherData returns error for network error`() = runTest {
        coEvery { apiService.searchLocation(any(), any()) } throws IOException()

        val result: Result<List<WeatherData>> = repository.getWeatherData("Test City")
        assert(result is Result.Error && result.exception is NoNetworkException)
    }

    @Test
    fun `getLocations returns locations for valid query`() = runTest {
        val location = Location(11, "Test City", "Region", "Country", 0.0, 0.0, "url")
        coEvery { apiService.searchLocation(any(), any()) } returns Response.success(listOf(location))

        val result: Result<List<Location>> = repository.getLocations("Test City")
        assertEquals(Result.Success(listOf(location)), result)
    }

    @Test
    fun `getLocations returns error for network error`() = runTest {
        coEvery { apiService.searchLocation(any(), any()) } throws IOException()

        val result: Result<List<Location>> = repository.getLocations("Test City")
        assert(result is Result.Error && result.exception is NoNetworkException)
    }

    @Test
    fun `getWeatherDataForLocation returns weather data for valid location`() = runTest {
        val condition = Condition("Clear", "icon_url")
        val currentWeather = CurrentWeather(25.0, condition, 60, 5.0, 25.0)
        val weatherResponse = WeatherResponse(currentWeather)
        coEvery { apiService.getCurrentWeather(any(), any()) } returns Response.success(weatherResponse)

        val result: Result<WeatherData> = repository.getWeatherDataForLocation("Test City")
        assert(result is Result.Success)
        result as Result.Success

        val expected = currentWeather
        val actual = result.data

        assertEquals(expected.tempC, actual.temperature, 0.0)
        assertEquals(expected.condition.text, actual.condition)
        assertEquals(expected.condition.icon, actual.icon)
        assertEquals(expected.humidity, actual.humidity)
        assertEquals(expected.uv, actual.uvIndex, 0.0)
        assertEquals(expected.feelsLikeC, actual.feelsLike, 0.0)
    }

    @Test
    fun `getWeatherDataForLocation returns error for network error`() = runTest {
        coEvery { apiService.getCurrentWeather(any(), any()) } throws IOException()

        val result: Result<WeatherData> = repository.getWeatherDataForLocation("Test City")
        assert(result is Result.Error && result.exception is NoNetworkException)
    }
}