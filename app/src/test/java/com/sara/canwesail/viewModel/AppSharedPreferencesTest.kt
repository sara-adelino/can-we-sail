package com.sara.canwesail.viewModel


import android.content.Context
import android.content.SharedPreferences
import com.sara.canwesail.TestExtension
import com.sara.canwesail.view.util.SharedConstants
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExtendWith(TestExtension::class)
internal class AppSharedPreferencesTest {

  /*  @Mock
    private lateinit var context: Context*/

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor

    //private lateinit var instrumentationContext: Context

    private lateinit var appSharedPreferences: AppSharedPreferences

    @BeforeEach
    fun init(context: ExtensionContext) {
        //instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        MockitoAnnotations.openMocks(this)
        sharedPreferences =  (context as Context).getSharedPreferences(SharedConstants.SHARED_PREF_REPO, Context.MODE_PRIVATE)
        edit = sharedPreferences.edit()
        appSharedPreferences = AppSharedPreferences(context)
    }

    @Test
    fun `Check if saved city on device is retrieved`() {
         // save the city on device:
        edit.putString(SharedConstants.SHARED_PREF_CURRENT_CITY_KEY, "Berlin")

        assertEquals(appSharedPreferences.getStoredCityOrDefault(), "Berlin")

    }

    @Test
    fun saveCityId() {
    }
}