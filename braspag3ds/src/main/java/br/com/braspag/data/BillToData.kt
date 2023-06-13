package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BillToData(
    @SerializedName("customerId")
    val customerId: String? = null,
    @SerializedName("contactName")
    val contactName: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("street1")
    val street1: String,
    @SerializedName("street2")
    val street2: String? = null,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("zipCode")
    val zipCode: String,
    @SerializedName("country")
    val country: String,
) : Serializable
