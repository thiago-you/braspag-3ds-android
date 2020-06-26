package br.com.braspag.data

import androidx.annotation.Keep

@Keep
data class TravelLeg(
    val carrier: String? = null,
    val departureDate: String? = null,
    val origin: String? = null,
    val destination: String? = null
)