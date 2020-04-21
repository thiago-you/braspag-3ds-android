package br.com.braspag.data

data class AirlineData(
    val travelLeg: List<TravelLeg>? = listOf(),
    val passenger: List<Passenger>? = listOf(),
    val numberOfPassengers: Long? = null,
    val billToPassportCountry: String? = null,
    val billToPassportNumber: String? = null
)