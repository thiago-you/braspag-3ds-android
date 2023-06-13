package br.com.braspag.internal.network.dto

import br.com.braspag.data.PaymentMethod
import com.google.gson.annotations.SerializedName
import java.io.Serializable

internal data class RequestValidate(
    @SerializedName("orderNumber")
    val orderNumber: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("totalAmount")
    val totalAmount: Long?,
    @SerializedName("paymentMethod")
    val paymentMethod: PaymentMethod?,
    @SerializedName("cardNumber")
    val cardNumber: String,
    @SerializedName("cardExpirationMonth")
    val cardExpirationMonth: String,
    @SerializedName("cardExpirationYear")
    val cardExpirationYear: String,
    @SerializedName("authNotifyOnly")
    val authNotifyOnly: Boolean?,
    @SerializedName("authSuppressChallenge")
    val authSuppressChallenge: Boolean?,
    @SerializedName("transactionId")
    val transactionId: String,
) : Serializable
