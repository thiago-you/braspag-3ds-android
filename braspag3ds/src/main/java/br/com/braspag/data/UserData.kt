package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserData(
    @SerializedName("guest")
    val guest: Boolean? = null,
    @SerializedName("createdDate")
    val createdDate: String? = null,
    @SerializedName("changedDate")
    val changedDate: String? = null,
    @SerializedName("passwordChangedDate")
    val passwordChangedDate: String? = null,
    @SerializedName("authenticationMethod")
    val authenticationMethod: AuthenticationMethod? = null,
    @SerializedName("authenticationProtocol")
    val authenticationProtocol: String? = null,
    @SerializedName("authenticationTimestamp")
    val authenticationTimestamp: String? = null,
    @SerializedName("newCustomer")
    val newCustomer: Boolean? = null,
) : Serializable
