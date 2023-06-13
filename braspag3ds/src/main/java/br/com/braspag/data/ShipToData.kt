package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShipToData(
    @SerializedName("sameAsBillTo")
    val sameAsBillTo: Boolean? = null,
    @SerializedName("addressee")
    val addressee: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("street1")
    val street1: String? = null,
    @SerializedName("street2")
    val street2: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("zipCode")
    val zipCode: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("shippingMethod")
    val shippingMethod: String? = null,
    @SerializedName("firstUsageDate")
    val firstUsageDate: String? = null,
) : Serializable
