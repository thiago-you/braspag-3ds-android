package br.com.braspag.data

data class ShipToData(
    val sameAsBillTo: Boolean? = null,
    val addressee: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val street1: String? = null,
    val street2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val zipCode: String? = null,
    val country: String? = null,
    val shippingMethod: String? = null,
    val firstUsageDate: String? = null,
)
