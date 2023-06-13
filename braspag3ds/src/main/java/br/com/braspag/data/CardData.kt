package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CardData(
    @SerializedName("number")
    val number: String,
    @SerializedName("expirationMonth")
    val expirationMonth: String,
    @SerializedName("expirationYear")
    val expirationYear: String,
    @SerializedName("cardAlias")
    val cardAlias: String? = null,
    @SerializedName("defaultCard")
    val defaultCard: Boolean? = null,
) : Serializable
