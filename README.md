# WiFi Panel Launcher

An ultra-lightweight, native Android utility built exclusively to provide instant, one-tap access to Android's native system Wi-Fi popup panel (`android.settings.panel.action.WIFI`).

Designed for Motorola and modern Android devices (tested up through Android 16) where standard full settings (`android.settings.WIFI_SETTINGS`) may behave inconsistently, but the system panel opens immediately and reliably.

---

## 🚀 Core Architectural Principle: Zero Visible App UI

The user experience is designed so the application itself **never appears visibly** during standard operation:

```
User taps shortcut (App Icon / Widget / Quick Settings Tile / Long-Press)
       ↓
Android system Wi-Fi popup panel appears immediately
       ↓
User selects and connects to Wi-Fi network
```

- **No custom Wi-Fi network scanning or connection lists** (Android's system panel handles all Wi-Fi state, security, and connections).
- **No splash screen, loading screen, or intermediary UI**.
- Uses an invisible, translucent trampoline Activity (`android:theme="@style/Theme.Transparent"`) with `overridePendingTransition(0, 0)` that calls `finish()` in `onCreate()`.

---

## ⚡ 4 Direct Launch Access Methods

1. **Launcher App Icon**
   - Tap "WiFi Panel Launcher" from the home screen or app drawer.
   - Transparent `MainActivity` immediately dispatches `android.settings.panel.action.WIFI` and closes itself.
2. **Home-Screen Widget (1x1 & 2x1)**
   - Add the "Wi-Fi Panel" widget to your home screen.
   - Tapping anywhere on the widget triggers `WifiTrampolineActivity` to pop up the system panel without touching the app UI.
3. **Quick Settings Tile**
   - Pull down the Android notification shade and add the "Wi-Fi Panel" tile (`TileService`).
   - Tapping the tile immediately launches `android.settings.panel.action.WIFI` via modern `startActivityAndCollapse` (supports Android 14+ / 16 PendingIntents).
4. **Launcher Long-Press App Shortcut**
   - Long-press the app icon and select "Open Wi-Fi".
   - Immediately displays the system Wi-Fi popup.

---

## 🛡️ Privacy & Permissions

- **Permissions requested:** 0 (None)
- **Background services:** None (zero battery impact)
- **Network requests:** None (no internet permission, fully offline)
- **Compliant with modern Android & Google Play security guidelines**.

---

## 🧪 Testing Guide

### 1. Test Launcher Icon Launch
- Tap the **WiFi Panel Launcher** app icon on the home screen.
- **Verification:** The Android native Wi-Fi panel popup should appear immediately. No activity window, background, or splash screen should flash.

### 2. Test Home Screen Widget
- Long-press the home screen and select **Widgets**.
- Locate **Wi-Fi Panel** and drag it to your screen (1x1 or 2x1).
- Tap the widget.
- **Verification:** The system Wi-Fi panel immediately opens.

### 3. Test Quick Settings Tile
- Swipe down twice from the top of your screen to open the full Quick Settings panel.
- Tap the Edit (pencil) button.
- Locate the **Wi-Fi Panel** tile (label: "Wi-Fi") and drag it to your active tiles.
- Tap the tile.
- **Verification:** The notification shade collapses and the Android system Wi-Fi popup appears.

### 4. Test Launcher Long-Press Shortcut
- Long-press the **WiFi Panel Launcher** app icon until the shortcut menu appears.
- Tap **Open Wi-Fi**.
- **Verification:** The system Wi-Fi panel opens immediately.

### 5. Test Fallback UI & Error Handling
- If `android.settings.panel.action.WIFI` cannot be resolved on a device/emulator without the panel service, `FallbackActivity` will display:
  - Error banner: *"Wi-Fi panel is unavailable on this device."*
  - Diagnostic information showing target intent `android.settings.panel.action.WIFI`.
  - Manual *"Open Wi-Fi Panel"* button.

---

## 🛠️ Project Structure

- `com.example.WifiPanelLauncher`: Central singleton managing the `android.settings.panel.action.WIFI` intent and availability check.
- `com.example.MainActivity`: Primary transparent trampoline activity handling launcher clicks and shortcuts.
- `com.example.WifiTrampolineActivity`: Dedicated secondary transparent trampoline for widgets.
- `com.example.WifiPanelTileService`: `TileService` integration for the Android Quick Settings shade.
- `com.example.WifiPanelWidgetProvider`: `AppWidgetProvider` for 1x1 / 2x1 desktop widgets.
- `com.example.FallbackActivity`: Material 3 diagnostic and manual fallback UI.
