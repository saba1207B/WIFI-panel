package com.example

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("WiFi Panel Launcher", appName)
    }

    @Test
    fun `verify wifi panel intent action is strictly android settings panel action WIFI`() {
        assertEquals("android.settings.panel.action.WIFI", WifiPanelLauncher.ACTION_WIFI_PANEL)
        val intent = WifiPanelLauncher.createWifiPanelIntent()
        assertEquals(WifiPanelLauncher.ACTION_WIFI_PANEL, intent.action)
        assertTrue((intent.flags and Intent.FLAG_ACTIVITY_NEW_TASK) != 0)
    }

    @Test
    fun `main activity finishes immediately without visible lingering ui`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity { activity ->
            assertTrue(activity.isFinishing || activity.isDestroyed)
        }
        scenario.close()
    }

    @Test
    fun `wifi trampoline activity finishes immediately`() {
        val scenario = ActivityScenario.launch(WifiTrampolineActivity::class.java)
        scenario.onActivity { activity ->
            assertTrue(activity.isFinishing || activity.isDestroyed)
        }
        scenario.close()
    }

    @Test
    fun `tile service metadata and labels are accurate`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertEquals("Wi-Fi", context.getString(R.string.tile_label))
        assertEquals("Wi-Fi Panel", context.getString(R.string.tile_name))
    }
}
