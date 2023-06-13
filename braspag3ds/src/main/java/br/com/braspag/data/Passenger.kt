package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Passenger(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("ticketPrice")
    val ticketPrice: Long? = null,
) : Serializable
