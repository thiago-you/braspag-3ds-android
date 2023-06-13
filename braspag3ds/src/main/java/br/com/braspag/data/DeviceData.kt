package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DeviceData(
    @SerializedName("fingerPrint")
    val fingerPrint: String,
    @SerializedName("provider")
    val provider: String,
) : Serializable
