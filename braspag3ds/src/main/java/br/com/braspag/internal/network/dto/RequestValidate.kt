package br.com.braspag.internal.network.dto

import br.com.braspag.data.PaymentMethod

internal data class RequestValidate(
    val orderNumber: String,
    val currency: String,
    val totalAmount: Long?,
    val paymentMethod: PaymentMethod?,
    val cardNumber: String,
    val cardExpirationMonth: String,
    val cardExpirationYear: String,
    val authNotifyOnly: Boolean?,
    val authSuppressChallenge: Boolean?,
    val transactionId: String,
)
