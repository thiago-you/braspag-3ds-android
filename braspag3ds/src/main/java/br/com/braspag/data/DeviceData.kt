package br.com.braspag.data

import androidx.annotation.Keep

@Keep
data class DeviceData(
    val fingerPrint: String,
    val provider: String
)