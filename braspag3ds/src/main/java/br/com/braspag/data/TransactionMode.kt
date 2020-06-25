package br.com.braspag.data

import com.google.gson.annotations.SerializedName

enum class TransactionMode {
    @SerializedName("M")
    moto,

    @SerializedName("R")
    retail,

    @SerializedName("S")
    eCommerce,

    @SerializedName("P")
    mobile,

    @SerializedName("T")
    tablet
}