package br.com.braspag.data

enum class AuthenticationMethod(val value: Int) {
    noAuthentication(1),
    ownStoreLogin(2),
    federeatedLogin(3),
    fidoAuthenticatior(4)
}