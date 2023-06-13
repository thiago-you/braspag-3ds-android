package br.com.braspag.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthenticationResponse(
    @SerializedName("status")
    val status: AuthenticationResponseStatus,
    @SerializedName("cavv")
    val cavv: String? = null,
    @SerializedName("xId")
    val xId: String? = null,
    @SerializedName("eci")
    val eci: String? = null,
    @SerializedName("version")
    val version: String? = null,
    @SerializedName("referenceId")
    val referenceId: String? = null,
    @SerializedName("returnCode")
    val returnCode: String? = null,
    @SerializedName("returnMessage")
    val returnMessage: String? = null,
) : Serializable
