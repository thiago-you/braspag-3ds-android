package br.com.braspag.data

import com.google.gson.annotations.SerializedName

enum class AuthenticationMethod {
    @SerializedName("01")
    noAuthentication,

    @SerializedName("02")
    ownStoreLogin,

    @SerializedName("03")
    federeatedLogin,

    @SerializedName("04")
    fidoAuthenticator
}