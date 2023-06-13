package br.com.braspag.internal.network.dto

internal data class RequestOrder(

    val orderNumber: String,
    val currency: String,
    val amount: Long,
    val objectifyPayload: Boolean = true,
)
