package br.com.braspag.internal.network.dto

import com.google.gson.annotations.SerializedName

internal data class RequestValidate(
    @SerializedName("OrderNumber")
    val orderNumber: String,

    @SerializedName("Currency")
    val currency: String,

    @SerializedName("TotalAmount")
    val totalAmount: Long?,

    @SerializedName("PaymentMethod")
    val paymentMethod: String?,

    @SerializedName("CardNumber")
    val cardNumber: String,

    @SerializedName("CardExpirationMonth")
    val cardExpirationMonth: String,

    @SerializedName("CardExpirationYear")
    val cardExpirationYear: String,

    @SerializedName("AuthNotifyOnly")
    val authNotifyOnly: Boolean?,

    @SerializedName("AuthSuppressChallenge")
    val authSuppressChallenge: Boolean?,

    @SerializedName("TransactionId")
    val transactionId: String
)