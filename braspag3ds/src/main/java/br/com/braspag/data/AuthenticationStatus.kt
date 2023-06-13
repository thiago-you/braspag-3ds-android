package br.com.braspag.data

enum class AuthenticationResponseStatus {
    SUCCESS,
    FAILURE,
    ERROR,
    UNENROLLED,
    UNSUPPORTED_BRAND,
    CHALLENGE_SUPPRESSION,
}
