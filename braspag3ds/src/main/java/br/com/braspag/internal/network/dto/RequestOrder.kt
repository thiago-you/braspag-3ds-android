package br.com.braspag.internal.network.dto

import com.google.gson.annotations.SerializedName

internal data class RequestOrder(

    @SerializedName("OrderNumber")
    val orderNumber: String,

    @SerializedName("Currency")
    val currency: String,

    @SerializedName("Amount")
    val amount: Long,

    @SerializedName("ObjectifyPayload")
    val objectifyPayload: Boolean = true
)