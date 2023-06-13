package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AirlineData(
    @SerializedName("travelLeg")
    val travelLeg: List<TravelLeg>? = null,
    @SerializedName("passenger")
    val passenger: List<Passenger>? = null,
    @SerializedName("numberOfPassengers")
    val numberOfPassengers: Long? = null,
    @SerializedName("billToPassportCountry")
    val billToPassportCountry: String? = null,
    @SerializedName("billToPassportNumber")
    val billToPassportNumber: String? = null,
) : Serializable
