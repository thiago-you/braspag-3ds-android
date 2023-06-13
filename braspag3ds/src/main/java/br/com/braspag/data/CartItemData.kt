package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CartItemData(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("sku")
    val sku: String? = null,
    @SerializedName("quantity")
    val quantity: Long? = null,
    @SerializedName("unitPrice")
    val unitPrice: Long? = null,
) : Serializable
