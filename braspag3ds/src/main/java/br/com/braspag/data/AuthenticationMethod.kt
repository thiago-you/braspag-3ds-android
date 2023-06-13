package br.com.braspag.data

enum class AuthenticationMethod(val value: String) {
    NO_AUTHENTICATION("01"),
    OWN_STORE_LOGIN("02"),
    FEDERATED_LOGIN("03"),
    FIDO_AUTHENTICATOR("04"),
}
