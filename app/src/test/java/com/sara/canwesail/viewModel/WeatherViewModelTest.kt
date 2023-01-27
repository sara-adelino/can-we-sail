package com.sara.canwesail.viewModel


import com.sara.canwesail.model.FakeWeatherRepository
import com.sara.canwesail.model.ModelConstants.DEFAULT_CITY
import com.sara.canwesail.model.getFakeWeatherModelObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class WeatherViewModelTest {

    private var fakeRepository: FakeWeatherRepository = FakeWeatherRepository()

    private var weatherViewModel: WeatherViewModel = WeatherViewModel(repository = fakeRepository)

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterAll
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `check if current city is retrieved`() {
        assertEquals(DEFAULT_CITY, weatherViewModel.getCurrentCity())
    }

    @Test
    fun `check if other city is stored`() {
        weatherViewModel.setCitySelected("london")
        assertEquals("london", weatherViewModel.getCurrentCity())
    }

    @Test
    fun `check if current weather saved in repository is retrieved by view model`() {
        assertEquals(getFakeWeatherModelObject(), weatherViewModel.getCurrentWeatherForecast())
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `check if view model retrieves the weather information from the API trough the repository`()  = runTest {

        launch(Dispatchers.Main) { weatherViewModel.getWeatherForCurrentCity() }

        advanceUntilIdle()
        assertEquals(
            getFakeWeatherModelObject(),
            weatherViewModel.getCurrentWeatherForecast()
        )
    }

}