package com.example.fakewifi.data

import com.example.fakewifi.model.FakeWifiNetwork
import java.util.UUID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FakeWifiManager(
    private val ssidGenerator: SSIDGenerator = SSIDGenerator(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    private val _networks = MutableStateFlow<List<FakeWifiNetwork>>(emptyList())
    val networks: StateFlow<List<FakeWifiNetwork>> = _networks.asStateFlow()

    private var generationJob: Job? = null

    fun generate(count: Int, scope: CoroutineScope) {
        val safeCount = count.coerceIn(1, MAX_NETWORKS)
        generationJob?.cancel()
        generationJob = scope.launch(dispatcher) {
            val generated = mutableListOf<FakeWifiNetwork>()
            _networks.value = emptyList()
            repeat(safeCount) { index ->
                generated += FakeWifiNetwork(
                    id = UUID.randomUUID().toString(),
                    ssid = ssidGenerator.generateSsid(index),
                    signalStrength = ssidGenerator.generateSignalStrength(),
                    securityType = ssidGenerator.generateSecurityType()
                )
                _networks.value = generated.toList()
                delay(40)
            }
        }
    }

    fun stop() {
        generationJob?.cancel()
        generationJob = null
        _networks.value = emptyList()
    }

    companion object {
        const val MAX_NETWORKS = 50
    }
}
