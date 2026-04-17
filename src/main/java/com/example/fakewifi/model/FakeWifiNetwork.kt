package com.example.fakewifi.model

data class FakeWifiNetwork(
    val id: String,
    val ssid: String,
    val signalStrength: Int,
    val securityType: String
)
