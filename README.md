# WiFi Panel Launcher

[![Android CI](https://github.com/saba1207B/WIFI-panel/actions/workflows/android.yml/badge.svg)](https://github.com/saba1207B/WIFI-panel/actions/workflows/android.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

An ultra-lightweight, native Android utility that provides instant, one-tap access to Android's native Wi-Fi system panel using:

`android.settings.panel.action.WIFI`

It is especially useful on devices where the regular Wi-Fi Settings activity opens the wrong screen or behaves unexpectedly.

> ⚠️ **Experimental project — Not production ready.** This project is intended for testing, experimentation, and personal use.

## 📥 Download

[![Download Experimental APK](https://img.shields.io/badge/Download-Experimental%20APK-orange?style=for-the-badge&logo=android)](https://github.com/saba1207B/WIFI-panel/releases/latest)

**Download the APK from the [Releases](https://github.com/saba1207B/WIFI-panel/releases) page.**

The current release is a **pre-release experimental debug build**. It is not intended for production deployment or Google Play distribution.

GitHub Actions is used to build and verify the APK, but **Releases are the official download location** for users.

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

GitHub Actions automatically builds and verifies the debug APK on pushes and pull requests to `main`.

## 📂 Project Structure

- `MainActivity` — launcher entry point
- `WifiTrampolineActivity` — widget launch activity
- `WifiPanelTileService` — Quick Settings tile
- `WifiPanelWidgetProvider` — home-screen widget
- `FallbackActivity` — fallback/error screen
- `WifiPanelLauncher` — centralized Wi-Fi panel intent handling

## ⚠️ Experimental Status

This project is **not production ready**.

It was created primarily as an experimental workaround for a Wi-Fi Settings behavior observed on certain Motorola/Android 16 devices.

Compatibility and behavior may vary depending on the device manufacturer and Android version.

This application does **not** replace, modify, or repair the Android Settings application.

If the manufacturer changes or removes the `android.settings.panel.action.WIFI` intent in a future system update, compatibility may change.

## 📄 License

This project is licensed under the MIT License. See [LICENSE](LICENSE).

---

Made by **Sabareesh M**
