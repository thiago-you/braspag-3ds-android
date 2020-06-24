package br.com.braspag.data

import com.google.gson.annotations.SerializedName

enum class AuthenticationMethod {
    @SerializedName("1")
    noAuthentication,

    @SerializedName("2")
    ownStoreLogin,

    @SerializedName("3")
    federeatedLogin,

    @SerializedName("4")
    fidoAuthenticatior
}