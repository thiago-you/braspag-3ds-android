package br.com.braspag.internal.network.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

internal data class RequestOrder(
    @SerializedName("orderNumber")
    val orderNumber: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("amount")
    val amount: Long,
    @SerializedName("objectifyPayload")
    val objectifyPayload: Boolean = true,
) : Serializable
