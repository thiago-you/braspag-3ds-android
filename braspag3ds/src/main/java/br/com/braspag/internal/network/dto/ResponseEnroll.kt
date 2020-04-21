package br.com.braspag.internal.network.dto

import com.google.gson.annotations.SerializedName

internal data class ResponseEnroll(
    @SerializedName("CardBrand")
    val cardBrand: Int,

    @SerializedName("VEResEnrolled")
    val verEsEnrolled: String?,

    @SerializedName("AcsUrl")
    val acsUrl: String?,

    @SerializedName("Pareq")
    val paReq: String?,

    @SerializedName("AuthenticationTransactionId")
    val authenticationTransactionId: String?,

    @SerializedName("MessageCategory")
    val messageCategory: String?,

    @SerializedName("Version")
    val version: String?,

    @SerializedName("Status")
    val status: String?,

    @SerializedName("ReturnCode")
    val returnCode: String?,

    @SerializedName("ReturnMessage")
    val returnMessage: String?,

    @SerializedName("Authentication")
    val authentication: Authentication
)


