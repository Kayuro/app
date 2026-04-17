package com.example.fakewifi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakewifi.data.FakeWifiManager
import com.example.fakewifi.model.FakeWifiNetwork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class MainUiState(
    val requestedCount: Int = 12,
    val activeNetworks: List<FakeWifiNetwork> = emptyList(),
    val isGenerating: Boolean = false
)

class MainViewModel(
    private val fakeWifiManager: FakeWifiManager = FakeWifiManager()
) : ViewModel() {
    private val requestedCount = MutableStateFlow(12)
    private val isGenerating = MutableStateFlow(false)

    val uiState: StateFlow<MainUiState> = combine(
        requestedCount,
        fakeWifiManager.networks,
        isGenerating
    ) { count, networks, generating ->
        MainUiState(
            requestedCount = count,
            activeNetworks = networks,
            isGenerating = generating && networks.size < count
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState()
    )

    fun updateRequestedCount(value: Float) {
        requestedCount.update { value.toInt().coerceIn(1, FakeWifiManager.MAX_NETWORKS) }
    }

    fun generateNetworks() {
        isGenerating.value = true
        fakeWifiManager.generate(requestedCount.value, viewModelScope)
    }

    fun regenerateNetworks() {
        generateNetworks()
    }

    fun stopNetworks() {
        isGenerating.value = false
        fakeWifiManager.stop()
    }
}
