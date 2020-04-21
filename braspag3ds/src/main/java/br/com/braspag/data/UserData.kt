package br.com.braspag.data

data class UserData(
    val guest: Boolean? = null,
    val createdDate: String? = null,
    val changedDate: String? = null,
    val passwordChangedDate: String? = null,
    val authenticationMethod: AuthenticationMethod? = null,
    val authenticationProtocol: String? = null,
    val authenticationTimestamp: String? = null,
    val newCustomer: Boolean? = null
)