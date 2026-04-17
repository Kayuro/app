package com.example.fakewifi.data

import kotlin.random.Random

class SSIDGenerator(
    private val random: Random = Random.Default
) {
    private val prefixTemplates = listOf(
        "Livebox-%04d",
        "FreeWifi_%02d",
        "TP-Link_%04X",
        "Bbox-%04d",
        "AndroidAP_%04d",
        "SFR_Box_%03d",
        "Maison_%s",
        "Studio_%s",
        "Cafe_%s",
        "Boulangerie_%s",
        "Guest_%s",
        "Fibre_%04d"
    )

    private val words = listOf(
        "INVITE",
        "SALON",
        "ETAGE",
        "FAST",
        "NORD",
        "SUD",
        "PRIVE",
        "LOUNGE",
        "NET",
        "HOME"
    )

    private val securityTypes = listOf("Open", "WPA2", "WPA3", "WPA2/WPA3")

    fun generateSsid(index: Int): String {
        val template = prefixTemplates.random(random)
        return when {
            template.contains("%04X") -> template.format(random.nextInt(0x1000, 0xFFFF))
            template.contains("%04d") -> template.format(random.nextInt(1000, 9999))
            template.contains("%03d") -> template.format(random.nextInt(100, 999))
            template.contains("%02d") -> template.format(random.nextInt(10, 99))
            template.contains("%s") -> template.format(words.random(random))
            else -> "$template-$index"
        }
    }

    fun generateSignalStrength(): Int = random.nextInt(1, 5)

    fun generateSecurityType(): String = securityTypes.random(random)
}
