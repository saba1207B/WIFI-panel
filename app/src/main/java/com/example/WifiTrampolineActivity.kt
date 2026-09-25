package com.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * Secondary transparent trampoline Activity for App Widgets and external shortcuts.
 * Fires the system Wi-Fi panel and immediately finishes without any visible UI.
 */
class WifiTrampolineActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val launched = WifiPanelLauncher.openWifiPanel(this)

        if (!launched) {
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
