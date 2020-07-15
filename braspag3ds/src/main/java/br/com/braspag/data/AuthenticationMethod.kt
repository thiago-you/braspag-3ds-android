package br.com.braspag.data

enum class AuthenticationMethod(val value: String) {
    noAuthentication("01"),
    ownStoreLogin("02"),
    federeatedLogin("03"),
    fidoAuthenticatior("04")
}