package br.com.braspag.data

data class BillToData(
    val customerId: String? = null,
    val contactName: String,
    val phoneNumber: String,
    val email: String,
    val street1: String,
    val street2: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String = "BR"
)
