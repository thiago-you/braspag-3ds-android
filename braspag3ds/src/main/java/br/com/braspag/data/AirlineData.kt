package br.com.braspag.data

data class AirlineData(
    val travelLeg: List<TravelLeg>? = null,
    val passenger: List<Passenger>? = null,
    val numberOfPassengers: Long? = null,
    val billToPassportCountry: String? = null,
    val billToPassportNumber: String? = null,
)
