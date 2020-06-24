package br.com.braspag.data

import com.google.gson.annotations.SerializedName

enum class PaymentMethod {
    @SerializedName("credit")
    credit,

    @SerializedName("debit")
    debit
}