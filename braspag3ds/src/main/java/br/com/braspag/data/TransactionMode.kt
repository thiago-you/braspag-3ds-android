package br.com.braspag.data

import com.google.gson.annotations.SerializedName

enum class TransactionMode {
    @SerializedName("M")
    moto,

    @SerializedName("M")
    retail,

    @SerializedName("M")
    eCommerce,

    @SerializedName("M")
    mobile,

    @SerializedName("M")
    tablet
}