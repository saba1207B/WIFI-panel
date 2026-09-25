package com.example

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

/**
 * Core utility for launching Android's native system Wi-Fi popup panel.
 * Strictly uses the intent: android.settings.panel.action.WIFI
 */
object WifiPanelLauncher {

    const val ACTION_WIFI_PANEL = "android.settings.panel.action.WIFI"

    /**
     * Creates the Intent for the native system Wi-Fi panel.
     */
    fun createWifiPanelIntent(): Intent {
        return Intent(ACTION_WIFI_PANEL).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    /**
     * Queries whether the current device/ROM resolves the Wi-Fi panel intent.
     */
    fun isWifiPanelAvailable(context: Context): Boolean {
        val intent = createWifiPanelIntent()
        val resolveList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.queryIntentActivities(
                intent,
                PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
            )
        } else {
            @Suppress("DEPRECATION")
            context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        }
        return resolveList.isNotEmpty()
    }

    /**
     * Launches the Android system Wi-Fi panel.
     *
     * @param context Calling context
     * @return true if the panel was launched successfully, false otherwise.
     */
    fun openWifiPanel(context: Context): Boolean {
        return try {
            val intent = createWifiPanelIntent()
            context.startActivity(intent)
            true
        } catch (e: ActivityNotFoundException) {
            false
        } catch (e: SecurityException) {
            false
        } catch (e: Exception) {
            false
        }
    }
}
