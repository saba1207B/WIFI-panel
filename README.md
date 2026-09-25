# WiFi Panel Launcher

[![Android CI](https://github.com/saba1207B/WIFI-panel/actions/workflows/android.yml/badge.svg)](https://github.com/saba1207B/WIFI-panel/actions/workflows/android.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

An ultra-lightweight, native Android utility that provides instant, one-tap access to Android's native Wi-Fi system panel using:

`android.settings.panel.action.WIFI`

It is especially useful on devices where the regular Wi-Fi Settings activity opens the wrong screen or behaves unexpectedly.

## 📥 Download

**[Download the latest debug APK from GitHub Actions](https://github.com/saba1207B/WIFI-panel/actions/runs/36123899878#artifacts)**

> The APK is built automatically by GitHub Actions. The current workflow artifact is a debug build intended for testing and direct installation. GitHub Actions artifacts have a limited retention period, so a future version can be published as a GitHub Release for a permanent download.

## ✨ Features

- 🚀 One-tap access to the native Android Wi-Fi panel
- 📱 Launcher app icon
- 🧩 Home-screen widget
- ⚡ Quick Settings tile
- 📌 Launcher long-press shortcut
- 🪶 Ultra-lightweight design
- 🔒 No root, accessibility service, VPN, or device-admin access
- 🌐 No internet connection required
- 📍 No location permission
- 🔋 No background service

## 🧠 How It Works

The app does **not** implement its own Wi-Fi settings screen.

```
User taps App Icon / Widget / Quick Settings Tile / Shortcut
                         ↓
        android.settings.panel.action.WIFI
                         ↓
          Android system Wi-Fi panel
                         ↓
             User manages Wi-Fi
```

The app uses a transparent trampoline activity where Android requires an activity context, then immediately opens the system panel and finishes.

## ⚡ Launch Methods

### 1. Launcher App Icon

Tap **WiFi Panel Launcher** from the home screen or app drawer.

The transparent activity immediately launches the Android system Wi-Fi panel.

### 2. Home-Screen Widget

Add the **Wi-Fi Panel** widget to the home screen.

Supported sizes:
- 1×1
- 2×1

Tap the widget to open the system Wi-Fi panel.

### 3. Quick Settings Tile

Add **Wi-Fi Panel** to the Android Quick Settings area.

Tap the tile to launch the native Wi-Fi panel.

### 4. Launcher Long-Press Shortcut

Long-press the app icon and select **Open Wi-Fi**.

The system Wi-Fi panel opens immediately.

## 🔐 Privacy

WiFi Panel Launcher is designed to be completely local and minimal.

- **Permissions requested:** None
- **Internet access:** None
- **Location access:** None
- **Background services:** None
- **User data collection:** None
- **Custom Wi-Fi scanning:** None
- **Custom Wi-Fi connection handling:** None

All Wi-Fi operations are handled by Android's own system UI.

## 📱 Compatibility

Developed and tested with modern Android versions, including Android 16.

The exact behavior of `android.settings.panel.action.WIFI` depends on the Android device manufacturer and system software.

## 🛠️ Build

Requirements:

- Android Studio with Android SDK 36
- JDK 17
- Gradle 9.3.1

Build the debug APK with:

```bash
gradle assembleDebug
```

The GitHub Actions workflow also builds the APK automatically on pushes and pull requests to `main`.

## 📂 Project Structure

- `MainActivity` — launcher entry point
- `WifiTrampolineActivity` — widget launch activity
- `WifiPanelTileService` — Quick Settings tile
- `WifiPanelWidgetProvider` — home-screen widget
- `FallbackActivity` — fallback/error screen
- `WifiPanelLauncher` — centralized Wi-Fi panel intent handling

## ⚠️ Important

This application does **not** replace or modify Android's Settings application.

It simply provides a convenient shortcut to the system Wi-Fi panel.

If the manufacturer changes or removes the `android.settings.panel.action.WIFI` intent in a future Android/system update, compatibility may change.

## 📄 License

This project is licensed under the MIT License. See [LICENSE](LICENSE).

---

Made by **Sabareesh M**
