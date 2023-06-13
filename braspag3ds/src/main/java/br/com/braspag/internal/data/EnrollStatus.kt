package br.com.braspag.internal.data

internal enum class EnrollStatus {
    ENROLLED,
    VALIDATION_NEEDED,
    AUTHENTICATION_CHECK_NEEDED,
    NOT_ENROLLED,
    FAILED,
    UNSUPPORTED_BRAND,
    ERROR,
}
