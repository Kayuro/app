package com.example.fakewifi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fakewifi.ui.MainScreen
import com.example.fakewifi.ui.theme.FakeWifiTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            FakeWifiTheme {
                MainScreen(
                    state = state,
                    onSliderChange = viewModel::updateRequestedCount,
                    onGenerateClick = viewModel::generateNetworks,
                    onRegenerateClick = viewModel::regenerateNetworks,
                    onStopClick = viewModel::stopNetworks
                )
            }
        }
    }
}
