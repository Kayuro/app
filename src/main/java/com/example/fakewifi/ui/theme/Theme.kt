package com.example.fakewifi.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = ElectricBlue,
    secondary = Mint,
    tertiary = Cloud,
    background = Ink,
    surface = Slate,
    onPrimary = Cloud,
    onSecondary = Ink,
    onTertiary = Ink,
    onBackground = Cloud,
    onSurface = Cloud,
    onSurfaceVariant = Mist,
    error = Ember
)

@Composable
fun FakeWifiTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = AppTypography,
        content = content
    )
}
