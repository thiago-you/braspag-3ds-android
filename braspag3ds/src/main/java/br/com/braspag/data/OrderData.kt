package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrderData(
    @SerializedName("orderNumber")
    val orderNumber: String,
    @SerializedName("currencyCode")
    val currencyCode: String = "BRL",
    @SerializedName("totalAmount")
    val totalAmount: Long,
    @SerializedName("paymentMethod")
    val paymentMethod: PaymentMethod,
    @SerializedName("installments")
    val installments: Int? = null,
    @SerializedName("recurrence")
    val recurrence: Boolean? = null,
    @SerializedName("productCode")
    val productCode: ProductCode? = null,
    @SerializedName("countLast24Hours")
    val countLast24Hours: Int? = null,
    @SerializedName("countLast6Months")
    val countLast6Months: Int? = null,
    @SerializedName("countLast1Year")
    val countLast1Year: Int? = null,
    @SerializedName("cardAttemptsLast24Hours")
    val cardAttemptsLast24Hours: Int? = null,
    @SerializedName("marketingOptIn")
    val marketingOptIn: Boolean? = null,
    @SerializedName("marketingSource")
    val marketingSource: String? = null,
    @SerializedName("transactionMode")
    val transactionMode: TransactionMode? = null,
    @SerializedName("merchantUrl")
    val merchantUrl: String? = null,
) : Serializable
