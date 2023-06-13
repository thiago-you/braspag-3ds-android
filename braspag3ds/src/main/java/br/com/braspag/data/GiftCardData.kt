package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GiftCardData(
    @SerializedName("giftCardAmount")
    val giftCardAmount: Long? = null,
    @SerializedName("giftCardCurrency")
    val giftCardCurrency: GiftCardCurrency? = null,
) : Serializable
