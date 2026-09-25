package com.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * Transparent launcher trampoline Activity.
 * When the user taps the app icon or launcher shortcut, this Activity
 * triggers android.settings.panel.action.WIFI and immediately finishes
 * itself without displaying any visible UI.
 */
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val launched = WifiPanelLauncher.openWifiPanel(this)

        if (!launched) {
            // Only show fallback UI if system panel failed to open
            val fallbackIntent = Intent(this, FallbackActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                putExtra(FallbackActivity.EXTRA_SHOW_UNAVAILABLE_WARNING, true)
            }
            startActivity(fallbackIntent)
        }

        finish()
        @Suppress("DEPRECATION")
        overridePendingTransition(0, 0)
    }
}
