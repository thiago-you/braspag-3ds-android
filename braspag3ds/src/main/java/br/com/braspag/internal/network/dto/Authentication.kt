package br.com.braspag.internal.network.dto

import com.google.gson.annotations.SerializedName

internal data class Authentication(

    @SerializedName("DirectoryServerTransactionId")
    val transactionId: String? = null,

    @SerializedName("Xid")
    val xId: String? = null,

    @SerializedName("Eci")
    val eci: String? = null,

    @SerializedName("EciRaw")
    val eciRaw: String? = null,

    @SerializedName("Cavv")
    val cavv: String? = null,

    @SerializedName("ParesStatus")
    val paresStatus: String? = null,

    @SerializedName("AuthenticationResult")
    val authenticationResult: String? = null,

    @SerializedName("AuthenticationStatusMessage")
    val authenticationStatusMessage: String? = null,

    @SerializedName("Version")
    val version: String? = null,

    @SerializedName("Status")
    val status: String? = null,

    @SerializedName("ReturnCode")
    val returnCode: String? = null,

    @SerializedName("ReturnMessage")
    val returnMessage: String? = null,

)
