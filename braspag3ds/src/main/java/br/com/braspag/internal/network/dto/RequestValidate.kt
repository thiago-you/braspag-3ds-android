package br.com.braspag.internal.network.dto

import br.com.braspag.data.PaymentMethod
import com.google.gson.annotations.SerializedName

internal data class RequestValidate(
    @SerializedName("OrderNumber")
    val orderNumber: String,

    @SerializedName("Currency")
    val currency: String,

    @SerializedName("TotalAmount")
    val totalAmount: Long?,

    @SerializedName("PaymentMethod")
    val paymentMethod: PaymentMethod?,

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