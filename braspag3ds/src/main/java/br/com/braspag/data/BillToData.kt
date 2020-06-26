package br.com.braspag.data

data class BillToData(
    val customerId: String? = null,
    val contactName: String? = null,
    val phoneNumber: String,
    val email: String,
    val street1: String,
    val street2: String? = null,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String
)
