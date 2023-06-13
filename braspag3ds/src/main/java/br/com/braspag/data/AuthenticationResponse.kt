package br.com.braspag.data

data class AuthenticationResponse(
    val status: AuthenticationResponseStatus,
    val cavv: String? = null,
    val xId: String? = null,
    val eci: String? = null,
    val version: String? = null,
    val referenceId: String? = null,
    val returnCode: String? = null,
    val returnMessage: String? = null,
)
