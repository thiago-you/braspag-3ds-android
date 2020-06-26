package br.com.braspag.data

import androidx.annotation.Keep

@Keep
data class Passenger(
    val name: String? = null,
    val ticketPrice: Long? = null
)