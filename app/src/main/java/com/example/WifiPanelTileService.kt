package com.example

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

/**
 * Quick Settings tile service providing one-tap access to Android's system Wi-Fi panel.
 * Tapping this tile directly launches android.settings.panel.action.WIFI.
 */
class WifiPanelTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        val tile = qsTile ?: return
        tile.label = getString(R.string.tile_label) // "Wi-Fi"
        tile.subtitle = getString(R.string.tile_subtitle) // "Wi-Fi Panel"
        tile.state = Tile.STATE_INACTIVE
        tile.updateTile()
    }

    override fun onClick() {
        super.onClick()
        val intent = WifiPanelLauncher.createWifiPanelIntent()

        val pendingIntent = PendingIntent.getActivity(
            this,
            1001,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                startActivityAndCollapse(pendingIntent)
            } else {
                @Suppress("DEPRECATION")
                startActivityAndCollapse(intent)
            }
        } catch (_: Exception) {
            // If direct start fails, try launching fallback UI
            val fallbackIntent = Intent(this, FallbackActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra(FallbackActivity.EXTRA_SHOW_UNAVAILABLE_WARNING, true)
            }
            val fallbackPendingIntent = PendingIntent.getActivity(
                this,
                1002,
                fallbackIntent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    startActivityAndCollapse(fallbackPendingIntent)
                } else {
                    @Suppress("DEPRECATION")
                    startActivityAndCollapse(fallbackIntent)
                }
            } catch (_: Exception) {
            }
        }
    }
}
