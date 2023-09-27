package br.com.braspag.internal.network.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

internal data class ResponseJWT(
    @SerializedName("ReferenceId")
    val referenceId: String? = null,
    @SerializedName("Token")
    val token: String? = null,
) : Serializable
