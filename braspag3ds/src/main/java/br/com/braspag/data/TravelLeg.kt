package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TravelLeg(
    @SerializedName("carrier")
    val carrier: String? = null,
    @SerializedName("departureDate")
    val departureDate: String? = null,
    @SerializedName("origin")
    val origin: String? = null,
    @SerializedName("destination")
    val destination: String? = null,
) : Serializable
